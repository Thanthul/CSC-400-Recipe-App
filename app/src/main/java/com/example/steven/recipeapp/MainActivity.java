package com.example.steven.recipeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {
    protected ListView lv;
    protected ListAdapter adapter;
    SQLiteDatabase db;
    Cursor cursor;
    EditText et_db;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        db = (new DB_Recipe(this)).getWritableDatabase();
        lv = (ListView) findViewById(R.id.lv);
        et_db = (EditText) findViewById(R.id.et);


        try {
            cursor = db.rawQuery("SELECT * FROM recipe ORDER BY recipe_name ASC", null);
            adapter = new SimpleCursorAdapter(this, R.layout.isi_lv, cursor,
                    new String[]{"name", "ingredients"}, new int[]{
                    R.id.tvname, R.id.tvingr});
            lv.setAdapter(adapter);
            lv.setTextFilterEnabled(true);
            lv.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    detail(position);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public void search_db(View v) {
        String edit_db = et_db.getText().toString();
        if (!edit_db.equals("")) {
            try {
                cursor = db.rawQuery("SELECT * FROM recipe WHERE recipe_name LIKE ? ",
                        new String[] { "%" + edit_db + "%" });
                adapter = new SimpleCursorAdapter(
                        this,
                        R.layout.isi_lv,
                        cursor,
                        new String[] { "recipe", "ingr"},
                        new int[] { R.id.tvname, R.id.tvingr});
                if (adapter.getCount() == 0) {
                    Toast.makeText(
                            this,
                            "No results found " + edit_db
                                    + "", Toast.LENGTH_SHORT).show();
                } else {
                    lv.setAdapter(adapter);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                cursor = db.rawQuery("SELECT * FROM recipe ORDER BY recipe_name ASC",
                        null);
                adapter = new SimpleCursorAdapter(
                        this,
                        R.layout.isi_lv,
                        cursor,
                        new String[] { "recipe", "ingr"},
                        new int[] { R.id.tvname, R.id.tvingr});
                lv.setAdapter(adapter);
                lv.setTextFilterEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void detail(int position) {
        String _id = "";
        String recipe = "";
        String ingr = "";
        String steps = "";
        if (cursor.moveToFirst()) {
            cursor.moveToPosition(position);
            recipe = cursor.getString(cursor.getColumnIndex("recipe_name"));
            ingr = cursor.getString(cursor.getColumnIndex("ingredient_name"));
            steps = cursor.getString(cursor.getColumnIndex("step_description"));
        }

        Intent iIntent = new Intent(this, DB_Parse.class);
        iIntent.putExtra("dataName", recipe);
        iIntent.putExtra("dataIngredient", ingr);
        iIntent.putExtra("dataSteps", steps);
        setResult(RESULT_OK, iIntent);
        startActivityForResult(iIntent, 99);
    }


}
