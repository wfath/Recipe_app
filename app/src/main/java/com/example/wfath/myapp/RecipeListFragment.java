package com.example.wfath.myapp;
import androidx.fragment.app.ListFragment;


import android.os.Bundle;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.CheckBox;
import android.widget.ListView;
import android.view.View;
import android.app.Application;
import android.content.ContextWrapper;

import android.view.Menu;

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
    private ArrayList<Recipe> mRecipes;
    private ListView mlistView;


    @Override
    public void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.recipes_title);
        mRecipes = RecipeLab.get(getActivity()).getRecipes();

        RecipeAdapter adapter = new RecipeAdapter(mRecipes);
        setListAdapter(adapter);

//        TextView tv = new TextView(getActivity().getApplicationContext());
//        tv.setText("Select State");
//
//        listView = getListView();
//        listView.addHeaderView(tv);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Recipe r = ((RecipeAdapter)getListAdapter()).getItem(position);

        //start up the pager
        Intent i = new Intent(getActivity(), RecipePagerActivity.class);
        i.putExtra(RecipeFragment.EXTRA_RECIPE_ID, r.getId());
        startActivity(i);
    }

    private class RecipeAdapter extends ArrayAdapter<Recipe>{

        public RecipeAdapter(ArrayList<Recipe> recipes){

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

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        super.onCreateOptionsMenu(menu, inflater);
////        menu.clear();
//        inflater.inflate(R.menu.fragment_recipe_list, menu);
//
//    }
}
