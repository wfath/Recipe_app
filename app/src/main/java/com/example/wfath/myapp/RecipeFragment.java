package com.example.wfath.myapp;
//import androidx.lifecycle.Fr
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
//need both of these for some reason to get the button to work
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;

import android.text.TextWatcher;
import android.text.Editable;

//import android.support.v4.app.Action

import android.view.MenuItem;
import android.view.LayoutInflater;
import android.widget.EditText;

import java.util.UUID;


//must add a RecipeFragment to crimeactivity or all of this is in vain
//give the fragments a place to be heard!!
//#fragmentmovement 2k19
public class RecipeFragment extends Fragment {
    public static final String EXTRA_RECIPE_ID = "com.example.wfath.myapp.recipe_id";
    private static final String DIALOG_DATE = "date";

    private Recipe mRecipe;
    private EditText mRecipeInfo;
    private EditText mTitlefield;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

//    Fragment.onCreate

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID recipeId = (UUID)getArguments().getSerializable(EXTRA_RECIPE_ID);

        mRecipe = RecipeLab.get(getActivity()).getRecipe(recipeId);

        //part fo the Up button comment out
//        setHasOptionsMenu(true);
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_recipe, parent, false);
       //maybe uncomment this
        // setHasOptionsMenu(true);

//        so this is for the up button to go back one view ancestrally
        //but this is a recipe app so I don't know that I really need that so we probably just comment out bc headaches

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            if (NavUtils.getParentActivityName(getActivity()) != null) {
//                //https://stackoverflow.com/questions/18320713/getsupportactionbar-from-inside-of-fragment-actionbarcompat
//                ((AppCompatActivity)getActivity()).getActionBar().setDisplayHomeAsUpEnabled(true);
//            }
//        }

        mRecipeInfo = (EditText)v.findViewById(R.id.recipe_information);
        //TODO::NEED to go throuhg and implemnt all of the stuff for the recipe info
        //TODO::save the information to the JSON, add all of the getters and setters
        //TODO:: go through and do everything like that, might be a bit confusing but we will get this
        //TODO:: young money fresh money lets get it!!
        mRecipeInfo.setText(mRecipe.getInfo());
        mRecipeInfo.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mRecipe.setInfo(c.toString());
            }


            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //intent left blacnk
            }

            public void afterTextChanged(Editable c) {
                //this one too
            }
        });

        mTitlefield = (EditText)v.findViewById(R.id.recipe_title);
        mTitlefield.setText(mRecipe.getTitle());
        mTitlefield.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(
                    CharSequence c, int start, int before, int count) {
                mRecipe.setTitle(c.toString());
            }


            public void beforeTextChanged(
                    CharSequence c, int start, int count, int after) {
                //intent left blacnk
            }

            public void afterTextChanged(Editable c) {
                //this one too
            }
        });
//        This is where the data gets written on the button
        mDateButton = (Button)v.findViewById(R.id.recipe_date);
        mDateButton.setText(mRecipe.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = new DatePickerFragment();
                dialog.show(fm, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.recipe_solved);
        mSolvedCheckBox.setChecked(mRecipe.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mRecipe.setSolved(isChecked);
            }
        });

        return v;
    }

    public static RecipeFragment newInstance(UUID recipeId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_RECIPE_ID, recipeId);

        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onPause(){
        super.onPause();
        RecipeLab.get(getActivity()).saveRecipes();
    }

    //plz stop breaking my program
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch(item.getItemId()){
//            case android.R.id.home:
//                if(NavUtils.getParentActivityName(getActivity()) != null){
//                    NavUtils.navigateUpFromSameTask(getActivity());
//                }
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
