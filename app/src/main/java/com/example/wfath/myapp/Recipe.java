package com.example.wfath.myapp;

import java.util.UUID;
import java.util.Date;

public class Recipe extends Object {
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Recipe(){

        mId = UUID.randomUUID();
        mDate = new Date();
    }

    @Override
    public String toString(){
        return mTitle;
    }

    public UUID getId(){

        return mId;
    }

    public String getTitle(){

        return mTitle;
    }

    public void setTitle(String title){

        mTitle = title;
    }

    public Date getDate(){
        return mDate;
    }

    public void setDate(Date date){
        mDate = date;
    }

    public boolean isSolved(){
        return mSolved;
    }

    public void setSolved(boolean solved){
        mSolved = solved;
    }
}

