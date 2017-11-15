package com.example.steven.recipeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageSwitcher;

public class DB_Parse extends Activity {
    ImageView Im;
    TextView tv_name, tv_ingr, tv_steps, id;
    int msg_im;
    ImageSwitcher imageSwitcher;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe);

        Intent ident = getIntent();
        msg_im = ident.getIntExtra("dataIM", 0);
        String msg_name = ident.getStringExtra("dataName");
        String msg_ingr = ident.getStringExtra("dataIngredient");
        String msg_steps = ident.getStringExtra("dataSteps");
        Im = (ImageView) findViewById(R.id.iv_detail);
        tv_name = (TextView) findViewById(R.id.tvname);
        tv_ingr = (TextView) findViewById(R.id.tvingr);
        tv_steps = (TextView) findViewById(R.id.tvsteps);
        Im.setImageResource(msg_im);
        tv_name.setText(msg_name);
        tv_ingr.setText(msg_ingr);
        tv_steps.setText(msg_steps);
    }

}
