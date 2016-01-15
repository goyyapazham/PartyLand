public class VerbPhrase extends Words {
    
    private String[] verbs = new String[]{"run", "greet", "walk", "jump", "play", "eat", "move", "dream", "sing", "learn", "ride", "scream", "smile", "read"};
    private String[] adverbs = new String[]{"loudly", "kindly", "carelessly", "softly"};

    public VerbPhrase(boolean plural) {
	word = verbs[(int)(Math.random() * verbs.length)];
	if(! (plural)) conjugate();
	phrase = word;
	if((int)(Math.random() * 4) == 0) {
	    String adv = adverbs[(int)(Math.random() * adverbs.length)];
	    phrase += " " + adv;
	}
    }

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
