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
	if(input.substring(input.length() - 1).equals("?")) {
	    retBool = true;
	}
	return retBool;
    }

    public Sentence basicQAnswer(Sentence s) {
	String str = "That's an interesting question. I don't have an answer for you right now. But... ";
	s = new FunFact();
	str += s.generate();
	s.sentence = str;
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
	    else response = input;
	    if(!s.equals("exit")) System.out.println(response);
	    if(s.equals("help")) help();
	}
    }

    public static void main(String[] args) {

        Reader kumar = new Reader();
	kumar.speak();

    }

}
