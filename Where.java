public class Where extends Sentence {

    public Where( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "Have you tried Google Maps";
    }
    
    public void punctuate() {
	sentence += "?";
    }

}
