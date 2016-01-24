import cs1.Keyboard;
import java.util.ArrayList;

public class Kumar {
    //for farewells terminate program afterwards
    private boolean terminate = false;
    private static String punctuation = ".,;:!?";
    
    ParseCSV f = new ParseCSV("objects.txt");
    ParseCSV g = new ParseCSV("foods.txt");
    ParseCSV h = new ParseCSV("colors.txt");
    ParseCSV j = new ParseCSV("animals.txt");
    private ArrayList<String> objects = f.words;
    private ArrayList<String> foods = g.words;
    private ArrayList<String> colors = h.words;
    private ArrayList<String> animals = j.words;

    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1)
		retStr += s.substring(i, i + 1);
	}
	return retStr;
    }
    
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

    /* ~~~~ QUESTION ~~~~
       - isQuestion()
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
	if ( search("what",input) || search("what's",input) ) return "what";
	if ( search("when",input) || search("when's",input) ) return "when";
	if ( search("where",input) || search("where's",input) ) return "where";
	if ( search("who",input) || search("who's",input) ) return "who";
	if ( search("why",input) || search("why's",input) ) return "why";
	if ( search("how",input) || search("how's",input) ) return "how";
	return "unknown";
    }
    
    public Sentence basicQAnswer(Sentence s) {
	String str;
	if ( search("meaning", s.sentence)
	     && search("of", s.sentence)
	     && search("life", s.sentence) ) {
	    str = "The meaning of life is 42.";
	}
	else if ( questionType(s.sentence).equals("what") ) {
	    What answer = new What(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("when") ) {
	    When answer = new When(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("where") ) {
	    Where answer = new Where(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("who") ) {
	    Who answer = new Who(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("why") ) {
	    Why answer = new Why(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("how") ) {
	    How answer = new How(s);
	    str = answer.toString();
	}
	else {
	    Sentence filler = new Filler();
	    str = filler.generate("");
	}
	s = new Filler();
	s.sentence = str;
	return s;
    }

    public static String removeSpace ( String input ) {
	int x = input.length();
	for ( int i = 0; i < x; i++ ) {
	    if ( input.substring(0,1).equals(" ") ) {
		input = input.substring(1);
	    }
	}
	return input;
    }
    public static boolean search( String searching, String input ) {
	input = input.toLowerCase();
	input = removeSpace(input);
	String[] inputArray = input.split(" ");
	if ( searching.equals(input) ) return true;
	for ( int i = 0; i < inputArray.length; i++) {
	    if ( inputArray.length == 1 && inputArray[i].equals("") ) return false;
	    if ( isPunc( inputArray[i].substring( inputArray[i].length() - 1 ) ) ) {
		inputArray[i] = inputArray[i].substring( 0, inputArray[i].length() - 1 );
	    }
	    if ( searching.equals( inputArray[i] ) ) return true;
	}
	return false;
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
	greet.generate(s.sentence);
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
    public static boolean isPunc( String searching ) {
     	for ( int i = 0; i < punctuation.length(); i++) {
	    if ( searching.equals( punctuation.substring( i, i+1 ) ) ) {
		return true;
	    }
	}
	return false;
    }

    public Sentence respondRelated(Sentence s) {
	Declarative d = new Declarative();
	Boolean b = false;
	String[] str = s.sentence.split(" ");
	str[str.length - 1] = strip(str[str.length - 1]);
	//singular version of all objects in input
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
		return answer;
	    }
	    if (colors.contains(str[i])) {
		s.sentence = str[i].substring(0, 1).toUpperCase()
		    + str[i].substring(1) + " looks lovely on you.";
		return s;
	    }
	    if (foods.contains(str[i])) {
		s.sentence = str[i].substring(0, 1).toUpperCase()
		    + str[i].substring(1) + " is nice, but I prefer "
		    + foods.get((int)(Math.random() * foods.size())) + ".";
		return s;
	    }
	    if (animals.contains(str[i])) {
		String animal1 = str[i];
		String animal2 =
		    animals.get((int)(Math.random() * animals.size()));
		s.sentence = "Once upon a time, a";
		if ("aeiouAEIOU".indexOf(animal1.substring(0, 1)) > -1)
		    s.sentence += "n";
		s.sentence += " " + str[i] + " befriended a";
		if ("aeiouAEIOU".indexOf(animal2.substring(0, 1)) > -1)
		    s.sentence += "n";
		s.sentence += " " + animal2 + ".";
		return s;
	    }
	    if (str[i].equals("Do you even English?")) {
		s.sentence = "Do you even English?";
		return s;
	    }
	    if(objects.contains(str[i])) {
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
	    else response = respondRelated(input);
	    if(!s.equals("exit")
	       && !s.equals("help"))
		System.out.println(response);
	    if(s.equals("help")) help();
	    if (terminate) System.exit(0);
	}
    }

    public static void main(String[] args) {

        Kumar kumar = new Kumar();
	kumar.speak();

    }

}
