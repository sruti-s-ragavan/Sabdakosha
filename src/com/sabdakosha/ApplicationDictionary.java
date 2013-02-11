package com.sabdakosha;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationDictionary {
    private Context context;
    DatabaseHelper dbHelper;

    public ApplicationDictionary(Context context) {
        this.context = context;
    }

    public int SearchMatches(String searchString){
        dbHelper.openDataBase();
        SQLiteDatabase database = dbHelper.getDatabase();
        String[] args = new String[]{};
        Cursor dbCursor = database.rawQuery("select * from Dictionary", args);
        dbCursor.moveToFirst();
        dbHelper.close();
        return dbCursor.getCount();
    }

    public void Init(){
        dbHelper = new DatabaseHelper(context);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
