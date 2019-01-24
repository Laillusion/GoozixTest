package com.example.user.goozixtest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.user.goozixtest.api.ApiService;
import com.example.user.goozixtest.api.RetroClient;
import com.example.user.goozixtest.model.Example;
import com.example.user.goozixtest.util.InternetConnection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Example> example;
    UserAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        example = new ArrayList<>();

        recyclerView = findViewById(R.id.users);
        adapter = new UserAdapter(MainActivity.this, example);
        recyclerView.setAdapter(adapter);



       // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btn_search = findViewById(R.id.btnGet);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // Checking internet Conntction
                if (InternetConnection.checkConection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    // Process Dialog for user Interaction
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_json_title));
                    dialog.setMessage(getString(R.string.string_getting_json_message));
                    dialog.show();

                    ApiService apiService = RetroClient.getApiService();
                    // Calling JSON
                    Call<List<Example>> call = apiService.getMyJSON();

                    //Callback wiil be call when responce...
                    call.enqueue(new Callback<List<Example>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Example>> call, @NonNull Response<List<Example>> response) { // onResponce если успешно
                            dialog.dismiss();
                            if (response.isSuccessful()) {

                                example.addAll(response.body());

                                recyclerView.getAdapter().notifyDataSetChanged();


                            } else {
                                Snackbar.make(view, R.string.string_some_thing_vrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Example>> call, @NonNull Throwable t) {   // onResponce если не успешно
                            dialog.dismiss();
                        }
                    });
                } else {
                    Snackbar.make(view, R.string.internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });

    }

}
