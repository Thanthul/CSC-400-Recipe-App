package com.example.steven.recipeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Recipe extends SQLiteOpenHelper {
    final static String DB_NAME = "DB_Recipe";

    public DB_Recipe(Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS recipe(_id INTEGER PRIMARY KEY AUTOINCREMENT,course_id INTEGER, food_category_id INTEGER, recipe_name TEXT, recipe_description TEXT, prep_time TEXT, cook_time TEXT,FOREIGN KEY(course_id) REFERENCES course (_id),FOREIGN KEY(food_category_id) REFERENCES category (_id))";
        db.execSQL(sql);

        sql ="CREATE TABLE IF NOT EXISTS category(_id INTEGER PRIMARY KEY AUTOINCREMENT, food_category_name TEXT)" ;
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS course(_id INTEGER PRIMARY KEY AUTOINCREMENT, course_name TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS ingredients(_id INTEGER PRIMARY KEY AUTOINCREMENT, ingredient_name TEXT, ingredient_core INTEGER)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS quantity(_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_id INTEGER, ingredient_id INTEGER, ingredient_measurement_id INTEGER, ingredient_quantity REAL, FOREIGN KEY(recipe_id) REFERENCES recipe (_id), FOREIGN KEY(ingredient_id) REFERENCES ingredients (_id),FOREIGN KEY(ingredient_measurement_id) REFERENCES measurement(_id) )";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS measurement(_id INTEGER PRIMARY KEY AUTOINCREMENT, measurement_name TEXT)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS steps(_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_id INTEGER, step_number INTEGER, step_description TEXT, FOREIGN KEY (recipe_id) REFERENCES recipe(_id))";
        db.execSQL(sql);

        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        ContentValues values3 = new ContentValues();
        ContentValues values4 = new ContentValues();
        ContentValues values5 = new ContentValues();
        ContentValues values6 = new ContentValues();
        ContentValues values7 = new ContentValues();

        values1.put("_id", "1");
        values1.put("course_id", "1");
        values1.put("food_category_id", "1");
        values1.put("recipe_name", "Scrambled Eggs");
        values1.put("recipe_description", "A simple scrambled eggs recipe");
        values1.put("prep_time", "1 Minute");
        values1.put("cook_time", "4 Minutes");//Finish more recipes
        db.insert("recipe", "_id", values1);

        values2.put("_id", "1");
        values2.put("food_category_name", "Meat");
        db.insert("category", "_id", values2);

        values3.put("_id", "1");
        values3.put("course_name", "Breakfast");
        db.insert("course", "_id", values3);

        values4.put("_id", "1");
        values4.put("ingredient_name", "Eggs");
        values4.put("ingredient_core", "1");
        db.insert("ingredients", "_id", values4);

        values4.put("_id", "2");
        values4.put("ingredient_name", "Milk");
        values4.put("ingredient_core", "1");
        db.insert("ingredients", "_id", values4);

        values4.put("_id", "3");
        values4.put("ingredient_name", "Salt");
        values4.put("ingredient_core", "0");
        db.insert("ingredients", "_id", values4);

        values4.put("_id", "4");
        values4.put("ingredient_name", "Pepper");
        values4.put("ingredient_core", "0");
        db.insert("ingredients", "_id", values4);

        values4.put("_id", "5");
        values4.put("ingredient_name", "Butter");
        values4.put("ingredient_core", "1");
        db.insert("ingredients", "_id", values4);

        values5.put("_id", "1");
        values5.put("recipe_id", "1");
        values5.put("ingredient_id", "1");
        values5.put("ingredient_measurement_id", "1");
        values5.put("ingredient_quantity", "4");
        db.insert("quantity", "_id", values5);

        values5.put("_id", "2");
        values5.put("recipe_id", "1");
        values5.put("ingredient_id", "2");
        values5.put("ingredient_measurement_id", "2");
        values5.put("ingredient_quantity", ".25");
        db.insert("quantity", "_id", values5);

        values5.put("_id", "3");
        values5.put("recipe_id", "1");
        values5.put("ingredient_id", "3");
        values5.put("ingredient_measurement_id", "3");
        values5.put("ingredient_quantity", "1");
        db.insert("quantity", "_id", values5);

        values5.put("_id", "4");
        values5.put("recipe_id", "1");
        values5.put("ingredient_id", "4");
        values5.put("ingredient_measurement_id", "3");
        values5.put("ingredient_quantity", "1");
        db.insert("quantity", "_id", values5);

        values5.put("_id", "5");
        values5.put("recipe_id", "1");
        values5.put("ingredient_id", "5");
        values5.put("ingredient_measurement_id", "4");
        values5.put("ingredient_quantity", "2");
        db.insert("quantity", "_id", values5);

        values6.put("_id", "1");
        values6.put("measurement_name", "Whole");
        db.insert("measurement", "_id", values6);

        values6.put("_id", "2");
        values6.put("measurement_name", "Cup");
        db.insert("measurement", "_id", values6);

        values6.put("_id", "3");
        values6.put("measurement_name", "Pinch");
        db.insert("measurement", "_id", values6);

        values6.put("_id", "4");
        values6.put("measurement_name", "Tsp.");
        db.insert("measurement", "_id", values6);

        values7.put("_id", "1");
        values7.put("recipe_id", "1");
        values7.put("step_number", "1");
        values7.put("step_description", "Beat the eggs, milk, salt and pepper in a medium bowl until blended");
        db.insert("steps", "_id", values7);

        values7.put("_id", "2");
        values7.put("recipe_id", "1");
        values7.put("step_number", "2");
        values7.put("step_description", "Heat butter in large nonstick skillet over medium heat until hot. Pour in egg mixture. As eggs begin to set, Gently pull the eggs across the pan with a spatula, forming large soft curds.");
        db.insert("steps", "_id", values7);

        values7.put("_id", "3");
        values7.put("recipe_id", "1");
        values7.put("step_number", "3");
        values7.put("step_description", "Continue cooking – pulling, lifting and folding eggs – until thickened and no visible liquid egg remains. Do not stir constantly. Remove from heat. Serve immediately.");
        db.insert("steps", "_id", values7);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipe");
        db.execSQL("DROP TABLE IF EXISTS category");
        db.execSQL("DROP TABLE IF EXISTS course");
        db.execSQL("DROP TABLE IF EXISTS ingredients");
        db.execSQL("DROP TABLE IF EXISTS quantity");
        db.execSQL("DROP TABLE IF EXISTS measurement");
        db.execSQL("DROP TABLE IF EXISTS steps");
        onCreate(db);
    }
}
