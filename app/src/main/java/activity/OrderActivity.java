package activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ntd.shopping.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapter.OrderAdapter;
import model.OrderCart;
import util.CheckInternetConnection;
import util.Sever;

public class OrderActivity extends AppCompatActivity {
    RecyclerView reOrderCart;
    Toolbar toolbar;
    OrderAdapter orderAdapter;
    ArrayList<OrderCart> mangOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initToolbar();
        getOrderCart();

    }

    private void getOrderCart() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String link= Sever.getcart+"?UserName="+MainActivity.username;
        Log.d("s", link);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id=0;
                String name="";
                String custunmer_name="";
                String custunmer_adress="";
                int price=0;
                String cost;
                int productId=0;
                if(response != null && response.length() != 2){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        System.out.println(response);
                        for (int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("order_items");
                            custunmer_name = jsonObject.getString("custumer_name");
                            custunmer_adress = jsonObject.getString("custumer_adress");
                            price = jsonObject.getInt("subtotal");

//                            name = jsonObject.getString("name");
//                            price = jsonObject.getInt("cost");
//                            image = jsonObject.getString("image");
//                            productId = jsonObject.getInt("product_id");
//                            mangOrder.add(new OrderCart(id, name));
                            mangOrder.add(new OrderCart(id, custunmer_name, custunmer_adress, price));
                            orderAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    CheckInternetConnection.ShowToast_Short(getApplicationContext(),"No more data !");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String,String>();
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        reOrderCart =(RecyclerView) findViewById(R.id.recyclerview_order);
        toolbar = findViewById(R.id.toolbarorder);
        mangOrder = new ArrayList<>();
        orderAdapter =new OrderAdapter(getApplicationContext(),mangOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reOrderCart.setLayoutManager(layoutManager);
        reOrderCart.setAdapter(orderAdapter);


//        lvOrderCart.setAdapter(orderAdapter);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}