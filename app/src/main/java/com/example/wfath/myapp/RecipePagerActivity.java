package com.example.wfath.myapp;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class RecipePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private ArrayList<Recipe> mRecipes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewpager);
        setContentView(mViewPager);

        mRecipes = RecipeLab.get(this).getRecipes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Recipe recipe = mRecipes.get(position);
                return RecipeFragment.newInstance(recipe.getId());
            }

            @Override
            public int getCount() {
                return mRecipes.size();
            }
        });
    }

}
