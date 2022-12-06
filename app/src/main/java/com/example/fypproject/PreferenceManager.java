package com.example.fypproject;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private final SharedPreferences storeSharedPreferences;

    public PreferenceManager(Context context){

        storeSharedPreferences = context.getSharedPreferences(Constants.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);

    }

    public void putBoolean(String key, Boolean value){
        SharedPreferences.Editor editor = storeSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean getBoolean(String key){
        return storeSharedPreferences.getBoolean(key, false);
    }

    public void putString(String key, String value){
        SharedPreferences.Editor editor = storeSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key){
        return storeSharedPreferences.getString(key, null);
    }

    public void clear(){
        SharedPreferences.Editor editor = storeSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
