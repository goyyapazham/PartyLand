import java.util.ArrayList;

public class Why extends Sentence {

    //instance variables of punctuation and nouns
    private static String punctuation = ".,;:!?";
    ParseCSV f = new ParseCSV("objects.txt");
    private ArrayList<String> objects = f.words;
    public Why( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }
    //strips string of punctuation
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    //checks if something is punctuation
    public static boolean isPunc( String searching ) {
     	for ( int i = 0; i < punctuation.length(); i++) {
	    if ( searching.equals( punctuation.substring( i, i+1 ) ) ) {
		return true;
	    }
	}
	return false;
    }

    public String generate(String s) {
	//create new Declarative
	Declarative retDec = new Declarative();
	Boolean b = false;
	//split input by spaces
	String[] str = s.split(" ");
	//remove punctuation from input
	str[str.length - 1] = strip(str[str.length - 1]);
	//loop through inputs
	for(int i = 0; i < str.length; i++) {
	    //if it contains punctuation remove it
	    if ( isPunc( str[i].substring( str[i].length() - 1 ) ) ) {
		str[i] = str[i].substring( 0, str[i].length() - 1 );
	    }
	    //use the HTMLParser to make words singular
	    HTMLParser singular = new HTMLParser( str[i] );
	    singular.startConnection();
	    singular.singular();
	    str[i] = singular.toString();
	    //if it is related to color , animal, or food use What class to answer
	    if ( str[i].equals("color") ||
		 str[i].equals("animal") ||
		 str[i].equals("food") ) {
		What answer = new What(s);
		return answer.toString();
	    }
	    //if it is a noun create a declarative statement based on noun
	    //and set b to true to show there is a noun
	    if(objects.contains(str[i])) {
		retDec.generate(str[i]);
		b = true;
		break;
	    }
	}
	int rand = (int)(Math.random() * 10);
	//if noun was found return the declarative
	if (b) {
	    retDec.sentence = strip( retDec.toString().toLowerCase() );
	    return "Because " + retDec;
	}
	//if not then use one of three repsonses
	else if(rand == 0) return "Because pigs can fly";
	else if(rand < 3) return "Because the sky is blue";
	else return "Because I said so";
    }
    
    public void punctuate() {
	sentence += ".";
    }

}
