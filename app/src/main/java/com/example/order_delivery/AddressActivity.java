package com.example.order_delivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddressActivity extends AppCompatActivity {

    private EditText etAddress;
    private Button btSubmit;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        etAddress = findViewById(R.id.etAddress);
        btSubmit = findViewById(R.id.btSubmit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = etAddress.getText().toString();
                System.out.println(address);
                Intent intent = new Intent();
                intent.putExtra("address", address);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}