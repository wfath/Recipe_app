package com.example.wfath.myapp;
//import androidx.lifecycle.Fr
import androidx.fragment.app.Fragment;


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

import android.view.LayoutInflater;
import android.widget.EditText;


//must add a RecipeFragment to crimeactivity or all of this is in vain
//give the fragments a place to be heard!!
//#fragmentmovement 2k19
public class RecipeFragment extends Fragment {
    private Recipe mRecipe;
    private EditText mTitlefield;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

//    Fragment.onCreate

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mRecipe = new Recipe();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                            Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        mTitlefield = (EditText)v.findViewById(R.id.crime_title);
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
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        mDateButton.setText(mRecipe.getDate().toString());
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                mRecipe.setSolved(isChecked);
            }
        });

        return v;
    }
}
