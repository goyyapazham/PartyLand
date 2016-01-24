public class Greeting extends Sentence {
    
    String[] greet1 = new String[]{"hi","hello","greetings","hey"};
    String[] greet2 = new String[]{"how's it going","how are you","how about you", "what's up","what's going on"};
    String[] answer = new String[]{"i'm fine","i feel sad","it's been a long day","i have the energy of a dinosaur"};
    //if you have a question at end of greeting or not
    boolean quest = false;
    private String punctuation = ".,;:!?";

    public String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    public boolean search( String searching, String input ) {
	int length = searching.length();
	input = input.toLowerCase();
	if ( searching.equals(input) ) return true;
	for ( int i = 0; i < input.length() - length + 1; i++) {
	    if ( ( input.substring( i, i + length) ).equals(searching) ) {
		return true;
	    }
	}
	return false;
    }
    public String generate(String s) {
	s = strip(s);
	for ( int i = 0; i < greet2.length; i++ ) { 
	    for ( int x = 0; x < greet1.length; x++ ) {
		if ( search(greet1[x],s) && search(greet2[i],s) ) {
		    sentence = greet1[ (int) (Math.random() * greet1.length) ];
		    sentence += ", " + answer[ (int) (Math.random() * answer.length) ];
		    punctuate();
		    capitalize();
		}
		else if ( greet1[x].equals( s.toLowerCase() ) ) {
		    //choose a greeting from greet1
		    sentence = greet1[ (int) (Math.random() * greet1.length) ];
		    //add greeting question from greet2
		    if (Math.random() > .5) {
			quest = true;
			sentence += ", " + greet2[ (int) (Math.random() * greet2.length) ];
		    }
		    punctuate();
		    capitalize();
		}
	    }
	    if (greet2[i].equals( s.toLowerCase() ) ) {
		sentence = answer[ (int) (Math.random() * answer.length) ];
		punctuate();
		capitalize();
	    }
	}
	for ( int i = 0; i < answer.length; i++ ) {
	    if ( search( answer[i], s ) ) {
		sentence = "that's cool. Or is it";
		quest = true;
		punctuate();
		capitalize();
	    }
	}
	return sentence;
    }

    public void punctuate( ) {
	if (quest) sentence += "?";
	else sentence += ".";
	quest = false;
    }

}
