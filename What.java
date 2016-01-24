import java.util.ArrayList;

public class What extends Sentence {

    ParseCSV a = new ParseCSV("animals.txt");
    ArrayList<String> animals = a.words;
    ParseCSV c = new ParseCSV("colors.txt");
    ArrayList<String> colors = c.words;
    ParseCSV f = new ParseCSV("foods.txt");
    ArrayList<String> foods = f.words;
    
    ParseCSV k = new ParseCSV("kumar.txt");
    ArrayList kumar = k.words;
    ArrayList<String> categories = new ArrayList<String>();
    ArrayList<String> bio = new ArrayList<String>();
    
    private static String punctuation = ".,;:!?";

    public What( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }
    public What( String input ) {
	sentence = generate( input );
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

    public String animalResponse() {
	String[] v = new String[]{"munches", "nuzzles", "nibbles"};
	String[] a = new String[]{"delicately", "violently", "savagely",
				  "wildly", "daintily", "angrily"};
	Phrase resp
	    = new VerbPhrase(v[(int)(Math.random() * v.length)],
			     a[(int)(Math.random() * a.length)], true);
	String newAnimal = animals.get((int)(Math.random() * animals.size()));
        return resp.phrase + " the " + newAnimal;
    }

    public void modifyKumar() {
	for(int i = 0; i < kumar.size(); i++) {
	    kumar.set(i, ((String)kumar.get(i)).split(", "));
	}
	for(int i = 0; i < kumar.size(); i++) {
	    categories.add(((String[])kumar.get(i))[0]);
	    bio.add(((String[])kumar.get(i))[1]);
	}
    }

    public String generate(String s) {
	String z = strip(s);
	String[] y = z.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	modifyKumar();
	for(int i = 0; i < input.size(); i++) {
	    String x = input.get(i);
	    if (animals.contains(x)
		|| input.get(i).indexOf("animal") > -1
		|| input.get(i).indexOf("animals") > -1) {
		if(x.equals("animal") || x.equals("animals"))
		    return "That's a " +
			animals.get((int)(Math.random() * animals.size()));
		return "The " + x + " " + animalResponse();
	    }
	    if (colors.contains(x)
		|| input.get(i).indexOf("color") > -1
		|| input.get(i).indexOf("colors") > -1) {
		return "My favorite color is " +
		    bio.get(categories.indexOf("color"));
	    }
	    if (foods.contains(x)
		|| input.get(i).indexOf("food") > -1
		|| input.get(i).indexOf("foods") > -1) {
		return "I myself am partial to "
		    + bio.get(categories.indexOf("food"));
	    }
	}
	return "I don't know";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
