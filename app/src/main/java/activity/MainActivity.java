package activity;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toolbar;
import android.widget.ViewFlipper;
import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ntd.shopping.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import adapter.ProductAdapter;
import adapter.ProductTypeAdapter;
import model.Cart;
import model.Product;
import model.ProductType;
import util.CheckInternetConnection;
import util.Sever;

public class MainActivity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<ProductType> arrProductType;
    ProductTypeAdapter productTypeAdapter;
    ArrayList<Product> arrProduct;
    ProductAdapter productAdapter;
    Button btnuser;
    static boolean islogin=false;
    static String username;
    int ID;
    String producttypename= "";
    String image = "";
    public static ArrayList<Cart> arrCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
        {
            Actionbar();
            ActionViewFlipper();
            GetProductTypeData();
            GetNewProductData();
            CatchOnItemListView();
            CheckLogin();
        }
        else
        {
            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Internet Connection");
            finish();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menucart:
                Intent intent = new Intent(getApplicationContext(),CartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,LapTopActivity.class);
                            intent.putExtra("producttypeid",arrProductType.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,DienThoaiActivity.class);
                            intent.putExtra("producttypeid",arrProductType.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,LienHeActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,ThongTinActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckInternetConnection.haveNetworkConnection(getApplicationContext()))
                        {
                            Intent intent = new Intent(MainActivity.this,OrderActivity.class);
//                            intent.putExtra("producttypeid",arrProductType.get(i).getId());
                            startActivity(intent);
                        }
                        else
                        {
                            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Check Your Internet Connection");

                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetNewProductData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.newproduct, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    int ID=0;
                    String productname="";
                    Integer productprice=0;
                    String image="";
                    String productdetail="";
                    int productid=0;
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            productname = jsonObject.getString("productname");
                            productprice = jsonObject.getInt("price");
                            image = jsonObject.getString("image");
                            productdetail = jsonObject.getString("productdetail");
                            productid = jsonObject.getInt("productid");
                            arrProduct.add(new Product(ID,productname,productprice,image,productdetail,productid));
                            productAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetProductTypeData() {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.producttypelink, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)
                {
                    for(int i=0;i<response.length();i++)
                    {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            producttypename = jsonObject.getString("devicetypename");
                            image = jsonObject.getString("image");
                            arrProductType.add(new ProductType(ID,producttypename,image));
                            productTypeAdapter.notifyDataSetChanged();
                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    arrProductType.add(3,new ProductType(0,"Lien He","https://cdn1.iconfinder.com/data/icons/mix-color-3/502/Untitled-12-512.png"));
                    arrProductType.add(4,new ProductType(0,"Thong Tin","https://cdn4.iconfinder.com/data/icons/pictograms-2/512/512-05-512.png"));
                    arrProductType.add(5,new ProductType(0,"Don Hang","https://cdn4.iconfinder.com/data/icons/pictograms-2/512/512-05-512.png"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckInternetConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://cdn.tgdd.vn/qcao/01_11_2018_17_18_35_iphone-new-800-300.png");
        mangquangcao.add("https://res.cloudinary.com/dqwmcmprm/image/upload/v1669622665/image_android/coca.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/qcao/06_11_2018_09_35_11_SWkhaitruong-800-300.png");
        mangquangcao.add("https://cdn.tgdd.vn/qcao/07_11_2018_16_14_03_SiuSim-vietnammobile-800-300.png");
        mangquangcao.add("https://cdn.tgdd.vn/qcao/15_10_2018_10_41_10_phu-kien-giam-soc-800-300.png");
        for(int i=0;i<mangquangcao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void Actionbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void CheckLogin()
    {
        if(islogin)
            CheckInternetConnection.ShowToast_Short(getApplicationContext(),"Log In Successfully");
    }
    private void Anhxa() {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewfliper);
        recyclerViewmanhinhchinh = (RecyclerView) findViewById(R.id.recyclerview);
        navigationView = (NavigationView) findViewById(R.id.navigationviewmanhinhchinh);
        listViewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        arrProductType = new ArrayList<>();
        arrProductType.add(0,new ProductType(0,"Trang Chinh","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT88q_ol7xsgmf-rcznW6UEn2JNqPQSv3IKTtPBkz52cK1lcsxq"));
        productTypeAdapter = new ProductTypeAdapter(arrProductType,getApplicationContext());
        listViewmanhinhchinh.setAdapter(productTypeAdapter);
        arrProduct = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(),arrProduct);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerViewmanhinhchinh.setAdapter(productAdapter);
        btnuser =(Button)findViewById(R.id.user);
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        if(arrCart != null){

        }else{
            arrCart=new ArrayList<>();
        }
    }
    private void validate()
    {
        Intent intent;
        if(islogin==false) {
            intent = new Intent(MainActivity.this, LogIn.class);
        }
        else
        {
            intent = new Intent(MainActivity.this, UserInformation.class);
        }
            startActivity(intent);
    }
}
