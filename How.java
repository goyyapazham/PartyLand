public class How extends Sentence {

    public How( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public String generate(String s) {
	return "Don't ask me. I'm not god";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
