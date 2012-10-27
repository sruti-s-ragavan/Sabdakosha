package com.sabdakosha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class ApplicationDictionary {
    java.util.Dictionary<String, String> dictionary;

    public void ApplicationDictionary(){
        InitializeDictionary();
    }

    private void InitializeDictionary() {
        dictionary = new Hashtable<String, String>();
        dictionary.put("a", "b");
        dictionary.put("aa", "bb");
        dictionary.put("aa", "bc");
        dictionary.put("c", "c");
    }

    public ArrayList<String> GetEntriesContaining(String searchWord) {
        String pattern = ".*" + searchWord + ".*";
        List<String> keys =  Collections.list(dictionary.keys());
        ArrayList<String> matches = new ArrayList<String>();
        for (String word : keys) {
            if (word.matches(pattern)) {
                matches.add(word);
            }
        }
        return matches;
    }
}
