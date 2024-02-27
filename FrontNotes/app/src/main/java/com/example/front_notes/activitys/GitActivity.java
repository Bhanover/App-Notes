package com.example.front_notes.activitys;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.front_notes.R;
import com.example.front_notes.models.GitData;
import com.example.front_notes.service.recycler.GitRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_git);
        List<GitData> gitDataArrayList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        JsonArrayRequest request = new JsonArrayRequest(
               Request.Method.GET,
               "https://raw.githubusercontent.com/bilrazor/DWES/main/resources/catalog.json",
               null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       for(int i = 0 ; i < response.length();i++){
                           try {
                               JSONObject gitData = response.getJSONObject(i);
                               GitData data = new GitData(gitData);
                               gitDataArrayList.add(data);
                           } catch (JSONException e) {
                               throw new RuntimeException(e);
                           }

                       }
                       GitRecyclerViewAdapter adapter = new GitRecyclerViewAdapter(gitDataArrayList,GitActivity.this);
                       recyclerView.setAdapter(adapter);
                       recyclerView.setLayoutManager(new LinearLayoutManager(GitActivity.this));
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
       );
// Crea una nueva instancia de RequestQueue para manejar las solicitudes de red con Volley.
        RequestQueue queue = Volley.newRequestQueue(this);
        // Añade la solicitud creada a la cola de solicitudes para que se ejecute.
        queue.add(request);
    }
}
