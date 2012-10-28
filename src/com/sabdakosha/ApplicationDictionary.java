package com.sabdakosha;

import java.util.ArrayList;

public class ApplicationDictionary {
    Repository repository;

    public ApplicationDictionary(Repository repo) {
        this.repository = repo;
    }

    public ArrayList<String> GetEntriesContaining(String searchWord) {
        ArrayList<String> matches = repository.getWordsContaining(searchWord);
        return matches;
    }


}
