package com.example.user.goozixtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.goozixtest.model.Example;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private static List<Example> example;


    private LayoutInflater inflater;

    static Context context;

    private OnItemClickListener clickListener;

    private RequestOptions imageOption;

    public UserAdapter(Context context, List<Example> example) {

        this.example = example;
        this.context = context;
        this.clickListener =clickListener;
        this.inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.imageOption = new RequestOptions()
                .placeholder(R.drawable.avatar_default_list)
                .fallback(R.drawable.avatar_default_list)
                .centerCrop();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.list_item_user, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Example examples = example.get(position);

        Glide.with(context)
                .load(examples.getAvatarUrl())
                .apply(imageOption)
                .into(holder.photoView);
        holder.login.setText(examples.getLogin());
        holder.id.setText(Integer.toString(examples.getId()));
    }

    @Override
    public int getItemCount() {
        return example.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView photoView;
         TextView login;
         TextView id;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
            photoView = itemView.findViewById(R.id.ivAvatar);
            login = itemView.findViewById(R.id.tvLogin);
            id = itemView.findViewById(R.id.tvId);
        }

    @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            Example examples = example.get(position);
            UserDetailsActivity.start(context, examples);
        }
    }

    }

}