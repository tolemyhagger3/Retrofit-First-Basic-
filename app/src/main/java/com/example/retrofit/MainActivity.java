package com.example.retrofit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofit.adapter.ApiService;
import com.example.retrofit.adapter.RecyclerAdapter;
import com.example.retrofit.adapter.RetrofitClient;
import com.example.retrofit.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private ProgressBar progress;

    RecyclerView recycleView;

    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter imageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init (){
        rcv = (RecyclerView) findViewById(R.id.recycler);
        progress = (ProgressBar) findViewById(R.id.progress);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        rcv.setItemAnimator(new DefaultItemAnimator());
        getImageList();


    }

    public void getImageList(){

        //Creating an object of our api interface
        ApiService api = RetrofitClient.getApiService();

        /**
         * Calling JSON
         */
        Call<List<Model>> call = api.getImageList();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<List<Model>>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                //Dismiss Dialog


                if (response.isSuccessful()) {
                    /**
                     * Got Successfully
                     */
                    progress.setVisibility(View.GONE);

                    List<Model> imageModelList = response.body();

                    if(imageModelList.size()>0){
                        imageAdapter = new RecyclerAdapter(MainActivity.this,imageModelList);
                        rcv.setAdapter(imageAdapter);
                    }else {
                        Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                    }

                    Log.d("response","success");

                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

                progress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
