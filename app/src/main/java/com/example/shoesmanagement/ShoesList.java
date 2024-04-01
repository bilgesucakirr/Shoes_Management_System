package com.example.shoesmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.shoesmanagement.sampledata.dataModel;

import java.util.List;

public class ShoesList extends AppCompatActivity {

    RecyclerView recyclerView;
    ShoeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shoes);

        recyclerView = findViewById(R.id.shoesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://shoesapi.intalalab.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // instance for interface
        MyApiCall myAPICall = retrofit.create(MyApiCall.class);
        Call<List<dataModel>> call = myAPICall.getShoes();

        call.enqueue(new retrofit2.Callback<List<dataModel>>() {
            @Override
            public void onResponse(Call<List<dataModel>> call, retrofit2.Response<List<dataModel>> response) {
                if (response.code() != 200) {
                    return;
                }

                // Set the adapter for the RecyclerView
                adapter = new ShoeAdapter(response.body());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<dataModel>> call, Throwable t) {
            }
        });
    }

    class ShoeAdapter extends RecyclerView.Adapter<ShoeAdapter.ShoeViewHolder> {

        List<dataModel> shoes;

        ShoeAdapter(List<dataModel> shoes) {
            this.shoes = shoes;
        }

        @Override
        public ShoeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shoe, parent, false);
            return new ShoeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ShoeViewHolder holder, int position) {
            dataModel shoe = shoes.get(position);
            holder.shoeID.setText("Shoe ID: " + String.valueOf(shoe.getShoeId()));
            holder.shoeBrand.setText("Shoe Brand: " + shoe.getShoeBrand());
            holder.shoeModel.setText("Shoe Model: " + shoe.getShoeModel());
            holder.shoeSize.setText("Shoe Size: " + String.valueOf(shoe.getShoeSize()));
            holder.shoeColour.setText("Shoe Colour: " + shoe.getShoeColour());
            holder.shoePrice.setText("Shoe Price: " + String.valueOf(shoe.getShoePrice()));
        }

        @Override
        public int getItemCount() {
            return shoes.size();
        }

        class ShoeViewHolder extends RecyclerView.ViewHolder {

            TextView shoeID;
            TextView shoeBrand;
            TextView shoeModel;
            TextView shoeSize;
            TextView shoeColour;
            TextView shoePrice;

            ShoeViewHolder(View itemView) {
                super(itemView);
                shoeID = itemView.findViewById(R.id.shoeID);
                shoeBrand = itemView.findViewById(R.id.shoeBrand);
                shoeModel = itemView.findViewById(R.id.shoeModel);
                shoeSize = itemView.findViewById(R.id.shoeSize);
                shoeColour = itemView.findViewById(R.id.shoeColour);
                shoePrice = itemView.findViewById(R.id.shoePrice);
            }
        }
    }
}