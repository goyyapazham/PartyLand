import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

public class ParseCSV {

    protected ArrayList<String> words = new ArrayList<String>();

    public ParseCSV(String filename) {
	words = readFile(filename);
    }

    public String toString() {
	return words.toString();
    }

    public static int freq(String s, String t) {
	int freq = 0;
	for(int i = 0; i < s.length(); i++) {
	    if (s.substring(i, i + 1).equals(t)) freq++;
	}
	return freq;
    }

    public String print() {
	String retStr = "[";
	for(int i = 0; i < words.size(); i++) {
	    retStr += words.get(i) + ", ";
	}
	return retStr.substring(0, retStr.length() - 2) + "]";
    }

    public ArrayList<String> readFile(String fileName) {
       	try {
	    BufferedReader file =
		new BufferedReader(new FileReader(fileName));
	    for(String str; (str = file.readLine()) != null; ) {
		words.add(str);
	    }
	} catch(IOException e) {
	    System.out.println("oops");
	    System.out.println(fileName);
	}
	return words;
    }

    public static void main(String[] args) {

	ParseCSV nala = new ParseCSV("nouns.txt");
	System.out.println(nala.print());

    }
    
}

