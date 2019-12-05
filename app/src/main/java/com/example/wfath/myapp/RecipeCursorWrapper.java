package com.example.wfath.myapp;

import android.database.Cursor;
import java.util.UUID;
import java.util.Date;
import android.database.CursorWrapper;
import com.example.wfath.myapp.RecipeDbSchema.RecipeTable;

public class RecipeCursorWrapper extends CursorWrapper {

    public RecipeCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Recipe getRecipe(){
        String uuidString =
                getString(getColumnIndex(RecipeTable.Cols.UUID));
        String title =
                getString(getColumnIndex(RecipeTable.Cols.TITLE));
        String info =
                getString(getColumnIndex(RecipeTable.Cols.RECIPE));
        long date =
                getLong(getColumnIndex(RecipeTable.Cols.DATE));
        int isSolved =
                getInt(getColumnIndex(RecipeTable.Cols.SOLVED));

        Recipe recipe = new Recipe(UUID.fromString(uuidString));
        recipe.setTitle(title);
        recipe.setInfo(info);
        recipe.setDate(new Date(date));
        recipe.setSolved(isSolved != 0);

        return recipe;
    }

    }
