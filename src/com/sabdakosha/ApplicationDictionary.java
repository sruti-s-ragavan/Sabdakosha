package com.sabdakosha;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ApplicationDictionary {
    private Context context;
    DatabaseHelper dbHelper;


    public ApplicationDictionary(Context context) {
        this.context = context;
    }

    public ArrayList<WordMeaningPair> SearchMatches(String searchString){
        dbHelper.openDataBase();
        SQLiteDatabase database = dbHelper.getDatabase();
        String query = getQuery(searchString);
        Cursor dbCursor = database.rawQuery(query, new String[]{});
        ArrayList<WordMeaningPair> results = ProcessSearchResults(dbCursor);
        dbHelper.close();
        return results;
    }

    private String getQuery(String searchString) {
        String searchTransliteration = "Transliteration like '%" + searchString + "%'";
        String searchWord = "Meaning like '%" + searchString + "%'";
        String whereClause = searchTransliteration + " OR " + searchWord;
        return "select * from Dictionary WHERE " + whereClause;
    }

    private ArrayList<WordMeaningPair> ProcessSearchResults(Cursor dbCursor) {

        ArrayList<WordMeaningPair> results = new ArrayList<WordMeaningPair>();
        dbCursor.moveToFirst();
        while(!dbCursor.isAfterLast()) {
                String word = dbCursor.getString(dbCursor.getColumnIndex("Word"));
                //String wordAsUtf8 = word.getBytes(Charset.forName("UTF-8")).toString();
                String meaning = dbCursor.getString(dbCursor.getColumnIndex("Meaning"));
                WordMeaningPair wordMeaningPair = new WordMeaningPair(word, meaning);
            results.add(wordMeaningPair);
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
