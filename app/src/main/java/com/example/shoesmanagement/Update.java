package com.example.shoesmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoesmanagement.sampledata.DataPut;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update extends AppCompatActivity {

    private EditText editTextShoeId;
    private EditText editTextShoeBrand;
    private Button buttonUpdate;

    private EditText editTextColor;

    private EditText editTextModel;

    private EditText editTextSize;

    private EditText editTextPrice;

    private Button buttonBacktoMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_page);

        editTextShoeId = findViewById(R.id.editTextShoeID);
        editTextShoeBrand = findViewById(R.id.editTextBrand);
        editTextColor = findViewById(R.id.editTextColor);
        editTextModel = findViewById(R.id.editTextModel);
        editTextSize = findViewById(R.id.editTextSize);
        editTextPrice = findViewById(R.id.editTextPrice);

        buttonUpdate = findViewById(R.id.buttonSaveandUpdateShoe);

        Button buttonBacktoMenu = findViewById(R.id.buttonBacktoMenu);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shoeId = Integer.parseInt(editTextShoeId.getText().toString());
                String newShoeBrand = editTextShoeBrand.getText().toString();
                String newShoeModel = editTextModel.getText().toString();
                int newShoeSize = Integer.parseInt(editTextSize.getText().toString());
                Object newShoePrice = editTextPrice.getText().toString();

                DataPut dataPut = new DataPut();
                dataPut.setShoeBrand(newShoeBrand);
                dataPut.setShoeModel(newShoeModel);
                dataPut.setShoeSize(newShoeSize);
                dataPut.setShoePrice(newShoePrice);
                dataPut.setShoeColour(editTextColor.getText().toString());
                dataPut.setShoeId(shoeId);


                MyApiCall apiService = RetrofitClientInstance.getRetrofitInstance().create(MyApiCall.class);
                Call<DataPut> call = apiService.updateShoe(shoeId, dataPut);
                call.enqueue(new Callback<DataPut>() {
                    @Override
                    public void onResponse(Call<DataPut> call, Response<DataPut> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Update.this, "Shoe updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Update.this, "Failed to update shoe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataPut> call, Throwable t) {
                        Toast.makeText(Update.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        buttonBacktoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}