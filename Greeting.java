public class Greeting extends Sentence {
    
    String[] greet1 = new String[]{"hi","hello","greetings","good day","hey"};
    String[] greet2 = new String[]{"how are you", "what's up","what's going on"};
    //if you have a question at end of greeting or not
    boolean greetLong = false;

    public String generate(String s) {
	//choose a greeting from greet1
	sentence = greet1[ (int) (Math.random() * greet1.length) ];
	//add greeting question from greet2
	if (Math.random() > .5) {
	    greetLong = true;
	    sentence += ", " + greet2[ (int) (Math.random() * greet2.length) ];
	}
        punctuate();
	capitalize();
	return sentence;
    }

    public void punctuate( ) {
	if (greetLong) sentence += "?";
	else sentence += ".";
	greetLong = false;		      
    }

}
