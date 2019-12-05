package com.example.wfath.myapp;

public class RecipeDbSchema {
    public static final class RecipeTable{
        public static final String NAME = "recipes";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String RECIPE = "recipe";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
        }
    }
}
