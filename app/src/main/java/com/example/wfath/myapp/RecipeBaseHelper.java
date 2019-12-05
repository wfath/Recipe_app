package com.example.wfath.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecipeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "recipeBase.db";

    public RecipeBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + RecipeDbSchema.RecipeTable.NAME + "(" +
                "_id interger primary key autoincrement, " +

                RecipeDbSchema.RecipeTable.Cols.UUID + ", " +
                RecipeDbSchema.RecipeTable.Cols.TITLE + ", " +
                RecipeDbSchema.RecipeTable.Cols.RECIPE + ", " +
                RecipeDbSchema.RecipeTable.Cols.DATE + ", " +
                RecipeDbSchema.RecipeTable.Cols.SOLVED +
               ")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
