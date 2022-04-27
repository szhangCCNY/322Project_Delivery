package com.example.order_delivery;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("sz_customer")
public class sz_customer extends ParseObject {
    public static final String KEY_NAME = "name";
    public static final String KEY_BALANCE = "balance";
    public static final String KEY_WARNING = "warning";
    public static final String KEY_VIP = "vip";
    public static final String KEY_OBJECTID = "objectId";

    public String getName(){
        return getString(KEY_NAME);
    }
    public void setName(String name){
        put(KEY_NAME, name);
    }

    public double getBalance(){
        return getNumber(KEY_BALANCE).doubleValue();
    }

    public void setBalance(double balance){
        put(KEY_BALANCE, balance);
    }

    public int getWarning(){
        return getNumber(KEY_WARNING).intValue();
    }

    public void setWarning(int warning){
        put(KEY_WARNING, warning);
    }
    public boolean getVip(){
        return getBoolean(KEY_VIP);
    }

    public void setVip(boolean vip ){
        put(KEY_VIP, vip);
    }
}
