package com.example.order_delivery;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseUser {
    public static final String KEY_TYPE = "type";

    public String getType(){
        return getType();
    }
}
