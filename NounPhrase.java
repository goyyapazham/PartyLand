public class NounPhrase extends Phrase {

    //constant arrays
    private String[] nouns = new String[]{"dog", "house", "cat", "watch", "tool", "box", "lunch", "peach", "waltz", "apple", "truck", "egg", "beach", "tax"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big", "small", "sad", "smelly", "soft", "loud", "mean", "generous", "wonderful"};

    //used to conjugate (see class VerbPhrase)
    protected int subj = 3;
    protected boolean plural = false;

    public NounPhrase() {
	this((int)(Math.random() * 3) == 0);
    }
    
    public NounPhrase(boolean boo) {
	//select random noun from array of nouns
	int getSubj = (int)(Math.random() * 8);
	if(getSubj == 0) {
	    if(boo) word = "we";
	    else word = "I";
	    subj = 1;
	}
	else if (getSubj == 1) {
	    word = "you";
	    subj = 2;
	}
	else {
	    word = nouns[(int)(Math.random() * nouns.length)];
	    if(boo) pluralize();
	}
	phrase = word;
        addAdj();
    }

    public NounPhrase(int i, boolean boo) {
	subj = i;
	if(subj == 1) {
	    if(boo) word = "we";
	    else word = "I";
	}
	else if(subj == 2) {
	    word = "you";
	}
	else {
	    word = nouns[(int)(Math.random() * nouns.length)];
	    if(boo) pluralize();
	}
	phrase = word;
	addAdj();
    }

    public void addAdj() {
	if (subj != 3) return;
	for(int i = 0; i < (int)(Math.random() * 3); i++) {
	    //select random adjective from array of adjectives
	    String adj = adjective[(int)(Math.random() * adjective.length)];
	    //if adjective has not already been used,
	    if (phrase.indexOf(adj) == -1) {
		//add adjective to the beginning of the noun-phrase
		phrase = adj + " " + phrase;
	    }
	}
    }

    public void addArt() {
	if(subj == 3) phrase = "the " + phrase;
    }

    public void pluralize() {
	//used to conjugate (see class VerbPhrase)
	plural = true;
	if (word.equals("I")) {
	    word = "we";
	}
	else if (word.substring(word.length() - 2).equals("ch")
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
	    word = word.substring(0,word.length() - 1) + "ies";
	}
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
