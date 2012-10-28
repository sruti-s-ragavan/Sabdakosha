package com.sabdakosha;

import android.app.Activity;
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
    private InputStream inputStream;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Initialize();
    }

    private void Initialize() {
        inputStream = getResources().openRawResource(R.raw.dictionary);
        Repository repository = new Repository(inputStream);
        dictionary = new ApplicationDictionary(repository);
    }

    public void onSearchWord(View view) {
        String searchWord = GetSearchKey();
        ArrayList<String> matches = dictionary.GetEntriesContaining(searchWord);
        DisplayMatches(matches);
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
}
