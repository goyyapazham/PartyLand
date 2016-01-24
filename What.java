import java.util.ArrayList;

public class What extends Sentence {

    ParseCSV a = new ParseCSV("animals.txt");
    ArrayList<String> animals = a.words;
    ParseCSV c = new ParseCSV("colors.txt");
    ArrayList<String> colors = c.words;
    ParseCSV f = new ParseCSV("foods.txt");
    ArrayList<String> foods = f.words;
    
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

    public String generate(String s) {
	String z = strip(s);
	String[] y = z.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	for(int i = 0; i < input.size(); i++) {
	    String x = input.get(i);
	    if (animals.contains(x)
		|| input.contains("animal")
		|| input.contains("animals")) {
		return "The " + x + animalResponse();
	    }
	    if (colors.contains(x)
		|| input.contains("color")
		|| input.contains("colors")) {
		return "My favorite color is " +
		    colors.get((int)(Math.random() * colors.size()));
	    }
	    if (foods.contains(x)
		|| input.contains("food")
		|| input.contains("foods")) {
		return "I myself am partial to " +
		    foods.get((int)(Math.random() * foods.size()));
	    }
	}
	return "I don't know";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
