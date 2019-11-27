package com.example.wfath.myapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeJSONSerializer {
    private Context mContext;
    private String mFilename;

    public RecipeJSONSerializer(Context c, String f){
        mContext = c;
        mFilename = f;
    }

    public ArrayList<Recipe> loadRecipes() throws IOException, JSONException{
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        BufferedReader reader = null;
        try{
            //open and read the file into stringbuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                //line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            //pare the json using jsonTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            //build the array of recipes
            for(int i = 0; i < array.length(); i++){
                recipes.add(new Recipe(array.getJSONObject(i)));
            }
        }catch (FileNotFoundException e){
            //ignore this one; it happens when starting fresh
        } finally {
            if(reader != null)reader.close();
        }
        return recipes;
    }

    public void saveRecipes(ArrayList<Recipe> recipes)
        throws JSONException, IOException{
        //build an array in json
        JSONArray array = new JSONArray();
        for(Recipe r: recipes)
            //we write the toJSON thing
            array.put(r.toJSON());
        //write to disk
        Writer writer = null;
        try{
            OutputStream out = mContext.openFileOutput(mFilename,
                    Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        }finally {
            if(writer != null)
                writer.close();
        }

    }

}
