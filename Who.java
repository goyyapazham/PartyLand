import java.util.ArrayList;

public class Who extends Sentence {

    ParseCSV n = new ParseCSV("names.txt");
    ArrayList namestxt = n.words;
    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> genders = new ArrayList<String>();

    private static String punctuation = ".,;:!?";

    public Who( Sentence input ) {
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
	String z = strip(s);
	String[] y = z.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	modifyNames();
	for(int i = 0; i < input.size(); i++) {
	    String x = input.get(i);
	    if(names.contains(x)) {
		String gend = "he";
		if(genders.get(names.indexOf(x)).equals("f")) gend = "she";
		VerbPhrase vp = new VerbPhrase(true);
		return "I think " + gend + " " + vp.phrase;
	    }
	}
	if((int)(Math.random() * 2) == 0) {
	    return "I think it was "
		+ names.get((int)(Math.random() * names.size()));
	}
	else {
	    return "Who? I'm all alone out here";
	}

    }
    
    public void punctuate() {
	sentence += ".";
    }

}
