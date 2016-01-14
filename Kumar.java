public class Kumar {
    //instance variables of words
    private String[] nouns = new String[]{"dog", "house", "cat"};
    private String[] verbs = new String[]{"run", "greet", "walk", "jump", "play"};
    private String[] adjective = new String[]{"slimy", "clean", "dirty", "big"};
    private String[] adverbs = new String[]{"loudly", "kindly", "carelessly", "softly"};
 
    public void Kumar(){
    }

    public String sentence() {
	String nounPhrase;
	String verbPhrase;
	nounPhrase = nounPhrase();
	verbPhrase = verbPhrase();
	return nounPhrase + " "+ verbPhrase;
    }

    public String nounPhrase() {
	String adj = "";
	String noun;
	for ( int x = 0; x < (int) (Math.random() * 5); x++ ) {
	    adj += adjective[ (int) (Math.random() * adjective.length) ] + " ";
	}
	noun = nouns[ (int) (Math.random() * nouns.length) ];
	return adj + noun;
    }

    public String verbPhrase() {
	String adv;
	String verb;
	adv = adverbs[ (int) (Math.random() * adverbs.length) ];
	verb = verbs[ (int) (Math.random() * verbs.length) ] + " ";
	return verb + adv;
    }

    public static void main( String[] args ) {
	Kumar pardeep = new Kumar();
	for ( int x = 0; x < 15; x++ ) {
	    System.out.println( pardeep.sentence() );
	}
    }
}
