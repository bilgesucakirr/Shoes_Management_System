package com.example.shoesmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button shoeslistbutton, addshoesbutton, deleteshoesbutton, updateshoesbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoeslistbutton = findViewById(R.id.shoesbutton);
        addshoesbutton = findViewById(R.id.addbutton);
        deleteshoesbutton = findViewById(R.id.deletebutton);
        updateshoesbutton = findViewById(R.id.updatebutton);


        shoeslistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoesList.class);
                startActivity(intent);
                           }


        });
        addshoesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
            }
        });

        updateshoesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Update.class);
                startActivity(intent);
            }
        });
        deleteshoesbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Delete.class);
                startActivity(intent);
            }
        });
    }
}