import java.util.ArrayList;

public class Who extends Sentence {
    //create ArrayLists for names and genders
    ParseCSV n = new ParseCSV("names.txt");
    ArrayList namestxt = n.words;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> genders = new ArrayList<String>();

    private static String punctuation = ".,;:!?";

    public Who( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }
    //strips punctuation form strings
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    //makes data of names.txt easier to use
    public void modifyNames() {
	for(int i = 0; i < namestxt.size(); i++) {
	    namestxt.set(i, ((String)namestxt.get(i)).split(", "));
	}
	for(int i = 0; i < namestxt.size(); i++) {
	    names.add(((String[])namestxt.get(i))[0]);
	    genders.add(((String[])namestxt.get(i))[1]);
	}
    }

    public String generate(String s) {
	//strip input of punctuation
	String z = strip(s);
	//split by spaces
	String[] y = z.split(" ");
	//convert to an ArrayList
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	//change names to be easier to use
	modifyNames();
	//loop through input
	for(int i = 0; i < input.size(); i++) {
	    String x = properNounIfy(input.get(i));
	    //if input is one of the names in name.txt
	    if(names.contains(x)) {
		//set correct geneder
		String gend = "he";
		if(genders.get(names.indexOf(x)).equals("f")) gend = "she";
		//make verb phrase for what person did
		VerbPhrase vp = new VerbPhrase(true);
		return "I think " + gend + " " + vp.phrase;
	    }
	}
	//else return sentence using random name from name.txt
	if((int)(Math.random() * 2) == 0) {
	    return "I think it was "
		+ names.get((int)(Math.random() * names.size()));
	}
	//or return pre set sentence
	else {
	    return "Who? I'm all alone out here";
	}

    }
    //capitilizes names
    public String properNounIfy(String s) {
	return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
