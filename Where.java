import java.lang.ArrayIndexOutOfBoundsException;
import java.util.ArrayList;

public class Where extends Sentence {
    //instance variables refering to locations form locations.txt
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
    //strips string of punctuation
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    //organizes locations by state, city country
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
	//strip input of punctuation
	String z = strip(s);
	//split by spaces
	String[] y = z.split(" ");
	//convert to ArrayList
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	//organize locations
	modifyLocations();
	String retStr;
	for(int i = 0; i < input.size(); i++) {
	    //make locations properly capitilized
	    String x = properNounIfy(input.get(i));
	    //if input it a city ask if its in a country + state or just country
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
	//if not tell them to use google Maps or try and another city
	retStr = "I'm not sure. Have you tried ";
	if((int)(Math.random() * 2) == 0) {
	    retStr += cities.get((int)(Math.random() * cities.size()));
	}
	else {
	    retStr += "Google Maps";
	}
	return retStr;
    }
    //capitilize first letter in string
    public String properNounIfy(String s) {
	return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public void punctuate() {
	sentence += "?";
    }

}
