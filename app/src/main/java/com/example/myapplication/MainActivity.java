package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;
import android.content.Context;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int quantity = 0;
    Spinner spinner;

    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;

    String goodsName;

    double price;

    EditText userNameEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();

        createMap();

        userNameEditText = findViewById(R.id.nameEditText);
    }

    public void increaseQuantity(View view) {
        quantity++;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(""+quantity);
        vibrate(25);

        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*price);
    }
    public void decreaseQuantity(View view) {
        if(quantity<=1) {
            quantity = 0;
            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText("" + quantity);
            vibrate(100);
            //Toast.makeText(getApplicationContext(),"Quantity is already 0", Toast.LENGTH_SHORT).show();
        }
        else{
            quantity--;
            TextView quantityTextView = findViewById(R.id.quantityTextView);
            quantityTextView.setText("" + quantity);
            vibrate(25);
        }
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*price);
    }
    public void vibrate(int duration)
    {
        Vibrator vibs = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch(goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    void createMap(){
        goodsMap = new HashMap();

        goodsMap.put("guitar",500.0);
        goodsMap.put("drums",1500.0);
        goodsMap.put("keyboard",1000.0);
    }
    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();

        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.price = price;
        order.orderPrice = quantity*price;
        if(order.userName==""||order.userName.length()<=2){
            vibrate(200);
            Toast.makeText(getApplicationContext(),"Enter your name", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
            orderIntent.putExtra("userName", order.userName);
            orderIntent.putExtra("goodsName", order.goodsName);
            orderIntent.putExtra("quantity", order.quantity);
            orderIntent.putExtra("price", order.price);
            orderIntent.putExtra("orderPrice", order.orderPrice);
            startActivity(orderIntent);
        }

    }
}