package com.example.recyclerassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.recyclerassignment.adapters.RecyclerViewAdapter;
import com.example.recyclerassignment.models.Result;
import com.example.recyclerassignment.models.UsersResponse;
import com.example.recyclerassignment.networking.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    ArrayList<Result> results=new ArrayList<>();
    private ArrayAdapter<Result> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerViewAdapter=new RecyclerViewAdapter(this,results);
        recyclerView.setAdapter(recyclerViewAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<UsersResponse> call = service.getSingleUser();
        call.enqueue(new Callback<UsersResponse>() {

            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                if (response.isSuccessful()) {
                    List<Result> tempresults = response.body().getResults();

                    for (int i = 0; i < tempresults.size(); i++) {
                        results.add(tempresults.get(i));
                    }
                    recyclerViewAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                Log.e("", t.getLocalizedMessage());
            }
        });
    }
}