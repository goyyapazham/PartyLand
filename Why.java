import java.util.ArrayList;

public class Why extends Sentence {

    private static String punctuation = ".,;:!?";
    ParseCSV f = new ParseCSV("objects.txt");
    private ArrayList<String> objects = f.lines;
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
    public static boolean isPunc( String searching ) {
     	for ( int i = 0; i < punctuation.length(); i++) {
	    if ( searching.equals( punctuation.substring( i, i+1 ) ) ) {
		return true;
	    }
	}
	return false;
    }

    public String generate(String s) {
	Declarative retDec = new Declarative();
	Boolean b = false;
	String[] str = s.split(" ");
	str[str.length - 1] = strip(str[str.length - 1]);
	for(int i = 0; i < str.length; i++) {
	    if ( isPunc( str[i].substring( str[i].length() - 1 ) ) ) {
		str[i] = str[i].substring( 0, str[i].length() - 1 );
	    }
	    HTMLParser singular = new HTMLParser( str[i] );
	    singular.startConnection();
	    singular.singular();
	    str[i] = singular.toString();
	    if ( str[i].equals("color") ||
		 str[i].equals("animal") ||
		 str[i].equals("food") ) {
		What answer = new What(s);
		return answer.toString();
	    }
	    if(objects.contains(str[i])) {
		retDec.generate(str[i]);
		b = true;
		break;
	    }
	}
	int rand = (int)(Math.random() * 10);
	if (b) {
	    retDec.sentence = strip( retDec.toString().toLowerCase() );
	    return "Because " + retDec;
	}
	else if(rand == 0) return "Because pigs can fly";
	else if(rand < 3) return "Because the sky is blue";
	else return "Because I said so";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
