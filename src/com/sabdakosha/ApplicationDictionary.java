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

    public ArrayList<String> SearchMatches(String searchString){
        dbHelper.openDataBase();
        SQLiteDatabase database = dbHelper.getDatabase();
        String query = getQuery(searchString);
        Cursor dbCursor = database.rawQuery(query, new String[]{});
        ArrayList<String> results = ProcessSearchResults(dbCursor);
        dbHelper.close();
        return results;

    }

    private String getQuery(String searchString) {
        String searchTransliteration = "Transliteration like '%" + searchString + "%'";
        String searchWord = "Meaning like '%" + searchString + "%'";
        String whereClause = searchTransliteration + " OR " + searchWord;
        return "select * from Dictionary WHERE " + whereClause;
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
