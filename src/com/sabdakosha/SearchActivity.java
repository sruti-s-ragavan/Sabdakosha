package com.sabdakosha;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class SearchActivity extends Activity {
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
        ArrayList<WordMeaningPair> matches = dictionary.SearchMatches(searchWord);
        DisplayMatches(matches);
    }

    private void DisplayMatches(ArrayList<WordMeaningPair> entries) {
        ListView matchingEntriesListView = (ListView) findViewById(R.id.meaning_list);
        WordListAdapter adapter = new WordListAdapter(this, R.layout.dictionary_item, entries);
        matchingEntriesListView.setAdapter(adapter);
    }

    private String GetSearchKey() {
        EditText searchKeyBox = (EditText) findViewById(R.id.txt_SearchKey);
        return searchKeyBox.getText().toString();
    }

    private void Alert(String text){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(new String(text.getBytes(Charset.forName("UTF-8"))));
        alertDialog.show();
    }
}
