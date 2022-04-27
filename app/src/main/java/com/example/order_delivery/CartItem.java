package com.example.order_delivery;



public class CartItem {
    private String quantity;
    private String name;
    private String imageUrl;
    private String price;

    public CartItem(){
        quantity = null;
        name = null;
        imageUrl = null;
        price = null;
    }

    public void setField(String name, String quantity, String imageUrl, String price){
        this.name = name;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getQuantity(){
        return this.quantity;
    }

    public String getName(){
        return this.name;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
    public String getPrice(){
        return this.price;
    }
}
