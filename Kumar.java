import cs1.Keyboard;
import java.util.ArrayList;

public class Kumar {
    //for farewells terminate program afterwards
    private boolean terminate = false;
    private static String punctuation = ".,;:!?";
    
    private static ParseCSV f = new ParseCSV("objects.txt");
    private static ParseCSV g = new ParseCSV("foods.txt");
    private static ParseCSV h = new ParseCSV("colors.txt");
    private static ParseCSV j = new ParseCSV("animals.txt");
    private static ArrayList<String> objects = f.words;
    private static ArrayList<String> foods = g.words;
    private static ArrayList<String> colors = h.words;
    private static ArrayList<String> animals = j.words;
    
    //speaking info
    public void begin() {
	System.out.println("\nHello! Welcome. My name is Kumar.");
	help();
	System.out.println("Begin...");
	System.out.println();
    }

    public void help() {
	System.out.println("\nFor starters, try: ");
	System.out.println("  *  How are you?");
	System.out.println("  *  What is your favorite color?");
	System.out.println("  *  What is the meaning of life?\n");
	System.out.println("And don't forget your help commands: ");
	System.out.println("  *  help -- get this information again, at any time during our conversation");
	System.out.println("  *  exit -- if you want to leave without saying goodbye, you can... :'(\n");
    }

    //string manipulation
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    //only transfer old chars to new string if they aren't punc
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1)
		retStr += s.substring(i, i + 1);
	}
	return retStr;
    }

    public static boolean search( String searching, String input ) {
	input = input.toLowerCase();
	input = removeSpace(input);
	String[] inputArray = input.split(" ");
	if ( searching.equals(input) ) return true;
	for ( int i = 0; i < inputArray.length; i++) {
	    if ( inputArray.length == 1 && inputArray[i].equals("") )
		return false;
	    if (isPunc(inputArray[i].substring(inputArray[i].length() - 1))) {
		inputArray[i] = inputArray[i].substring( 0, inputArray[i].length() - 1 );
	    }
	    if ( searching.equals( inputArray[i] ) ) return true;
	}
	return false;
    }
    public boolean searchPhrase( String searching, String input ) {
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
    
    public static boolean isPunc( String searching ) {
     	for ( int i = 0; i < punctuation.length(); i++) {
	    if ( searching.equals( punctuation.substring( i, i+1 ) ) ) {
		return true;
	    }
	}
	return false;
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

    //for response mechanism

    /* ~~~~ QUESTION ~~~~
       - isQuestion()
       - questionType()
       - basicQAnswer()
       ~~~~~~~~~~~~~~~~~~ */
    
    public static boolean isQuestion(String input) {
	boolean retBool = false;
	if(input.substring(input.length() - 1).equals("?") ||
	   ! questionType(input).equals("unknown") ||
	   irrVerbStructure(input, "do") ||
	   irrVerbStructure(input, "are")) {
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
	else if (irrVerbStructure(s.sentence, "do")) {
	    str = irrVerbResponse(s.sentence, "do");
	}
	else if (irrVerbStructure(s.sentence, "are")) {
	    str = irrVerbResponse(s.sentence, "are");
	}
	else if ( questionType(s.sentence).equals("what") ) {
	    Sentence answer = new What(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("when") ) {
	    Sentence answer = new When(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("where") ) {
	    Sentence answer = new Where(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("who") ) {
	    Sentence answer = new Who(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("why") ) {
	    Sentence answer = new Why(s);
	    str = answer.toString();
	}
	else if ( questionType(s.sentence).equals("how") ) {
	    Sentence answer = new How(s);
	    str = answer.toString();
	}
	else if ( s.sentence.indexOf("bored") > -1 ||
		  s.sentence.indexOf("boring") > -1 ||
		  (int)(Math.random() * 35) == 0 ) {
	    FunFact answer = new FunFact();
	    str = answer.generate("");
	}
	else {
	    Filler filler = new Filler();
	    str = filler.generate(s.sentence);
	}
	s = new Filler();
	s.sentence = str;
	return s;
    }

    public static boolean irrVerbStructure(String s, String verb) {
	String v1, v2, v3, v4;
	if(verb.equals("do")) {
	    v1 = "Do";
	    v2 = "do";
	    v3 = "Does";
	    v4 = "does";
	}
	else {
	    v1 = "Are";
	    v2 = "are";
	    v3 = "Is";
	    v4 = "is";
	}
	String[] str = s.split(" ");
	str[str.length - 1] = strip(str[str.length - 1]);
	if (str.length > 1) {
	    HTMLParser singular1 = new HTMLParser( str[1] );
	    singular1.startConnection();
	    singular1.singular();
	    if(!str[1].equals("I"))
		str[1] = singular1.toString();
	    if(( (str[0].equals(v1) || str[0].equals(v2)) &&
		 (str[1].equals("I")
		  || str[1].equals("you")
		  || str[1].equals("we")
		  || str[1].equals("they") ) ||
		 (str[0].equals(v3) || str[0].equals(v4)) &&
		 (str[1].equals("he")
		  || str[1].equals("she")
		  || str[1].equals("it")) ) )
		return true;
	}
	if (str.length > 2) {
	    HTMLParser singular2 = new HTMLParser( str[2] );
	    singular2.startConnection();
	    singular2.singular();
	    str[2] = singular2.toString();
	    if (( objects.contains(str[1])
		  || animals.contains(str[1])
		  || colors.contains(str[1])
		  || foods.contains(str[1])
		  || str[1].equals("the") &&
		  (objects.contains(str[2])
		   || animals.contains(str[2])
		   || colors.contains(str[2])
		   || foods.contains(str[2]))) ||
		(str[1].equals("the") && objects.contains(str[2])))
		return true;
	    return false;
	}
	return false;
    }

    public static String irrVerbResponse(String s, String type){
	String s1, s2, s3, s4;
	if(type.equals("do")) {
	    s1 = "do";
	    s2 = "does";
	}
	else {
	    s1 = "are";
	    s2 = "is";
	}
	String[] L = s.split(" ");
	L[L.length - 1] = strip(L[L.length - 1]);
	if(L[1].equals("I")) return "You " + s1 + ".";
	else if (L[1].equals("you")) return "I " + s1 + ".";
	else if (L[1].equals("we")) return "We " + s1 + ".";
	else if (L[1].equals("he")) return "He " + s2 + ".";
	else if (L[1].equals("she")) return "She " + s2 + ".";
	else if (L[1].equals("it") ||
		 L[1].equals("the") &&
		 (objects.contains(L[2]) ||
		  animals.contains(L[2]) ||
		  colors.contains(L[2]) ||
		  foods.contains(L[2])))
	    return "It " + s2 + ".";
	else return "They " + s1 + ".";
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
	    if ( searchPhrase ( x, s.sentence ) ) {
		return true;
	    }
	}
	for ( String x : greet.answer ) {
	    if ( searchPhrase ( x, s.sentence ) ) {
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

    public Sentence respondRelated(Sentence s) {
	Declarative d = new Declarative();
	Boolean b = false;
	//counts number of words not found on
	int count = 0;
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
	    if (singular.getUnknown()) {
		count++;
	    }
	    if(objects.contains(str[i])) {
		d.generate(str[i]);
		b = true;
		break;
	    }
	    if( count == str.length ) {
		s.sentence = "Do you even English?";
		return s;
	    }
	}
	if (!b) {
	    Filler f = new Filler();
	    f.sentence = f.generate(s.sentence);
	    return f;
	}
	else return d;
    }
    
    public boolean isSpecific( Sentence s ) {
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
		 str[i].equals("food") ||
		 colors.contains(str[i]) ||
		 foods.contains(str[i]) ||
		 animals.contains(str[i]) ) {
		return true;
	    }
	}
	return false;
    }
    public Sentence specific( Sentence s ) {
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
	}
	return s;
    }

    //and so, Kumar is born
    public void speak() {
	String s = "";
	Sentence input;
	begin();
	while(! s.equals("exit")) {
	    Sentence response;
	    s = Keyboard.readString();
	    input = new Input(s);
	    if ( isGreet(input) ) response = greet(input);
	    else if( isSpecific(input) ) response = specific(input);
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
