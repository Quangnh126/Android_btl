package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ntd.shopping.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.ItemCart;

public class ChitietOrderAdapter extends RecyclerView.Adapter<ChitietOrderAdapter.MyViewHolder> {
    Context context;
    List<ItemCart> itemCartList;

    public ChitietOrderAdapter(List<ItemCart> itemCartList) {
        this.context = context;
        this.itemCartList = itemCartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chitiet, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ItemCart itemCart = itemCartList.get(i);
        myViewHolder.txttensp.setText(itemCart.getName() + " ");
        Picasso.with(context).load(itemCart.getImage())
                .error(R.drawable.error)
                .into(myViewHolder.imageChitiet);

    }

    @Override
    public int getItemCount() {
        return itemCartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageChitiet;
        TextView txttensp, txtsoluong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageChitiet = itemView.findViewById(R.id.item_imagechitiet);
            txttensp = itemView.findViewById(R.id.item_tenspchitiet);
            txtsoluong = itemView.findViewById(R.id.item_soluongchitiet);
        }
    }
}
