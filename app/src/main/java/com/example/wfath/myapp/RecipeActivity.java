package com.example.wfath.myapp;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class RecipeActivity extends SingleFragmentActivity {

    @Override
    protected  Fragment createFragment(){
        return new RecipeFragment();
    }
}
