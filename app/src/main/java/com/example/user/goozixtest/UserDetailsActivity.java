package com.example.user.goozixtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.goozixtest.api.ApiService;
import com.example.user.goozixtest.api.RetroClient;
import com.example.user.goozixtest.model.Example;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_PHOTO = "photo";


    public static void start(Context context, Example examples) {
        Intent intent = new Intent(context, UserDetailsActivity.class);

        intent.putExtra(EXTRA_NAME, examples.getLogin());
        intent.putExtra(EXTRA_PHOTO, examples.getAvatarUrl());


        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String avatar = intent.getStringExtra(EXTRA_PHOTO);


        ImageView ivPhoto = findViewById(R.id.ivPhoto);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvFollowing = findViewById(R.id.tvFollowing);
        TextView tvFollowers = findViewById(R.id.tvFollowers);
        TextView tvOrganization = findViewById(R.id.tvOrganization);
        TextView tvAccount = findViewById(R.id.tvAccount);

        Glide.with(this)
                .load(avatar)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.avatar_default_details)
                        .fallback(R.drawable.avatar_default_details)
                        .centerCrop())
                .into(ivPhoto);

        tvName.setText(name);

        ApiService apiService = RetroClient.getApiService();
        // Calling JSON
        Call<Example> call = apiService.getUser(name);

        //Callback wiil be call when responce...
        call.enqueue(new Callback<Example>() {


            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull Response<Example> response) { // onResponce если успешно
                // dialog.dismiss();
                if (response.isSuccessful()) {

                    Example examples = response.body();

                    tvEmail.setText((CharSequence) examples.getEmail());
                    tvFollowing.setText(String.valueOf(examples.getFollowing()));
                    tvFollowers.setText(String.valueOf(examples.getFollowers()));
                    tvOrganization.setText(examples.getCompany());
                    tvAccount.setText(examples.getCreatedAt());


                } else {
                    tvEmail.append("Sorry,  fail...");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {   // onResponce если не успешно
                // dialog.dismiss();
            }
        });
    }
}


