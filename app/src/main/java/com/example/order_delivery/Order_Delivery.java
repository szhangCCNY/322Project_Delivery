package com.example.order_delivery;

import android.app.Application;

import com.parse.Parse;
import android.app.Application;
import com.parse.ParseObject;

public class Order_Delivery extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ppwkyUgSD69n0LPhUB22zKo5BAzCCPfidmCGvbEr")
                .clientKey("QvrQuB08ZNrgbYW7TIRFBHMZ9IyTzzz7Atc5JgB6")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
