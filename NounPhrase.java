public class NounPhrase extends Phrase {

    //constant arrays
    private String[] nouns = new String[]{"dog", "house", "cat", "watch", "tool", "box", "lunch", "peach", "waltz", "apple", "truck", "egg", "beach", "tax"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big"};

    //used to conjugate (see class VerbPhrase)
    protected boolean plural = false;

    //constructor
    public NounPhrase() {
	//select random noun from array of nouns
	word = nouns[(int)(Math.random() * nouns.length)];
	//one-third chance of pluralizing
	if((int)(Math.random() * 3) == 2) {
	    pluralize();
	}
	//now work with the noun-phrase
	phrase = word;
	for(int i = 0; i < (int)(Math.random() * 5); i++) {
	    //select random adjective from array of adjectives
	    String adj = adjective[(int)(Math.random() * adjective.length)];
	    //if adjective has not already been used,
	    if (phrase.indexOf(adj) == -1) {
		//add adjective to the beginning of the noun-phrase
		phrase = adj + " " + phrase;
	    }
	}
    }

    public void pluralize() {
	//used to conjugate (see class VerbPhrase)
	plural = true;
	//if a word ends in one of these things,
	if (word.substring(word.length() - 2).equals("ch")
	    || word.substring(word.length() - 2).equals("sh")
	    || word.substring(word.length() - 1).equals("x")
	    || word.substring(word.length() - 1).equals("s")
	    || word.substring(word.length() - 1).equals("z")) {
	    //it's pluralized via "-es"
	    word += "es";
	}
	//if word ends in y
	else if (word.substring(word.length() - 1).equals("y")) {
	    //it's pluralized via dropping "y" adding "-ies"
	    word = word.substring(0,word.length() - 1) + "ies"
	else {
	    //otherwise, it's pluralized via "-s"
	    word += "s";
	}
	//definitely need more cases, but solid enough for now
    }
    
    public String toString() {
	return phrase;
    }
    
    /*
    public static void main(String[] args) {
	NounPhrase pardeep = new NounPhrase();
	NounPhrase nala = new NounPhrase();
	System.out.println(pardeep);
	System.out.println(nala);
    }
    */

}
