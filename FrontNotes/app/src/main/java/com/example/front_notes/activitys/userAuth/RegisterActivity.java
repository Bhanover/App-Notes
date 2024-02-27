package com.example.front_notes.activitys.userAuth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.front_notes.R;
import com.example.front_notes.Server;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private Button userButtonLogin;
    private Button userButtonRegister;
    private EditText userUsernameEditText;
    private EditText userEmailEditText;
    private EditText userPasswordEditText;
    private EditText userRePasswordEditText;
    private Context context = this;
    private RequestQueue queue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initUI();
        setupListeners();

    }
    private void initUI() {
        userButtonLogin = findViewById(R.id.user_button_login);
        userButtonRegister = findViewById(R.id.user_button_register);
        userUsernameEditText = findViewById(R.id.user_username);
        userEmailEditText = findViewById(R.id.user_email);
        userPasswordEditText = findViewById(R.id.user_password);
        userRePasswordEditText = findViewById(R.id.user_re_password);

        queue = Volley.newRequestQueue(context);

    }
    private void setupListeners(){
        userButtonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(context,RegisterActivity.class);
            context.startActivity(intent);
        });
        userButtonRegister.setOnClickListener(v -> startUserRegister());
    }
    private void startUserRegister(){
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("username",userUsernameEditText.getText().toString().trim());
            requestBody.put("email",userEmailEditText.getText().toString().trim());
            requestBody.put("password",userPasswordEditText.getText().toString().trim());
        } catch (JSONException e) {
            // Lanza una excepción en tiempo de ejecución si hay un error al poner los datos en el requestBody.
            throw new RuntimeException(e);
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Server.name + "/api/auth/signout",
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }
}
