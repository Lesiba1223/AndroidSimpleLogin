package com.example.loginapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.TextView



class DashboardActivity : AppCompatActivity() {

    private lateinit var welcomeText: String
    private lateinit var tvWelcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        welcomeText = "Welcome " + getIntent().getStringExtra("Username") + "!";
        tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(welcomeText);

    }
}