public class Why extends Sentence {

    private static String punctuation = ".,;:!?";

    public Why( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }

    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    
    public String generate(String s) {
	return "That's the question";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
