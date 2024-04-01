package com.example.shoesmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delete extends AppCompatActivity {

    private EditText editTextShoeId;
    private Button buttonDelete;

    private Button buttonBacktoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_page);

        editTextShoeId = findViewById(R.id.editTextShoeID);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonBacktoMenu = findViewById(R.id.buttonBacktoMenu);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int shoeId = Integer.parseInt(editTextShoeId.getText().toString());

                MyApiCall apiService = RetrofitClientInstance.getRetrofitInstance().create(MyApiCall.class);
                Call<Void> call = apiService.deleteShoe(shoeId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(Delete.this, "Shoe deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Delete.this, "Failed to delete shoe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Delete.this, "Request failed", Toast.LENGTH_SHORT).show();
                    }


                });
            }
        });

        buttonBacktoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delete.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}