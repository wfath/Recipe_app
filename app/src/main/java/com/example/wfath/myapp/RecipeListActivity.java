package com.example.wfath.myapp;
import androidx.fragment.app.Fragment;


public class RecipeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new RecipeListFragment();
    }
}

