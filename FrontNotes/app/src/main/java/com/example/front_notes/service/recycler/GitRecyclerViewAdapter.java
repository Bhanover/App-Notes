package com.example.front_notes.service.recycler;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front_notes.R;
import com.example.front_notes.models.GitData;
import com.example.front_notes.models.NotesData;

import java.util.List;

public class GitRecyclerViewAdapter extends RecyclerView.Adapter<GitViewHolder> {
    private List<GitData> gitAllData;
    // Referencia a la actividad donde se usa este adaptador
    private Activity activity;

    public GitRecyclerViewAdapter(List<GitData> gitAllData, Activity activity) {
        this.gitAllData = gitAllData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public GitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.git_view_holder,parent,false);

        return new GitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitViewHolder holder, int position) {

        GitData dataInPositionToBeRendered = gitAllData.get(position);
        holder.showData(dataInPositionToBeRendered,activity);

    }

    @Override
    public int getItemCount() {
        return gitAllData.size();
    }
}
