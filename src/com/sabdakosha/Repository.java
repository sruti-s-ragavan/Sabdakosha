package com.sabdakosha;

import java.util.Dictionary;
import java.util.Hashtable;

public class Repository {
    public Dictionary<String, String> LoadDictionary() {

        Dictionary<String, String> dictionary = new Hashtable<String, String>();
        dictionary.put("a", "b");
        dictionary.put("aa", "bb");
        dictionary.put("aa", "bc");
        dictionary.put("c", "c");

        return dictionary;
    }

}
