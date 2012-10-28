package com.sabdakosha;

import java.nio.charset.Charset;
import java.util.ArrayList;

public class ApplicationDictionary {
    Repository repository;

    public ApplicationDictionary(Repository repo) {
        this.repository = repo;
    }

    public ArrayList<String> GetEntriesContaining(String searchWord) {
        String pattern = ".*" + searchWord + ".*";
        ArrayList<String> matches = repository.getWordsMatching(pattern);
        return matches;
    }


}
