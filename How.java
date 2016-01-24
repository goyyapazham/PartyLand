import java.util.ArrayList;

public class How extends Sentence {

    private static String punctuation = ".,;:!?";
    
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
	//loop through array of inputs
	for ( int i = 0; i < input.size(); i++ ) {
	    String check = input.get(i);
	    //if one of the inputs is many respond with a number
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
