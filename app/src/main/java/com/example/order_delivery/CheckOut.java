package com.example.order_delivery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class CheckOut extends AppCompatActivity {
    private double totalCost;
    private TextView tvTotalCost;
    private RecyclerView rvCheckoutList;
    private checkOutAdapter adapter;
    private String address;

    public static final int REQUEST_CODE = 20;
    public static ArrayList<CartItem> cartItemList = new ArrayList<>();
    public static final String TAG = "CHECKOUT ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        rvCheckoutList = findViewById(R.id.rvCheckoutList);
        totalCost = 0;
        adapter = new checkOutAdapter(this, cartItemList);
        rvCheckoutList.setAdapter(adapter);
        rvCheckoutList.setLayoutManager(new LinearLayoutManager(this));
        setTotalCost();
    }

    public void setTotalCost(){
        //calculate price
        for(int i = 0; i < cartItemList.size(); i++){
            totalCost += Integer.parseInt(cartItemList.get(i).getQuantity()) * Double.parseDouble(cartItemList.get(i).getPrice());
        }
        tvTotalCost.setText("Your total: " + "$" + Double.toString(totalCost));
    }
    public void onCheckoutClick(View view) {
        if (totalCost > CurrentUserInfo.currentUserBalance) {
            Toast.makeText(CheckOut.this, "Total cost greater than balance: Receiving a warning", Toast.LENGTH_SHORT).show();
            CurrentUserInfo.currentUserWarning += 1;
            CurrentUserInfo.currentUser.setWarning(CurrentUserInfo.currentUserWarning);
            CurrentUserInfo.currentUser.saveInBackground();
            System.out.println(CurrentUserInfo.currentUserWarning + "current warning");
        }
        else{
            CurrentUserInfo.currentUserBalance -= totalCost;
            CurrentUserInfo.currentUser.setBalance(CurrentUserInfo.currentUserBalance);
            CurrentUserInfo.currentUser.saveInBackground();
            System.out.println(CurrentUserInfo.currentUserBalance + "current balance");
            Intent intent = new Intent(this, AddressActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
//        saveCheckoutItem(true);
//        cartItemList.clear(); //empty out cartitem list because its all checked out
//        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            //get data from intent (address)
            //update string
            address = data.getExtras().getString("address");
            System.out.println(address + "in checkout");
            saveCheckoutItem(true);
            cartItemList.clear();
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveCheckoutItem(boolean delivery) {
        for (int i = 0; i < cartItemList.size(); i++){
            CheckoutList checkoutList = new CheckoutList();
            checkoutList.setQuantity(cartItemList.get(i).getQuantity());
            checkoutList.setItem(cartItemList.get(i).getName());
            checkoutList.setPrice(cartItemList.get(i).getPrice());
            checkoutList.setImageURL(cartItemList.get(i).getImageUrl());
            if (delivery == true){
                checkoutList.setAddress(address);
            }
            checkoutList.saveInBackground();
        }
    }

    public void onPickUpClick(View view) {
        if (totalCost > CurrentUserInfo.currentUserBalance) {
            Toast.makeText(CheckOut.this, "Total cost greater than balance: Receiving a warning", Toast.LENGTH_SHORT).show();
            CurrentUserInfo.currentUserWarning += 1;
            CurrentUserInfo.currentUser.setWarning(CurrentUserInfo.currentUserWarning);
            CurrentUserInfo.currentUser.saveInBackground();
            System.out.println(CurrentUserInfo.currentUserWarning + "current warning");
        }
        else{
            CurrentUserInfo.currentUserBalance -= totalCost;
            CurrentUserInfo.currentUser.setBalance(CurrentUserInfo.currentUserBalance);
            System.out.println(CurrentUserInfo.currentUserBalance + "current balance");
            CurrentUserInfo.currentUser.saveInBackground();
            saveCheckoutItem(false);
            cartItemList.clear();
            finish();
        }
    }
}