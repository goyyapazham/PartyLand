import java.lang.ArrayIndexOutOfBoundsException;
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

    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }

    public void modifyLocations() {
	for(int i = 0; i < locationstxt.size(); i++) {
	    locationstxt.set(i, ((String)locationstxt.get(i)).split(", "));
	}
	for(int i = 0; i < locationstxt.size(); i++) {
	    cities.add(((String[])locationstxt.get(i))[0]);
	    countries.add(((String[])locationstxt.get(i))[1]);
	    try {
		states.add(((String[])locationstxt.get(i))[2]);
	    }
	    catch(ArrayIndexOutOfBoundsException e) {
		states.add("");
	    }
	}
    }

    public String generate(String s) {
	String z = strip(s);
	String[] y = z.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    y[i] = y[i].toLowerCase();
	    input.add(y[i]);
	}
	modifyLocations();
	String retStr;
	for(int i = 0; i < input.size(); i++) {
	    String x = input.get(i);
	    if(cities.contains(x)) {
	        retStr = "Isn't that in ";
		String country = countries.get(cities.indexOf(x));
		String state = states.get(cities.indexOf(x));
		if(!(state).equals(""))
		    return retStr + state;
		else
		    return retStr + country;
	    }
	}
	retStr = "I'm not sure. Have you tried ";
	if((int)(Math.random() * 2) == 0) {
	    retStr += cities.get((int)(Math.random() * cities.size()));
	}
	else {
	    retStr += "Google Maps";
	}
	return retStr;
    }
    
    public void punctuate() {
	sentence += "?";
    }

}
