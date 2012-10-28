package com.sabdakosha;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Repository {
    private ArrayList<String> matchingEntries;
    private InputStream inputStream;
    private Charset utf8Charset = Charset.forName("UTF-8");

    public Repository(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public ArrayList<String> getWordsContaining(String searchWord) {
        matchingEntries = new ArrayList<String>();
        String line;
        String searchRegex = ".*" + searchWord + ".*";
        Pattern pattern = Pattern.compile(searchRegex, Pattern.CASE_INSENSITIVE);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, utf8Charset);
            BufferedReader dictionaryReader = new BufferedReader(inputStreamReader);
            while((line = dictionaryReader.readLine())!= null)    {
                if(pattern.matcher(line).matches())
                    matchingEntries.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return matchingEntries;
    }
}
