package com.example.wfath.myapp;
import androidx.fragment.app.ListFragment;


import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import java.util.List;
import android.app.Application;
import android.content.ContextWrapper;

import android.view.Menu;

import android.annotation.TargetApi;

import android.app.ActionBar;

import android.util.Log;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import android.content.Intent;

//need both of these for some reason to get the button to work
//import android.widget.RadioGroup.OnCheckedChangeListener;
//commit chapter 11 start 202

public class RecipeListFragment extends ListFragment {
    private static final String TAG = "RecipeListFragment";
    private List<Recipe> mRecipes;
    private ListView mlistView;
    private boolean mSubtitleVisible;
    private EditText mRecipeInfo;
    private EditText mTitlefield;

    @Override
    public void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.recipes_title);
        mRecipes = RecipeLab.get(getActivity()).getRecipes();

        RecipeAdapter adapter = new RecipeAdapter(mRecipes);
        setListAdapter(adapter);


        setRetainInstance(true);
        mSubtitleVisible = false;
        //this is gone be important for trying to change the icon of the app
        //I would like icon to the left of the app name but it's not necessary
        //getSupportActionBar();


//        TextView tv = new TextView(getActivity().getApplicationContext());
//        tv.setText("Select State");
//
//        listView = getListView();
//        listView.addHeaderView(tv);

    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if(mSubtitleVisible) {
                getActivity().getActionBar().setSubtitle(R.string.subtitle);
            }
        }
        return v;
    }

    @TargetApi(11)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_recipe:
                Recipe recipe = new Recipe();
                RecipeLab.get(getActivity()).addRecipe(recipe);
                Intent i = new Intent(getActivity(), RecipePagerActivity.class);
                i.putExtra(RecipeFragment.EXTRA_RECIPE_ID, recipe.getId());
                startActivityForResult(i, 0);

//                RecipeFragment.newInstance(recipe.getId()).setTitleAndInfoFocusable();

                return true;
//            case R.id.menu_item_show_subtitle:
//                if (getActivity().getActionBar().getSubtitle() == null) {
//                    getActivity().getActionBar().setSubtitle(R.string.subtitle);
//                    mSubtitleVisible = true;
//                    item.setTitle(R.string.hide_subtitle);
//                } else {
//                    getActivity().getActionBar().setSubtitle(null);
//                    mSubtitleVisible = false;
//                    item.setTitle(R.string.show_subtitle);
//                }
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Recipe r = ((RecipeAdapter)getListAdapter()).getItem(position);
//        mTitlefield = (EditText)v.findViewById(R.id.recipe_title);
//        mTitlefield.setFocusable(false);
//
//        mRecipeInfo = (EditText)v.findViewById(R.id.recipe_information);
//        mRecipeInfo.setFocusable(false);
        //start up the pager
        Intent i = new Intent(getActivity(), RecipePagerActivity.class);
        i.putExtra(RecipeFragment.EXTRA_RECIPE_ID, r.getId());

//        RecipeFragment.newInstance(r.getId()).setTitleAndInfoNOTFocusable();

        startActivity(i);
    }

    private class RecipeAdapter extends ArrayAdapter<Recipe>{

        public RecipeAdapter(List<Recipe> recipes){

            super(getActivity(), 0 , recipes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_recipe, null);
            }
            Recipe r = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.recipe_list_item_titleTextView);
            titleTextView.setText(r.getTitle());
            TextView dateTextView = (TextView)convertView.findViewById(R.id.recipe_list_item_dateTextView);
            dateTextView.setText(r.getDate().toString());
            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.recipe_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(r.isSolved());

            return convertView;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        ((RecipeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
//        menu.clear();
        inflater.inflate(R.menu.fragment_recipe_list, menu);

        //taking out this dumb subtitle too
//        MenuItem showSubtitle = menu.findItem(R.id.menu_item_show_subtitle);
//        if(mSubtitleVisible && showSubtitle != null) {
//            showSubtitle.setTitle(R.string.hide_subtitle);
//        }

    }

    public void setRecipes(List<Recipe> recipes){
        mRecipes = recipes;
    }
}
