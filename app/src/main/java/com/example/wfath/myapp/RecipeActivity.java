package com.example.wfath.myapp;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import java.util.UUID;


//singlFragmentActivity
public class RecipeActivity extends SingleFragmentActivity {

    @Override
    protected  Fragment createFragment(){

        UUID recipeId = (UUID)getIntent().getSerializableExtra(RecipeFragment.EXTRA_RECIPE_ID);
        return RecipeFragment.newInstance(recipeId);
    }
}
