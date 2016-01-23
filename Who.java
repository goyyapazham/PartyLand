public class Who extends Sentence {

    public Who( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "Who? I'm all alone here";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
