public class Test {

    protected Sentence sentence;

    public Test() {
	sentence = new Farewell();
    }

    public String toString() {
	return sentence.toString();
    }

    public static void main( String[] args ) {
	Test pardeep = new Test();
	//output 15 random sentences
	for ( int x = 0; x < 15; x++ ) {
	    System.out.println( pardeep.sentence.generate("") );
	}
    }
}
