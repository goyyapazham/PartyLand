import cs1.Keyboard;
import java.util.ArrayList;

public class Reader {
    //for farewells terminate program afterwards
    private boolean terminate = false;
    
    //speaking info
    public void begin() {
	System.out.println("Hello! Welcome. My name is Kumar.");
	help();
	System.out.println("Begin...");
	System.out.println();
    }

    public void help() {
	System.out.println("\nFor starters, try: ");
	System.out.println("\tHow are you?");
	System.out.println("\tWhat is 4/3?");
	System.out.println("\tWhat is the meaning of life?\n");
    }

    //for response mechanism
    public static boolean search( String searching, String input ) {
	int length = searching.length();
	input = input.toLowerCase();
	if ( searching.equals(input) ) return true;
	for ( int i = 0; i < input.length() - length; i++) {
	    if ( input.substring(i,i+length).equals(searching) ) {
		return true;
	    }
	}
	return false;
    }

    /* ~~~~ QUESTION ~~~~
       - isQ8estion()
       - questionType()
       - basicQAnswer()
       ~~~~~~~~~~~~~~~~~~ */
    
    public static boolean isQuestion(String input) {
	boolean retBool = false;
	if(input.substring(input.length() - 1).equals("?") ||
	   ! questionType(input).equals("unknown")) {
	    retBool = true;
	}
	return retBool;
    }

    public static String questionType( String input ) {
	if ( search("what",input) ) return "what";
	if ( search("when",input) ) return "when";
	if ( search("where",input) ) return "where";
	if ( search("who",input) ) return "who";
	if ( search("why",input) ) return "why";
	if ( search("how",input) ) return "how";
	return "unknown";
    }
    
    public Sentence basicQAnswer(Sentence s) {
	String str;
	if ( questionType(s.sentence).equals("what") ) {
	    str = "What is that? I don't know either but... \n ";
	}
	else if ( questionType(s.sentence).equals("when") ) {
	    str = "When is that? I don't know either but... \n ";
	}
	else if ( questionType(s.sentence).equals("where") ) {
	    str = "Where is that? I don't know either but... \n ";
	}
	else if ( questionType(s.sentence).equals("who") ) {
	    str = "Who is that? I don't know either but... \n ";
	}
	else if ( questionType(s.sentence).equals("why") ) {
	    str = "Why did that happen? I don't know either but... \n ";
	}
	else if ( questionType(s.sentence).equals("how") ) {
	    str = "How is that possible? I don't know either but... \n ";
	}
	else {
	    Sentence filler = new Filler();
	    str = filler.generate("") + " ";
	}
	s = new FunFact();
	str += s.generate("");
	s.sentence = str;
	return s;
    }

    //Greeting
    public boolean isGreet( Sentence s ) {
	Greeting greet = new Greeting();
	for ( String x : greet.greet1 ) {
	    if ( search ( x, s.sentence ) ) {
		return true;
	    }
	}
	for ( String x : greet.greet2 ) {
	    if ( search ( x, s.sentence ) ) {
		return true;
	    }
	}
	return false;
    }
    
    public Sentence greet( Sentence s ) {	
	Greeting greet = new Greeting();
	greet.generate("");
	return greet;
    }

    //Farewell
    public boolean isFarewell( Sentence s ) {
	Farewell farewell = new Farewell();
	for ( String x : farewell.farewell ) {
	    if ( search ( x, s.sentence ) ) {
		return true;
	    }
	}
	return false;
    }
    
    public Sentence farewell( Sentence s ) {	
	Farewell farewell = new Farewell();
	farewell.generate("");
	return farewell;
    }

    public Sentence respondRelated(Sentence s,
				   ArrayList<String> n) {
	Declarative d = new Declarative();
	Boolean b = false;
	String[] str = s.sentence.split(" ");
	//singular version of all nouns in input
	for(int i = 0; i < str.length; i++) {
	    HTMLParser singular = new HTMLParser( str[i] );
	    singular.startConnection();
	    singular.singular();
	    str[i] = singular.toString();
	    if ( str[i].equals("Do you even English?") ) {
		s.sentence = "Do you even English?";
		return s;
	    }
	    if(n.contains(str[i])) {
		d.generate(str[i]);
		b = true;
		break;
	    }
	}
	if (b == false) return randAnswer(s);
	else return d;
    }
    
    //if all else fails
    public Sentence randAnswer(Sentence s) {
	int rand = (int)(Math.random() * 2);
	if(rand == 0) s = new Declarative();
        else s = new Question();
	s.generate("");
	return s;
    }

    //and so, Kumar is born
    public void speak() {
	String s = "";
	Sentence input;
	begin();
	//completed before while loop so as to only perform once
        ParseCSV f = new ParseCSV("nouns.txt");
	ArrayList<String> nouns = f.words;
	while(! s.equals("exit")) {
	    Sentence response;
	    s = Keyboard.readString();
	    input = new Input(s);
	    if ( isGreet(input) ) response = greet(input);
	    else if(isQuestion(input.sentence)) response = basicQAnswer(input);
	    else if ( isFarewell(input) ) {
		response = farewell(input);
		terminate = true;
	    }
	    else response = respondRelated(input, nouns);
	    if(!s.equals("exit")
	       && !s.equals("help"))
		System.out.println(response);
	    if(s.equals("help")) help();
	    if (terminate) System.exit(0);
	}
    }

    public static void main(String[] args) {

        Reader kumar = new Reader();
	kumar.speak();

    }

}
