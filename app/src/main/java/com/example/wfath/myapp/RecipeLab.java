package com.example.wfath.myapp;
import android.content.ContentValues;
import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RecipeLab {
    private static final String TAG = "RecipeLab";
//    private static final String FILENAME = "crimes.json";
    private Context mContext;
    private SQLiteDatabase mDatabase;

//    private ArrayList<Recipe> mRecipes;
//    private RecipeJSONSerializer mSerializer;

    private static RecipeLab sRecipeLab;
//    private Context mAppContext;

    private RecipeLab(Context context) {
//        mAppContext = appContext;
//        mRecipes = new ArrayList<Recipe>();
//        mSerializer = new RecipeJSONSerializer(mAppContext, FILENAME);
        mContext = context.getApplicationContext();
        mDatabase = new RecipeBaseHelper(mContext).getWritableDatabase();

//        try{
//            mRecipes = mSerializer.loadRecipes();
//        } catch (Exception e){
//            mRecipes = new ArrayList<Recipe>();
//            Log.e(TAG, "Error loading recipes: ", e);
//        }

        //puts 100 crimes on the screen
//        for (int i = 0; i < 100; i++){
//            Recipe c = new Recipe();
//            c.setTitle("Recipe #" + i);
//            c.setSolved(i % 2 == 0);
//            mRecipes.add(c);
//        }
    }
    private RecipeCursorWrapper queryRecipes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                RecipeDbSchema.RecipeTable.NAME,
                null, //cols - null selects all cols
                whereClause,
                whereArgs,
                null, //groupby
                null, //having
                null //order by
        );
        return new RecipeCursorWrapper(cursor);
    }

    public void updateRecipe(Recipe recipe){
        String uuidString = recipe.getId().toString();
        ContentValues values = getContentValues(recipe);

        mDatabase.update(RecipeDbSchema.RecipeTable.NAME, values,
                RecipeDbSchema.RecipeTable.Cols.UUID + " = ?",
                new String[] {uuidString});
    }

    public static RecipeLab get(Context c) {
        if(sRecipeLab == null) {
            sRecipeLab = new RecipeLab(c.getApplicationContext());
        }
        return sRecipeLab;
    }

    public void addRecipe(Recipe r) {
//        mRecipes.add(r);
        ContentValues values = getContentValues(r);
        mDatabase.insert(RecipeDbSchema.RecipeTable.NAME, null, values);
    }

//    public boolean saveRecipes(){
//        try{
//            mSerializer.saveRecipes(mRecipes);
//            Log.d(TAG, "crimes saved to file");
//            return true;
//        } catch (Exception e){
//            Log.e(TAG, "Error saving crimes: ", e);
//            return false;
//        }
//    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        RecipeCursorWrapper cursor = queryRecipes(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                recipes.add(cursor.getRecipe());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return recipes;

    }

    private static ContentValues getContentValues(Recipe recipe){
        ContentValues values = new ContentValues();

        values.put(RecipeDbSchema.RecipeTable.Cols.UUID, recipe.getId().toString());
        values.put(RecipeDbSchema.RecipeTable.Cols.TITLE, recipe.getTitle());
        values.put(RecipeDbSchema.RecipeTable.Cols.RECIPE, recipe.getInfo());
        values.put(RecipeDbSchema.RecipeTable.Cols.DATE, recipe.getDate().getTime());
        values.put(RecipeDbSchema.RecipeTable.Cols.SOLVED, recipe.isSolved() ? 1 : 0);

        return values;
    }

    public Recipe getRecipe(UUID id) {
        RecipeCursorWrapper cursor = queryRecipes(
                RecipeDbSchema.RecipeTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRecipe();
        } finally {
            cursor.close();
        }
    }

}
