import java.util.ArrayList;

public class What extends Sentence {
    //variables for different nouns
    ParseCSV a = new ParseCSV("animals.txt");
    ArrayList<String> animals = a.words;
    ParseCSV c = new ParseCSV("colors.txt");
    ArrayList<String> colors = c.words;
    ParseCSV f = new ParseCSV("foods.txt");
    ArrayList<String> foods = f.words;
    
    //variables for kumar's bio
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
    //strips a string of punctuation
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    
    //responses for animal related inputs
    public String animalResponse() {
	//verbs for animals
	String[] v = new String[]{"munches", "nuzzles", "nibbles"};
	//adverbs for animals
	String[] a = new String[]{"delicately", "violently", "savagely",
				  "wildly", "daintily", "angrily"};
	//make a new verb phrase that selects a verb and adverb
	Phrase resp
	    = new VerbPhrase(v[(int)(Math.random() * v.length)],
			     a[(int)(Math.random() * a.length)], true);
	//string that stores an animal name 
	String newAnimal = animals.get((int)(Math.random() * animals.size()));
	//return the phrase with the animal name
        return resp.phrase + " the " + newAnimal;
    }
    
    //adjusts the Kumar bio data to be more accessible
    public void modifyKumar() {
	//loop through data pulled from CSV file
	for(int i = 0; i < kumar.size(); i++) {
	    //split the data on commas
	    kumar.set(i, ((String)kumar.get(i)).split(", "));
	}
	//loop through CSV data again adding the data to its
	//appropiate data set
	for(int i = 0; i < kumar.size(); i++) {
	    categories.add(((String[])kumar.get(i))[0]);
	    bio.add(((String[])kumar.get(i))[1]);
	}
    }

    public String generate(String s) {
	//strip input of punctuation
	String z = strip(s);
	//put input into an Array divided by spaces
	String[] y = z.split(" ");
	//convert the Array to an ArrayList
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	//change kumar's bio to be easier to use
	modifyKumar();
	//loop through the ArrayList of the input
	for(int i = 0; i < input.size(); i++) {
	    String x = input.get(i);
	    //if the input is animal repsond with an animal related answer
	    if (animals.contains(x)
		|| input.get(i).indexOf("animal") > -1
		|| input.get(i).indexOf("animals") > -1) {
		if(x.equals("animal") || x.equals("animals"))
		    return "That's a " +
			animals.get((int)(Math.random() * animals.size()));
		return "The " + x + " " + animalResponse();
	    }
	    //if the input contains color return color related answer
	    if (colors.contains(x)
		|| input.get(i).indexOf("color") > -1
		|| input.get(i).indexOf("colors") > -1) {
		return "My favorite color is " +
		    bio.get(categories.indexOf("color"));
	    }
	    //if input contains food return food related answer
	    if (foods.contains(x)
		|| input.get(i).indexOf("food") > -1
		|| input.get(i).indexOf("foods") > -1) {
		return "I myself am partial to "
		    + bio.get(categories.indexOf("food"));
		
	    }
	    //if the input word is name return name
	    if( x.equals("name") )
		return "My name is " + bio.get( categories.indexOf("name") );
	    //if the input word is age return age
	    if( x.equals("age") )
		return "I'm " + bio.get( categories.indexOf("age") )
		    + " old";
	    //if the input word is job return job
	    if( x.equals("job") )
		return "I'm a " + bio.get( categories.indexOf("job") );
	    
	}
	//if not recognized return "i dont know"
	return "I don't know";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
