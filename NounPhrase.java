public class NounPhrase extends Phrase {

    //constant arrays
    private String[] nouns = new String[]{"dog", "house", "cat", "watch", "tool", "box", "lunch", "peach", "waltz", "apple", "truck", "egg", "beach", "tax"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big", "small", "sad", "smelly", "soft", "loud", "mean", "generous", "wonderful"};

    //used to conjugate (see class VerbPhrase)
    protected int subj = 3;
    protected boolean plural = false;

    //constructor
    public NounPhrase() {
	this((int)(Math.random() * 3) == 0);
    }

    //if nouns /must/ be pluralized
    public NounPhrase(boolean boo) {
	int getSubj = (int)(Math.random() * 8);
	//FIRST: identify /subject/ word and its point-of-view integer
	//12.5% chance of getting first-person
	if(getSubj == 0) {
	    if(boo) word = "we";
	    else word = "I";
	    subj = 1;
	}
	//12.5% chance of getting second-person
	else if (getSubj == 1) {
	    word = "you";
	    subj = 2;
	}
	//75% chance of getting third-person
	else {
	    word = nouns[(int)(Math.random() * nouns.length)];
	    if(boo) pluralize();
	}
	//SECOND: transfer word to phrase
	phrase = word;
	//THIRD: commence adjective addition, if necessary
        addAdj();
    }

    //if Sentence type is subject-specific
    public NounPhrase(int i, boolean boo) {
	subj = i;
	//FIRST: identify /subject/ word and its point-of-view integer
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
	//SECOND: transfer word to phrase
	phrase = word;
	//THIRD: commence adjective addition, if necessary
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

    //never used in NounPhrase bc only certain sentence types require articles
    public void addArt() {
	//only third-person nouns require articles
	if(subj == 3) phrase = "the " + phrase;
    }

    public void pluralize() {
	//used to conjugate (see class VerbPhrase)
	plural = true;
	if (word.equals("I")) {
	    word = "we";
	}
	//pluralization via "-es"
	else if (word.substring(word.length() - 2).equals("ch")
	    || word.substring(word.length() - 2).equals("sh")
	    || word.substring(word.length() - 1).equals("x")
	    || word.substring(word.length() - 1).equals("s")
	    || word.substring(word.length() - 1).equals("z")) {
	    word += "es";
	}
	//pluralization via "-y" --> "-ies"
	else if (word.substring(word.length() - 1).equals("y")) {
	    word = word.substring(0,word.length() - 1) + "ies";
	}
	//pluralization via "-s"
	else {
	    word += "s";
	}
    }
    
    public String toString() {
	return phrase;
    }

    //~~~ test cases for debugging purposes ~~~
    /*
    public static void main(String[] args) {
	NounPhrase pardeep = new NounPhrase();
	NounPhrase nala = new NounPhrase();
	System.out.println(pardeep);
	System.out.println(nala);
    }
    */

}
