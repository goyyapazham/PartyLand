import java.util.ArrayList;

public class Where extends Sentence {

    ParseCSV l = new ParseCSV("locations.txt");
    ArrayList locationstxt = l.words;
    ArrayList<String> cities = new ArrayList<String>();
    ArrayList<String> countries = new ArrayList<String>();
    ArrayList<String> states = new ArrayList<String>();

    private static String punctuation = ".,;:!?";

    public Where( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "Have you tried Google Maps";
    }

    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    
    public void punctuate() {
	sentence += "?";
    }

}
