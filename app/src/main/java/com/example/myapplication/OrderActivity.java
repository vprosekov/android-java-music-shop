package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent recievedOrderIntent = getIntent();
        String userName = recievedOrderIntent.getStringExtra("userName");
        String goodsName = recievedOrderIntent.getStringExtra("goodsName");
        int quantity = recievedOrderIntent.getIntExtra("quantity", 0);
        double price = recievedOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = recievedOrderIntent.getDoubleExtra("orderPrice", 0);

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Customer name: "+userName+'\n'+
                "Goods name: "+goodsName+'\n'+
                "Quantity: "+quantity+'\n'+
                "Price: "+price+'\n'+
                "Order price: "+orderPrice);
    }
}