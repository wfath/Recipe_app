package com.example.wfath.myapp;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RecipeLab {
    private List<Recipe> mRecipes;

    private static RecipeLab sRecipeLab;
    private Context mAppContext;

    private RecipeLab(Context appContext) {
        mAppContext = appContext;
        mRecipes = new ArrayList<Recipe>();

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

    public List<Recipe> getRecipes() {


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
