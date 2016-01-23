public class Why extends Sentence {

    public Why( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "That's the question";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
