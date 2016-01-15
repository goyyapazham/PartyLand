public class NounPhrase extends Words {

    private String[] nouns = new String[]{"dog", "house", "cat", "watch", "tool", "box", "lunch", "peach", "waltz", "apple", "truck", "egg", "beach", "tax"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big"};
    protected boolean plural = false;
    
    public NounPhrase() {
	word = nouns[(int)(Math.random() * nouns.length)];
	if((int)(Math.random() * 3) == 2) {
	    pluralize();
	}
	phrase = word;
	for(int i = 0; i < (int)(Math.random() * 5); i++) {
	    String adj = adjective[(int)(Math.random() * adjective.length)];
	    if (phrase.indexOf(adj) == -1) {
		phrase = adj + " " + phrase;
	    }
	}
    }

    public void pluralize() {
	plural = true;
	if (word.substring(word.length() - 2).equals("ch")
	    || word.substring(word.length() - 2).equals("sh")
	    || word.substring(word.length() - 1).equals("x")
	    || word.substring(word.length() - 1).equals("z")) {
	    word += "es";
	}
	else {
	    word += "s";
	}
	//definitely more cases, but solid enough for now
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
