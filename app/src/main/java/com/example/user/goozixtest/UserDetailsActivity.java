package com.example.user.goozixtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.goozixtest.model.Example;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NAME = "name";
    private static final String EXTRA_PHOTO = "photo";
    private static final String EXTRA_EMAIL = "email";
    private static final String EXTRA_FOLLOWING = "following";
    private static final String EXTRA_FOLLOWERS = "followers";
    private static final String EXTRA_ORGANIZATION = "organization";
    private static final String EXTRA_DATE = "date";

    public static void start(Context context, Example examples) {
        Intent intent = new Intent(context, UserDetailsActivity.class);

        intent.putExtra(EXTRA_NAME, examples.getLogin());
        intent.putExtra(EXTRA_PHOTO, examples.getAvatarUrl());
       intent.putExtra(EXTRA_FOLLOWING, examples.getFollowing());
      intent.putExtra(EXTRA_FOLLOWERS, examples.getFollowers());
       intent.putExtra(EXTRA_ORGANIZATION, examples.getCompany());
       intent.putExtra(EXTRA_DATE, examples.getStarredUrl());

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        String avatar = intent.getStringExtra(EXTRA_PHOTO);
        String following = intent.getStringExtra(EXTRA_FOLLOWING);
       String followers = intent.getStringExtra(EXTRA_FOLLOWERS);
        String organization = intent.getStringExtra(EXTRA_ORGANIZATION);
        String date = intent.getStringExtra(EXTRA_DATE);

        ImageView ivPhoto = findViewById(R.id.ivPhoto);
        TextView tvName = findViewById(R.id.tvName);
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
       tvFollowing.setText(following);
      tvFollowers.setText(followers);
      tvOrganization.setText(organization);
      tvAccount.setText(date);
    }
}
