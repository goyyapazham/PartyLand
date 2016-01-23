public class When extends Sentence {

    public When( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "I might be tomorrow";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
