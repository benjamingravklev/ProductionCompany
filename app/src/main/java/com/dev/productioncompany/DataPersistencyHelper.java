package com.dev.productioncompany;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dev.productioncompany.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

public class DataPersistencyHelper {
    public static Context context;

    public static void storeData (List<UserModel> list) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        String json = new Gson().toJson(list);
        editor.putString("list",json);
        editor.commit();
    }

    public static List<UserModel> loadData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String json = sp.getString("list",null);
        if (json!=null) {
            Type type = new TypeToken<List<UserModel>>(){}.getType();
            return  new Gson().fromJson(json,type);
        } else {
            List<UserModel> list = new ArrayList<>();
            list.add(new UserModel("aaa", "aaa@123"));
            list.add(new UserModel("bbb", "bbb@123"));
            list.add(new UserModel("ccc", "ccc@123"));
            list.add(new UserModel("ddd", "ddd@123"));
            list.add(new UserModel("eee", "eee@123"));
            return list;
        }
    }

}
