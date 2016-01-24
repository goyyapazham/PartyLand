public class How extends Sentence {

    private static String punctuation = ".,;:!?";
    
    public How( Sentence input ) {
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
	s = strip(s);
	String[] y = s.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	String retStr = "";
	for ( int i = 0; i < input.size(); i++ ) {
	    String check = input.get(i);
	    if ( ( check.toLowerCase() ).equals("many") ) {
		retStr = "I'm pretty sure it is" + (int) (Math.random() * 100);
		return retStr;
	    }
	}
	return "Don't ask me. I'm not god";
    }
    
    
    public void punctuate() {
	sentence += ".";
    }

}
