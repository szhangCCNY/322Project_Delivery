package com.example.order_delivery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.List;

public class sz_item_custAdapter extends RecyclerView.Adapter<sz_item_custAdapter.ViewHolder> {
    private Context context;
    private List<sz_item_cust> item_cust;

    public sz_item_custAdapter(Context context, List<sz_item_cust> item_cust){
        this.context = context;
        this.item_cust = item_cust;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        sz_item_cust item = item_cust.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return item_cust.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
            itemContainer = itemView.findViewById(R.id.itemContainer);
        }


        public void bind(sz_item_cust item) {
            tvItemName.setText(item.getItemName());
            tvDescription.setText(item.getItemDescription());
            tvPrice.setText("$" + item.getItemPrice());
            tvRating.setText(item.getItemRating() + "/5");
            ParseFile image = item.getItemImage();

            if (image != null){
                Glide.with(context).load(item.getItemImage().getUrl()).into(ivItem);
            }
            itemContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Item clicked in adapter", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("itemName", item.getItemName());
                    i.putExtra("itemDesc", item.getItemDescription());
                    i.putExtra("itemPrice", item.getItemPrice());
                    i.putExtra("itemImageURL", item.getItemImage().getUrl());
                    i.putExtra("itemObjectId", item.getObjectId());
                    context.startActivity(i);
//                    System.out.println(getAdapterPosition());
//                    item.deleteInBackground(); //delete item on server
//                    item_cust.remove(getAdapterPosition()); //delete locally on recycler list

                    notifyDataSetChanged(); //update recycler view
                }
            });
        }
    }
}
