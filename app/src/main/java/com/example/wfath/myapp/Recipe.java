package com.example.wfath.myapp;

import java.util.UUID;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Recipe extends Object {

    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_INFO = "info";
    private static final String JSON_FAVORITE = "favorite";
    private static final String JSON_DATE = "date";

    private UUID mId;
    private String mTitle;
    private String mInformation;
    private Date mDate;
    private boolean mSolved;

    public Recipe(){
        this(UUID.randomUUID());
//        mId = UUID.randomUUID();
//        mDate = new Date();
    }
    public Recipe(UUID id){
        mId = id;
        mDate = new Date();
    }

    //implement info in here
    public Recipe(JSONObject json) throws JSONException{
        mId = UUID.fromString(json.getString(JSON_ID));
        if ( json.has(JSON_TITLE)) {
            mTitle = json.getString(JSON_TITLE);
        }
        mInformation = json.getString(JSON_INFO);
        mSolved = json.getBoolean(JSON_FAVORITE);
        mDate = new Date(json.getLong(JSON_DATE));
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_INFO, mInformation);
        json.put(JSON_FAVORITE, mSolved);
        json.put(JSON_DATE, mDate.getTime());
        return json;
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

    public String getInfo(){
        return mInformation;
    }

    public void setInfo(String info){
        mInformation = info;
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

