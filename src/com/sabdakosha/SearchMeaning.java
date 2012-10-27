package com.sabdakosha;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchMeaning extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onSearchWord(View view) {
        String searchWord = GetSearchKey();
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(searchWord);
        dialog.show();
    }

    private String GetSearchKey() {
        EditText searchKeyBox = (EditText)findViewById(R.id.txt_SearchKey);
        return searchKeyBox.getText().toString();
    }
}
