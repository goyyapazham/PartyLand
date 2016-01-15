public class NounPhrase extends Words {

    private String[] nouns = new String[]{"dog", "house", "cat"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big"};

    public NounPhrase() {
	phrase = nouns[(int)(Math.random() * nouns.length)];
	for(int i = 0; i < (int)(Math.random() * 5); i++) {
	    phrase = adjective[(int)(Math.random() * adjective.length)] + " " + phrase;
	}
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
