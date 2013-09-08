package com.sabdakosha;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WordListAdapter extends ArrayAdapter<WordMeaningPair> {
    private Context context;
    private int itemLayoutResource;
    private ArrayList<WordMeaningPair> wordMeaningList;

    public WordListAdapter(Context context, int itemLayoutResource, ArrayList<WordMeaningPair> wordMeaningList) {
        super(context, itemLayoutResource, wordMeaningList);
        this.context = context;
        this.itemLayoutResource = itemLayoutResource;
        this.wordMeaningList = wordMeaningList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)  {
        WordMeaningPair item = wordMeaningList.get(position);

        Activity activity = ((Activity)context);
        View row = activity.getLayoutInflater().inflate(itemLayoutResource, parent, false);
        TextView wordViewElement = (TextView)row.findViewById(R.id.word);
        TextView meaningViewElement = (TextView)row.findViewById(R.id.meaning);

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "devanagari.ttf");
        wordViewElement.setTypeface(typeface);

        wordViewElement.setText(item.getWord());
        meaningViewElement.setText(item.getMeaning());

        return row;
    }
}
