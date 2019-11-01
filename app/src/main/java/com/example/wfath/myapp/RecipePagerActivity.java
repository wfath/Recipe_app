package com.example.wfath.myapp;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import androidx.viewpager.widget.ViewPager;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.UUID;

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

        //setonpagelistener is deprecated
        //addonpage... can add more than one listenere at once
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Recipe recipe = mRecipes.get(position);
                if(recipe.getTitle() != null){
                    setTitle(recipe.getTitle());
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID recipeId = (UUID)getIntent().getSerializableExtra(RecipeFragment.EXTRA_RECIPE_ID);
        for(int i = 0; i < mRecipes.size(); i++){
            if(mRecipes.get(i).getId().equals(recipeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

}
