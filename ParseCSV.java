import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

public class ParseCSV {

    protected ArrayList words;

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

    public static ArrayList split(String input) {
        ArrayList words = new ArrayList();
	for(int i = 0; i < freq(input, ",") + 1; i++) {
	    words.add("noun");
	}
	for(int i = 0; i < words.size() - 1; i++) {
	    words.set(i, input.substring(0, input.indexOf(",")));
	    input = input.substring(input.indexOf(",") + 2);
	}

	words.set(words.size() - 1, input);
	return words;
    }

    public ArrayList readFile(String fileName) {
	ArrayList words = new ArrayList();
       	try {
	    BufferedReader file =
		new BufferedReader(new FileReader(fileName));
	    String str = file.readLine();
	    words = split(str);
	} catch(IOException e) {
	    System.out.println("oops");
	}
	return words;
    }

    public static void main(String[] args) {

	ParseCSV nala = new ParseCSV("nouns.txt");
	System.out.println(nala);

    }
    
}

