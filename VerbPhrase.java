public class VerbPhrase extends Phrase {

    //constant arrays
    private String[] verbs = parseCSV("verbs.txt");
    private String[] adverbs = parseCSV("adverbs.txt");

    public VerbPhrase(boolean conj) {
    //overloaded constructor (parameter ins
	//select random verb from array of verbs
	word = verbs[(int)(Math.random() * verbs.length)];
	//conjugate
	if(conj) conjugate();
	//now work with the verb-phrase
	phrase = word;
	//one-fourth chance of adding an adverb
	if((int)(Math.random() * 4) == 0) {
	    //select random adverb from array of adverbs
	    String adv = adverbs[(int)(Math.random() * adverbs.length)];
	    //add adverb to verb-phrase
	    phrase += " " + adv;
	}
	else if ( (int) (Math.random() * 4) == 0 ) {
	    NounPhrase extra = new NounPhrase();
	    String str = extra.toString();
	    phrase += " " + str;
	}
    }

    //the conjugate method currently only works with singular & plural third-person subjects, so it's very limited
    public void conjugate() {
	word += "s";
    }

    public String toString() {
	return phrase;
    }

    //~~~ test cases for debugging purposes ~~~
    
    public static void main(String[] args) {
	VerbPhrase pardeep = new VerbPhrase(true);
	VerbPhrase nala = new VerbPhrase(false);
	System.out.println(pardeep);
	System.out.println(nala);
    }
    
    
}
