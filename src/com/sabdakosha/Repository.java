package com.sabdakosha;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Repository {
    private ArrayList<String> words;
    private InputStream inputStream;
    private Charset utf8Charset = Charset.forName("UTF-8");

    public Repository(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public ArrayList<String> getWordsMatching(String pattern) {
        words = new ArrayList<String>();
        String line;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, utf8Charset);
            BufferedReader dictionaryReader = new BufferedReader(inputStreamReader);
            while((line = dictionaryReader.readLine())!= null)    {
                if(line.matches(pattern))
                    words.add(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return words;
    }
}
