package com.sabdakosha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class ApplicationDictionary {
    java.util.Dictionary<String, String> dictionary;
    Repository repository;

    public ApplicationDictionary(Repository repo) {
        this.repository = repo;
        dictionary = repository.LoadDictionary();
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
