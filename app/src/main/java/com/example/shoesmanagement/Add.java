package com.example.shoesmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesmanagement.sampledata.DataPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add extends AppCompatActivity {

    private EditText editTextShoeBrand;
    private EditText editTextModel;
    private EditText editTextSize;
    private EditText editTextColor;
    private EditText editTextPrice;
    private Button buttonApply;

    private Button buttonBacktoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_page);

        editTextShoeBrand = findViewById(R.id.editTextBrand);
        editTextColor = findViewById(R.id.editTextColor);
        editTextModel = findViewById(R.id.editTextModel);
        editTextSize = findViewById(R.id.editTextSize);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonApply = findViewById(R.id.buttonApplyandAddShoe);
        buttonBacktoMenu = findViewById(R.id.buttonBacktoMenu);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shoeBrand = editTextShoeBrand.getText().toString();
                String shoeModel = editTextModel.getText().toString();
                String shoeSize = editTextSize.getText().toString();
                String shoeColor = editTextColor.getText().toString();
                String shoePrice = editTextPrice.getText().toString();

                // Create a new DataPost object with these details
                DataPost newData = new DataPost();
                newData.setShoeBrand(shoeBrand);
                newData.setShoeModel(shoeModel);
                newData.setShoeSize(Integer.parseInt(shoeSize));
                newData.setShoeColour(shoeColor);
                newData.setShoePrice(Double.parseDouble(shoePrice));

                // Send the DataPost object to your server
                MyApiCall apiService = RetrofitClientInstance.getRetrofitInstance().create(MyApiCall.class);
                Call<DataPost> call = apiService.addShoe(newData);
                call.enqueue(new Callback<DataPost>() {
                    @Override
                    public void onResponse(Call<DataPost> call, Response<DataPost> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Add.this, "Shoe added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Add.this, "Failed to add shoe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataPost> call, Throwable t) {
                            Toast.makeText(Add.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonBacktoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}