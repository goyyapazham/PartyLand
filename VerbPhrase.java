public class VerbPhrase extends Words {
    
    private String[] verbs = new String[]{"run", "greet", "walk", "jump", "play"};
    private String[] adverbs = new String[]{"loudly", "kindly", "carelessly", "softly"};

    public VerbPhrase() {
	phrase = verbs[(int)(Math.random() * verbs.length)];
	for(int i = 0; i < (int)(Math.random() * 2); i++) {
	    phrase += " " + adverbs[(int)(Math.random() * adverbs.length)];
	}
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
