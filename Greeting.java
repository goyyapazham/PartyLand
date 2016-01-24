public class Greeting extends Sentence {

    //general greeting
    String[] greet1 = new String[]{"hi","hello","greetings","hey"};
    //more in depth greeting
    String[] greet2 = new String[]{"how's it going","how are you","how about you", "what's up","what's going on"};
    //answers to in depth greeting
    String[] answer = new String[]{"i'm fine","i feel sad","it's been a long day","i have the energy of a dinosaur"};
    //if you have a question at end of greeting or not
    boolean quest = false;
    private String punctuation = ".,;:!?";
    //method for stripping punctuation from a string
    public String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1) {
		retStr += s.substring(i, i + 1);
	    }
	}
	return retStr;
    }
    //method to seach for String searching in the input 
    public boolean search( String searching, String input ) {
	int length = searching.length();
	input = input.toLowerCase();
	//if the input is the same as what youre searching for return true
	if ( searching.equals(input) ) return true;
	//loop through input string to find searched string
	for ( int i = 0; i < input.length() - length + 1; i++) {
	    if ( ( input.substring( i, i + length) ).equals(searching) ) {
		return true;
	    }
	}
	//if not found return false
	return false;
    }
    
    public String generate(String s) {
	//strip the input of punctuation at the end
	s = strip(s);
	//loop through the in depth greeting
	for ( int i = 0; i < greet2.length; i++ ) {
	    //loop through regular greetings
	    for ( int x = 0; x < greet1.length; x++ ) {
		//if the input contains both types of greetings return a normal greeting
		//along with an answer for the in depth greeting
		if ( search(greet1[x],s) && search(greet2[i],s) ) {
		    sentence = greet1[ (int) (Math.random() * greet1.length) ];
		    sentence += ", " + answer[ (int) (Math.random() * answer.length) ];
		    punctuate();
		    capitalize();
		}
		//if it is just a bbasic greeting return a basic greeting along with
		//a more indepth one 50% of the time
		else if ( greet1[x].equals( s.toLowerCase() ) ) {
		    //choose a greeting from greet1
		    sentence = greet1[ (int) (Math.random() * greet1.length) ];
		    //add greeting question from greet2
		    if (Math.random() > .5) {
			//if more indepth greeting is added set quest to true to have
			//appropiate punctuation
			quest = true;
			sentence += ", " + greet2[ (int) (Math.random() * greet2.length) ];
		    }
		    punctuate();
		    capitalize();
		}
	    }
	    //if it is just a in depth greeting return an answer to it
	    if (greet2[i].equals( s.toLowerCase() ) ) {
		sentence = answer[ (int) (Math.random() * answer.length) ];
		punctuate();
		capitalize();
	    }
	}
	//loop through the answers for in depth greetings
	for ( int i = 0; i < answer.length; i++ ) {
	    //if the input is on the answers return a pre set response
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
