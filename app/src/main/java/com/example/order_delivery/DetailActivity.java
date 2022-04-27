package com.example.order_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private int count = 0;
    private TextView tvCount;
    private TextView tvItemNameDetail;
    private TextView tvItemDescDetail;
    private TextView tvPriceDetail;
    private ImageView ivImageDetail;
    private String itemObjectId;
    private String itemName;
    private String itemPrice;
    private String itemImageURL;
    private CartItem thisItem = new CartItem();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvCount = findViewById(R.id.tvCount);
        tvItemNameDetail = findViewById(R.id.tvItemNameDetail);
        tvItemDescDetail = findViewById(R.id.tvItemDescDetail);
        tvPriceDetail = findViewById(R.id.tvPriceDetail);
        ivImageDetail = findViewById(R.id.ivItemDetail);
        itemName = getIntent().getStringExtra("itemName");
        String itemDesc = getIntent().getStringExtra("itemDesc");
        itemPrice = getIntent().getStringExtra("itemPrice");
        itemImageURL = getIntent().getStringExtra("itemImageURL");
        itemObjectId = getIntent().getStringExtra("itemObjectId");
        //the following code sets the require field
        tvItemNameDetail.setText(itemName);
        tvItemDescDetail.setText(itemDesc);
        tvPriceDetail.setText("$" + itemPrice);
        Glide.with(this).load(itemImageURL).into(ivImageDetail);

    }

    public void onAddCartClick(View view) {
        thisItem.setField(itemName, Integer.toString(count), itemImageURL, itemPrice);
        //when this is click, update
        if (count > 0){
            CheckOut.cartItemList.add(thisItem);
            finish();
        }
        else {
            Toast.makeText(DetailActivity.this, "Cart size:" + CheckOut.cartItemList.size(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onRemoveClick(View view) {
        Toast.makeText(DetailActivity.this, "btn remove clicked", Toast.LENGTH_SHORT).show();
        if (count > 0) {
            count--;
            updateCount();
        }
        //update count text
    }

    public void onAddClick(View view) {
        Toast.makeText(DetailActivity.this, "btn add clicked", Toast.LENGTH_SHORT).show();
        if (count < 100) {
            count++;
            updateCount();
        }
        //update count text
    }

    //this just updates count
    public void updateCount(){
        tvCount.setText(Integer.toString(count));
    }

    public void onBackClick(View view) {
        Toast.makeText(DetailActivity.this, "btn back clicked", Toast.LENGTH_SHORT);
        finish();
    }
}