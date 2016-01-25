import java.util.ArrayList;

public class How extends Sentence {

    private static String punctuation = ".,;:!?";
    
    ParseCSV k = new ParseCSV("kumar.txt");
    ArrayList kumar = k.words;
    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<String> bio = new ArrayList<String>();
    
    public How( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }
    //strip punctuation form a string
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    
    //adjusts the Kumar bio data to be more accessible
    public void modifyKumar() {
	//loop through data pulled from CSV file
	for(int i = 0; i < kumar.size(); i++) {
	    //split the data on commas
	    kumar.set(i, ((String)kumar.get(i)).split("; "));
	}
	//loop through CSV data again adding the data to its
	//appropiate data set
	for(int i = 0; i < kumar.size(); i++) {
	    categories.add(((String[])kumar.get(i))[0]);
	    bio.add(((String[])kumar.get(i))[1]);
	}
    }
    
    public String generate(String s) {
	//strip input of punctuation at end
	s = strip(s);
	//split input on spaces
	String[] y = s.split(" ");
	//make an ArrayList of inputs
	ArrayList<String> input = new ArrayList<String>();
	//loop through string of inputs and add them to the ArrayList
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	String retStr = "";
	modifyKumar();
	//loop through array of inputs
	for ( int i = 0; i < input.size(); i++ ) {
	    String check = input.get(i);
	    //if one of the inputs is many respond with a number
	    if ( ( check.toLowerCase() ).equals("old") ) {
		retStr = "I'm " + bio.get(categories.indexOf("age"))
		    + " old";
		return retStr;
	    }
	    if ( ( check.toLowerCase() ).equals("many") ) {
		retStr = "I'm pretty sure it is " + (int) (Math.random() * 100);
		return retStr;
	    }
	}
	//if it is not many respond with a preset answer.
	return "Don't ask me. I'm not god";
    }
    
    
    public void punctuate() {
	sentence += ".";
    }

}
