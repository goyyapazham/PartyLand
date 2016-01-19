import cs1.Keyboard;

public class Reader {

    public void begin() {
	System.out.println("Hello! Welcome. My name is Kumar.");
	help();
	System.out.println("Begin...");
	System.out.println();
    }

    public void help() {
	System.out.println("For starters, try: ");
	System.out.println("\tHow are you?");
	System.out.println("\tWhat is 4/3?");
	System.out.println("\tWhat is the meaning of life?");
    }

    public static boolean isQuestion(String input) {
	boolean retBool = false;
	if(input.substring(input.length() - 1).equals("?") ||
	   ! questionType(input).equals("unknown")) {
	    retBool = true;
	}
	return retBool;
    }
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
	else str = "That's an interesting question. I don't have an answer for you right now. But... ";
	s = new FunFact();
	str += s.generate();
	s.sentence = str;
	return s;
    }

    public Sentence randAnswer(Sentence s) {
	int rand = (int)(Math.random() * 2);
	if(rand == 0) s = new Declarative();
        else s = new Question();
	s.generate();
	return s;
    }

    public void speak() {
	String s = "";
	Sentence input;
	begin();
	while(! s.equals("exit")) {
	    Sentence response;
	    s = Keyboard.readString();
	    input = new Input(s);
	    if(isQuestion(input.sentence)) response = basicQAnswer(input);
	    else response = randAnswer(input);
	    if(!s.equals("exit")
	       && !s.equals("help"))
		System.out.println(response);
	    if(s.equals("help")) help();
	}
    }

    public static void main(String[] args) {

        Reader kumar = new Reader();
	kumar.speak();

    }

}
