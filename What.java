public class What extends Sentence {

    public What( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "I don't know";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
