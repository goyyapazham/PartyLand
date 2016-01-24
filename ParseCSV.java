import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~
   Special thanks to the Java API
   for making this work!!!
   ~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class ParseCSV {

    //inst vars
    protected ArrayList<String> words = new ArrayList<String>();

    //constructor
    public ParseCSV(String filename) {
	words = readFile(filename);
    }

    //toString()
    public String toString() {
	return words.toString();
    }

    public ArrayList<String> readFile(String fileName) {
	//try to read the file (BufferedReader won't do this without try/catch)
       	try {
	    BufferedReader file =
		new BufferedReader(new FileReader(fileName));
	    //as long as you can read out the next line from the file
	    for(String str; (str = file.readLine()) != null; ) {
		//add the next line to the array of words
		words.add(str); //(can be split by whitespace if necessary)
	    }
	} catch(IOException e) {
	    System.out.println("oops");
	    System.out.println(fileName);
	}
	return words;
    }

    //~~~ test case for debugging purposes ~~~
    /*
    public static void main(String[] args) {

	ParseCSV nala = new ParseCSV("nouns.txt");
	System.out.println(nala);

    }
    */
    
}

