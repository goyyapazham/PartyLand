public class VerbPhrase extends Phrase {

    //constant arrays
    private String[] verbs = new String[]{"run", "greet", "walk", "jump", "play", "eat", "move", "dream", "sing", "learn", "ride", "scream", "smile", "read"};
    private String[] adverbs = new String[]{"loudly", "kindly", "carelessly", "softly"};

    //overloaded constructor (parameter inserted in driver Kumar)
    public VerbPhrase(boolean plural) {
	//select random verb from array of verbs
	word = verbs[(int)(Math.random() * verbs.length)];
	//now work with the verb-phrase
	phrase = word;
	//conjugate
	if(plural) conjugate();
	//one-fourth chance of adding an adverb
	if((int)(Math.random() * 4) == 0) {
	    //select random adverb from array of adverbs
	    String adv = adverbs[(int)(Math.random() * adverbs.length)];
	    //add adverb to verb-phrase
	    phrase += " " + adv;
	}
    }

    //the conjugate method currently only works with singular & plural third-person subjects, so it's very limited
    public void conjugate() {
	word += "s";
    }

    public String toString() {
	return phrase;
    }
    
    /*
    public static void main(String[] args) {
	VerbPhrase pardeep = new VerbPhrase();
	VerbPhrase nala = new VerbPhrase();
	System.out.println(pardeep);
	System.out.println(nala);
    }
    */

}
