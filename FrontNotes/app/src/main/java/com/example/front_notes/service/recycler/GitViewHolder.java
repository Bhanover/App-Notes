package com.example.front_notes.service.recycler;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.front_notes.R;
import com.example.front_notes.models.GitData;

public class GitViewHolder extends RecyclerView.ViewHolder {
    private TextView gitName;
    private TextView gitDescription;
    private ImageView gitImage;

    public GitViewHolder(@NonNull View itemView) {
        super(itemView);
        gitName = itemView.findViewById(R.id.gitName);
        gitDescription = itemView.findViewById(R.id.gitDescription);
        gitImage = itemView.findViewById(R.id.gitImage);
    }

    public void showData(GitData data, Activity activity){
        gitName.setText(data.getName());
        gitDescription.setText(data.getDescription());
        Glide.with(activity).load(data.getUrl()).into(gitImage);
    }
}
