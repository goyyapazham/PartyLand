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
    }

    public VerbPhrase(String v, String a, boolean conj) {
	word = v;
	if(conj) conjugate();
	phrase = word;
	phrase = a + " " + v;
    }

    //the conjugate method currently only works with singular & plural third-person subjects, so it's very limited
    public void conjugate() {
	String lastTwo = word.substring(word.length() - 2);
	String lastOne = lastTwo.substring(1);
	if(lastTwo.equals("ss")
	   || lastTwo.equals("ch")
	   || lastTwo.equals("sh")) {
	    word += "es";
	}
	else if(lastOne.equals("y")) {
	    word = word.substring(0, word.length() - 1) + "ies";
	}
	else word += "s";
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
