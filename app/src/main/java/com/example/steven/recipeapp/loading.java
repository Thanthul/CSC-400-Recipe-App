package com.example.steven.recipeapp;


import com.example.steven.recipeapp.task.TaskFinishedListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class loading extends Activity implements TaskFinishedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Show the splash screen
        setContentView(R.layout.loading);
        // Find the progress bar
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        // Start your loading
        new task(progressBar, this).execute("");
    }


    @Override
    public void onTaskFinished() {
        completeSplash();
    }

    private void completeSplash(){
        startApp();
        finish();
    }

    private void startApp() {
        Intent intent = new Intent(loading.this, MainActivity.class);
        startActivity(intent);
    }
}