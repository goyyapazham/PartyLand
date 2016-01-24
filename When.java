import java.util.ArrayList;

public class When extends Sentence {

    private String[] month = new String[]{"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
    private String[] day = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private String[] term = new String[]{"minute","day","week","month","year","decade","millineum"};
    private String[] term2 = new String[]{"tomorrow","next week","next month","next year"};
    private String[] term3 = new String[]{"yesterday","last week","last month","last year"};

    private static String punctuation = ".,;:!?";
    private boolean period = true;
    
    public When( Sentence input ) {
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

    public ArrayList<String> makeArrayList( String[] s ) {
	ArrayList<String> ret = new ArrayList();
	for ( int x = 0; x < s.length; x++ ) {
	    ret.add( s[x] );
	}
	return ret;
    }
    
    public String generate(String s) {
	s = strip(s);
	String[] y = s.split(" ");
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	String retStr = "";
	ArrayList<String> months = makeArrayList( month );
	ArrayList<String> days = makeArrayList( day );
	ArrayList<String> terms = makeArrayList( term );
	ArrayList<String> terms2 = makeArrayList( term2 );
	ArrayList<String> terms3 = makeArrayList( term3 );
	for ( int i = 0; i < input.size(); i++ ) {
	    String check = input.get(i);
	    if ( months.contains(check) ) {
		if ( Math.random() < .25 ) {
		    retStr = "That's";
		    retStr += " in " + (int) (Math.random() * 11 + 1) + " months";
		}
		return retStr;
	    }
	    else if ( days.contains(check) ) {
		if ( Math.random() < .25 ) {
		retStr = "That's";
		retStr += " in" + (int) (Math.random() * 6 + 1) + " days"; 
		}
		return retStr;
	    }
	    else if ( terms.contains(check) ) {
		if ( Math.random() < .25 ) {
		    retStr = "That's";
		    retStr += " in " + (int) (Math.random() * 10 + 1) + term2[ (int) (Math.random() * term2.length) ] + "s"; 
		}
		return retStr;
	    }
	    else if ( terms2.contains(check) ) {
		retStr = "What's happening";
		retStr += " " + term2[ (int) (Math.random() * term2.length) ]; 
		period = false;
		return retStr;
	    }
	    else if ( terms3.contains(check) ) {
		retStr = "What happened";
		retStr += " " + term3[ (int) (Math.random() * term3.length) ];
		period = false;
		return retStr;
	    }
	    retStr = "That's";
	    if ( Math.random() < .33 ) {
		retStr += " " + term2[ (int) (Math.random() * term2.length) ];
	    }
	    else if ( Math.random() < .5 ) {
		retStr += " was " + term3[ (int) (Math.random() * term3.length) ];
	    }
	    else {
		retStr += " in a few " + term[ (int) (Math.random() * term.length) ] + "s";
	    }
	    return retStr;
	}
	return retStr;
    }
    
    public void punctuate() {
	if (period) sentence += ".";
	else sentence += "?";
    }

}
