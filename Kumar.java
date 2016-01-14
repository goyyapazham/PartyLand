public class Kumar {

    protected String sentence;
 
    public Kumar() {
    }

    public String sentence() {
	NounPhrase n = new NounPhrase();
	VerbPhrase v = new VerbPhrase();
	return n + " " + v;
    }

    public String toString() {
	return sentence;
    }

    public static void main( String[] args ) {
	Kumar pardeep = new Kumar();
	for ( int x = 0; x < 15; x++ ) {
	    System.out.println( pardeep.sentence() );
	}
    }
}
