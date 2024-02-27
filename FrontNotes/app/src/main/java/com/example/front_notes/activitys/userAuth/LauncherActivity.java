package com.example.front_notes.activitys.userAuth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.front_notes.MainActivity;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = this.getSharedPreferences("MySharedPreference",MODE_PRIVATE);
        String jwtToken = preferences.getString("jwtToken",null);

        if (jwtToken == null){
            Intent intent = new Intent(this, LoginActivity.class);
            this.startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }
}
