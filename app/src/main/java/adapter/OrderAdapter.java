package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ntd.shopping.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import model.OrderCart;
//RecyclerView.Adapter<OrderAdapter.MyViewHolder> implements ListAdapter

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ItemHolder> {
//    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    ArrayList<OrderCart> listOrderCart;

    public OrderAdapter(Context context, ArrayList<OrderCart> listOrderCart) {
        this.context = context;
        this.listOrderCart = listOrderCart;
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtiddonhang, txtcustumername, txtcustumeraddress, txtsubtotal;

        public ItemHolder(View itemView) {
            super(itemView);
            txtiddonhang = (TextView) itemView.findViewById(R.id.iddonhang);
            txtcustumername = (TextView) itemView.findViewById(R.id.custumerName);
            txtcustumeraddress = (TextView) itemView.findViewById(R.id.custumerAddress);
            txtsubtotal = (TextView) itemView.findViewById(R.id.subtotal);
        }
    }

    @NonNull
    @Override
    public OrderAdapter.ItemHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_order,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(OrderAdapter.ItemHolder itemHolder, int i) {
        OrderCart orderCart = listOrderCart.get(i);
        itemHolder.txtiddonhang.setText("Đơn Hàng: " + orderCart.getId());
        itemHolder.txtcustumername.setText("Khách hàng: " + orderCart.getUsername());
        itemHolder.txtcustumeraddress.setText("Địa chỉ: " + orderCart.getAddress());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        itemHolder.txtsubtotal.setText("Tổng Tiền: "+ decimalFormat.format(orderCart.getSubtotal())+"Đ");

    }

    @Override
    public int getItemCount() {
        return listOrderCart.size();
    }





//    @Override
//    public int getCount() {
//        return listOrderCart.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return listOrderCart.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder = null;
//        if (view==null){
//            viewHolder = new ViewHolder();
//            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view=inflater.inflate(R.layout.dong_cart,null);
//            viewHolder.txtidorder = (TextView) view.findViewById(R.id.iddonhang);
//            viewHolder.txtsubtotal = (TextView) view.findViewById(R.id.subtotal);
//            viewHolder.txtcustumername = (TextView) view.findViewById(R.id.custumerName);
//            viewHolder.txtcustumeraddress = (TextView) view.findViewById(R.id.custumerAddress);
//            view.setTag(viewHolder);
//        }else {
//            viewHolder= (ViewHolder) view.getTag();
//        }
//        OrderCart orderCart= (OrderCart) getItem(i);
//        viewHolder.txtidorder.setText(orderCart.getId());
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        viewHolder.txtsubtotal.setText(decimalFormat.format(orderCart.getSubtotal()) + " Đ");
//        viewHolder.txtcustumername.setText(orderCart.getUsername());
//        viewHolder.txtcustumername.setText(orderCart.getAddress());
//
//        return view;
//    }

}
