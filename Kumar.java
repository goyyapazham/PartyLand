public class Kumar {

    //sentence
    protected String sentence;

    //constructor
    public String sentence() {
	//generate noun-phrase
	NounPhrase n = new NounPhrase();
	//generate verb-phrase with respect to noun-phrase's subject
	VerbPhrase v = new VerbPhrase(n.plural);
	//generate sentence
	return n + " " + v;
    }

    public String toString() {
	return sentence;
    }

    public static void main( String[] args ) {
	Kumar pardeep = new Kumar();
	//output 15 random sentences
	for ( int x = 0; x < 15; x++ ) {
	    System.out.println( pardeep.sentence() );
	}
    }
}
