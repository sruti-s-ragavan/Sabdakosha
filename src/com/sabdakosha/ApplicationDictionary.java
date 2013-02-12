package com.sabdakosha;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class ApplicationDictionary {
    private Context context;
    DatabaseHelper dbHelper;


    public ApplicationDictionary(Context context) {
        this.context = context;
    }

    public ArrayList<String> SearchMatches(String searchString){
        dbHelper.openDataBase();
        SQLiteDatabase database = dbHelper.getDatabase();
        String[] args = new String[]{};
//        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
//        String searchTransliteration = "Transliteration like '%" + searchString + "%'";
//        String searchWord = "Meaning like '%" + searchString + "%'";
//        queryBuilder.appendWhereEscapeString(searchTransliteration + " OR " + searchWord);
        Cursor dbCursor = database.rawQuery("select * from Dictionary LIMIT 5", args);
        ArrayList<String> results = ProcessSearchResults(dbCursor);
        dbHelper.close();
        return results;

    }

    private ArrayList<String> ProcessSearchResults(Cursor dbCursor) {

        ArrayList<String> results = new ArrayList<String>();
        dbCursor.moveToFirst();
        int  noOfColumns = dbCursor.getColumnCount();
        while(!dbCursor.isAfterLast()) {
            String resultString = "";
            for(int i=0; i<noOfColumns; i++, resultString+=" "){
                String stringVal = dbCursor.getString(i);
                resultString = resultString.concat(stringVal);
            }
            results.add(resultString);
            dbCursor.moveToNext();
        }
        return results;
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
