package com.example.order_delivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class checkOutAdapter extends RecyclerView.Adapter<checkOutAdapter.ViewHolder> {
    private Context context;
    private List<CartItem> cartItems;

    public checkOutAdapter(Context context, List<CartItem> cartItems){
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivItem;
        private TextView tvItemName;
        private TextView tvDescription;
        private TextView tvPrice;
        private TextView tvRating;
        private RelativeLayout itemContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.ivItem);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
        }

        public void bind(CartItem item) {
            tvItemName.setText(item.getName());
            tvDescription.setText("");
            tvPrice.setText("$" + item.getPrice());
            tvRating.setText("");
            Glide.with(context).load(item.getImageUrl()).into(ivItem);
        }
    }

}
