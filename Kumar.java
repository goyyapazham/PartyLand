public class Kumar {

    protected Sentence sentence;

    public Kumar() {
	sentence = new Farewell();
    }

    public String toString() {
	return sentence.toString();
    }

    public static void main( String[] args ) {
	Kumar pardeep = new Kumar();
	//output 15 random sentences
	for ( int x = 0; x < 15; x++ ) {
	    System.out.println( pardeep.sentence.generate() );
	}
    }
}
