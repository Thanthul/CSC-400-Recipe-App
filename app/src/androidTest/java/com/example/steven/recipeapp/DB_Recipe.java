package com.example.steven.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Recipe extends SQLiteOpenHelper {
    final static String DB_NAME = "db_recipe";

    public DB_Recipe(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS recipe(recipe_id INTEGER PRIMARY KEY AUTOINCREMENT,course_id INTEGER FOREIGN KEY, food_category_id INTEGER FOREIGN KEY, recipe_name TEXT, recipe_description TEXT, prep_time TEXT, cook_time TEXT)";
        db.execSQL(sql);

        sql ="CREATE TABLE IF NOT EXISTS category(food_category_id INTEGER PRIMARY KEY AUTOINCREMENT, food_category_name TEXT)" ;
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS course(course_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS ingredients(ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT, ingredient_name TEXT, ingredient_core TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS quantity(quantity_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_id INTEGER FOREIGN KEY, ingredient_id INTEGER FOREIGN KEY, ingredient_measurement_id INTEGER FOREIGN KEY, ingredient_quantity REAL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS measurement(measurement_id INTEGER PRIMARY KEY AUTOINCREMENT, measurement_name TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS steps(step_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_id INTEGER FOREIGN KEY, step_number INTEGER, step_description TEXT)";
        db.execSQL(sql);

        ContentValues values = new ContentValues();
        values.put("recipe_id", "1");
        values.put("course_id", "1");
        values.put("food_category_id", "1");
        values.put("recipe_name", "Scrambled Eggs");
        values.put("recipe_description", "A simple scrambled eggs recipe");
        values.put("prep_time", "1 Minute");
        values.put("cook_time", "4 Minutes");//Finish more recipes
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipe");//Figure out if I need to drop everything
        onCreate(db);
    }
}
