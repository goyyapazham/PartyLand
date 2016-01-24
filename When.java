import java.util.ArrayList;

public class When extends Sentence {
    //instance variables for time
    private String[] month = new String[]{"January","Febuary","March","April","May","June","July","August","September","October","November","December"};
    private String[] day = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    private String[] term = new String[]{"minute","day","week","month","year","decade","millineum"};
    private String[] term2 = new String[]{"tomorrow","next week","next month","next year"};
    private String[] term3 = new String[]{"yesterday","last week","last month","last year"};

    private static String punctuation = ".,;:!?";
    // boolean for if punctuation type
    private boolean period = true;
    
    public When( Sentence input ) {
	sentence = generate( input.toString() );
	punctuate();
    }
    //strips all the punctuation from a string
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    //makes a string Array into an ArrayList
    public ArrayList<String> makeArrayList( String[] s ) {
	ArrayList<String> ret = new ArrayList();
	for ( int x = 0; x < s.length; x++ ) {
	    ret.add( s[x] );
	}
	return ret;
    }
    
    public String generate(String s) {
	//strip the input of punctuation
	s = strip(s);
	//split by spaces
	String[] y = s.split(" ");
	//convert to an ArrayList
	ArrayList<String> input = new ArrayList<String>();
	for(int i = 0; i < y.length; i++) {
	    input.add(y[i]);
	}
	String retStr = "";
	//make ArrayLists for all the instance variables that are String Arrays
	ArrayList<String> months = makeArrayList( month );
	ArrayList<String> days = makeArrayList( day );
	ArrayList<String> terms = makeArrayList( term );
	ArrayList<String> terms2 = makeArrayList( term2 );
	ArrayList<String> terms3 = makeArrayList( term3 );
	//loop through inputs
	for ( int i = 0; i < input.size(); i++ ) {
	    String check = input.get(i);
	    //if input word is a month return month related answer wiht 25% chance
	    if ( months.contains(check) ) {
		if ( Math.random() < .25 ) {
		    retStr = "That's";
		    retStr += " in " + (int) (Math.random() * 11 + 1) + " months";
		}
		return retStr;
	    }
	    //if input word is a day return day related answer wiht 25% chance
	    else if ( days.contains(check) ) {
		if ( Math.random() < .25 ) {
		retStr = "That's";
		retStr += " in" + (int) (Math.random() * 6 + 1) + " days"; 
		}
		return retStr;
	    }
	    //if input word is a time frame return time frame related answer wiht 25% chance
	    else if ( terms.contains(check) ) {
		if ( Math.random() < .25 ) {
		    retStr = "That's";
		    retStr += " in " + (int) (Math.random() * 10 + 1) + term2[ (int) (Math.random() * term2.length) ] + "s"; 
		}
		return retStr;
	    }
	    //if input word is a future time return future time related answer wiht 25% chance
	    else if ( terms2.contains(check) ) {
		retStr = "What's happening";
		retStr += " " + term2[ (int) (Math.random() * term2.length) ]; 
		period = false;
		return retStr;
	    }
	    //if input word is a past time return past time related answer wiht 25% chance
	    else if ( terms3.contains(check) ) {
		retStr = "What happened";
		retStr += " " + term3[ (int) (Math.random() * term3.length) ];
		period = false;
		return retStr;
	    }
	    //if none of the above generate a random repsonse related to time
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
