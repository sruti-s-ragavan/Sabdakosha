package com.sabdakosha;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SearchMeaning extends Activity {
    ApplicationDictionary dictionary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Initialize();
    }

    private void Initialize() {
        dictionary = new ApplicationDictionary(this.getApplicationContext());
        dictionary.Init();
    }

    public void onSearchWord(View view) {
        String searchWord = GetSearchKey();
//        ArrayList<String> matches = dictionary.GetEntriesContaining(searchWord);
//        DisplayMatches(matches);
        int count = dictionary.SearchMatches(searchWord);
        Alert(searchWord + count);
    }

    private void DisplayMatches(ArrayList<String> entries) {
        ListView matchingEntriesListView = (ListView) findViewById(R.id.meaning_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, entries);
        matchingEntriesListView.setAdapter(adapter);

    }

    private String GetSearchKey() {
        EditText searchKeyBox = (EditText) findViewById(R.id.txt_SearchKey);
        return searchKeyBox.getText().toString();

    }

    private void Alert(String text){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(text);
        alertDialog.show();
    }
}
