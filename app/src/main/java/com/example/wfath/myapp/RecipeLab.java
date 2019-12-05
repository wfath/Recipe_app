package com.example.wfath.myapp;
import android.content.Context;
import java.util.ArrayList;
import java.util.UUID;
import android.util.Log;

public class RecipeLab {
    private static final String TAG = "RecipeLab";
    private static final String FILENAME = "crimes.json";

    private ArrayList<Recipe> mRecipes;
    private RecipeJSONSerializer mSerializer;

    private static RecipeLab sRecipeLab;
    private Context mAppContext;

    private RecipeLab(Context appContext) {
        mAppContext = appContext;
//        mRecipes = new ArrayList<Recipe>();
        mSerializer = new RecipeJSONSerializer(mAppContext, FILENAME);

        try{
            mRecipes = mSerializer.loadRecipes();
        } catch (Exception e){
            mRecipes = new ArrayList<Recipe>();
            Log.e(TAG, "Error loading recipes: ", e);
        }

        //puts 100 crimes on the screen
//        for (int i = 0; i < 100; i++){
//            Recipe c = new Recipe();
//            c.setTitle("Recipe #" + i);
//            c.setSolved(i % 2 == 0);
//            mRecipes.add(c);
//        }
    }

    public static RecipeLab get(Context c) {
        if(sRecipeLab == null) {
            sRecipeLab = new RecipeLab(c.getApplicationContext());
        }
        return sRecipeLab;
    }

    public void addRecipe(Recipe r) {
        mRecipes.add(r);
    }

    public void deleteRecipe(Recipe r){
        mRecipes.remove(r);
    }

    public boolean saveRecipes(){
        try{
            mSerializer.saveRecipes(mRecipes);
            Log.d(TAG, "crimes saved to file");
            return true;
        } catch (Exception e){
            Log.e(TAG, "Error saving crimes: ", e);
            return false;
        }
    }

    public ArrayList<Recipe> getRecipes() {


        return mRecipes;
    }

    public Recipe getRecipe(UUID id) {
        for (Recipe c : mRecipes) {
            if (c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }

}
