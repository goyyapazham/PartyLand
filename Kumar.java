import cs1.Keyboard;
import java.util.ArrayList;

public class Kumar {
    //for farewells terminate program afterwards
    private boolean terminate = false;
    private static String punctuation = ".,;:!?";

    //ArrayStrings for nouns, foods, colors, and animals
    private static ParseCSV f = new ParseCSV("objects.txt");
    private static ParseCSV g = new ParseCSV("foods.txt");
    private static ParseCSV h = new ParseCSV("colors.txt");
    private static ParseCSV j = new ParseCSV("animals.txt");
    private static ArrayList<String> objects = f.words;
    private static ArrayList<String> foods = g.words;
    private static ArrayList<String> colors = h.words;
    private static ArrayList<String> animals = j.words;
    
    //speaking info
    //prints out the info need to start
    public void begin() {
	System.out.println("\nHello! Welcome. My name is Kumar.");
	help();
	System.out.println("Begin...");
	System.out.println();
    }
    //prints out helpful tips
    public void help() {
	System.out.println("\nFor starters, try: ");
	System.out.println("  *  How are you?");
	System.out.println("  *  What is your favorite color?");
	System.out.println("  *  What is the meaning of life?\n");
	System.out.println("And don't forget your help commands: ");
	System.out.println("  *  help -- get this information again, at any time during our conversation");
	System.out.println("  *  exit -- if you want to leave without saying goodbye, you can... :'(");
	System.out.println("  *  you can also exit by typing a commonly used farwell!\n");
    }

    //string manipulation
    //removes punctuation form a string
    public static String strip(String s) {
	String retStr = "";
	for(int i = 0; i < s.length(); i++) {
	    //only transfer old chars to new string if they aren't punc
	    if (punctuation.indexOf(s.substring(i, i + 1)) == -1)
		retStr += s.substring(i, i + 1);
	}
	return retStr;
    }
    //searches for a string inside a string by dividing the input string into an Array 
    public static boolean search( String searching, String input ) {
	//get input string ready by making it lowercase and removing leading spaces
	input = input.toLowerCase();
	input = removeSpace(input);
	//split input on spaces and store in InputArray
	String[] inputArray = input.split(" ");
	//if the input equals search that means its in there so return true
	if ( searching.equals(input) ) return true;
	//loop through input array to look for the searching string
	for ( int i = 0; i < inputArray.length; i++) {
	    if ( inputArray.length == 1 && inputArray[i].equals("") )
		return false;
	    //remove any punctuation found on words
	    //i.e. the dog sleeps, walks and plays removes , from sleeps,
	    if (isPunc(inputArray[i].substring(inputArray[i].length() - 1))) {
		inputArray[i] = inputArray[i].substring( 0, inputArray[i].length() - 1 );
	    }
	    if ( searching.equals( inputArray[i] ) ) return true;
	}
	return false;
    }
    //searches for a phrase in the input but does not divide it up into Array
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
    //tells if something is a punctuation mark or not
    public static boolean isPunc( String searching ) {
	//loop through the string punctuation to check if searching equals one of them
     	for ( int i = 0; i < punctuation.length(); i++) {
	    if ( searching.equals( punctuation.substring( i, i+1 ) ) ) {
		return true;
	    }
	}
	return false;
    }
    //removes the leading spaces
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
    //checks if input is a question
    public static boolean isQuestion(String input) {
	boolean retBool = false;
	// checks for "?" and/or key words
	if(input.substring(input.length() - 1).equals("?") ||
	   ! questionType(input).equals("unknown") ||
	   irrVerbStructure(input, "do") ||
	   irrVerbStructure(input, "are")) {
	    retBool = true;
	}
	return retBool;
    }
    //tells what type of question the input is
    public static String questionType( String input ) {
	//checks the 5Ws and How
	if ( search("what",input) || search("what's",input) ) return "what";
	if ( search("when",input) || search("when's",input) ) return "when";
	if ( search("where",input) || search("where's",input) ) return "where";
	if ( search("who",input) || search("who's",input) ) return "who";
	if ( search("why",input) || search("why's",input) ) return "why";
	if ( search("how",input) || search("how's",input) ) return "how";
	//if none of above return none
	return "unknown";
    }

    //answers questions by redirecting them to class of question type
    public Sentence basicQAnswer(Sentence s) {
	String str;
	//meaning of life
	if ( search("meaning", s.sentence)
	     && search("of", s.sentence)
	     && search("life", s.sentence) ) {
	    str = "The meaning of life is 42.";
	}
	//if sentence has do
	else if (irrVerbStructure(s.sentence, "do")) {
	    str = irrVerbResponse(s.sentence, "do");
	}
	//if it has are
	else if (irrVerbStructure(s.sentence, "are")) {
	    str = irrVerbResponse(s.sentence, "are");
	}
	//what
	else if ( questionType(s.sentence).equals("what") ) {
	    Sentence answer = new What(s);
	    str = answer.toString();
	}
	//when
	else if ( questionType(s.sentence).equals("when") ) {
	    Sentence answer = new When(s);
	    str = answer.toString();
	}
	//where
	else if ( questionType(s.sentence).equals("where") ) {
	    Sentence answer = new Where(s);
	    str = answer.toString();
	}
	//who
	else if ( questionType(s.sentence).equals("who") ) {
	    Sentence answer = new Who(s);
	    str = answer.toString();
	}
	//why
	else if ( questionType(s.sentence).equals("why") ) {
	    Sentence answer = new Why(s);
	    str = answer.toString();
	}
	//how
	else if ( questionType(s.sentence).equals("how") ) {
	    Sentence answer = new How(s);
	    str = answer.toString();
	}
	//if it use bored or boring generate a FunFact
	else if ( s.sentence.indexOf("bored") > -1 ||
		  s.sentence.indexOf("boring") > -1 ||
		  (int)(Math.random() * 35) == 0 ) {
	    FunFact answer = new FunFact();
	    str = answer.generate("");
	}
	//if not found return a filler
	else {
	    Filler filler = new Filler();
	    str = filler.generate(s.sentence);
	}
	s = new Filler();
	s.sentence = str;
	return s;
    }
    //checks if it involves an irrVerbStructure 
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
    //responds to an irrVerbStructure
    public static String irrVerbResponse(String s, String type){
	String s1, s2, s3;
	if(type.equals("do")) {
	    s1 = "do";
	    s3 = s1;
	    s2 = "does";
	}
	else {
	    s1 = "are";
	    s3 = "am";
	    s2 = "is";
	}
	String[] L = s.split(" ");
	L[L.length - 1] = strip(L[L.length - 1]);
	if(L.length > 2) {
	    HTMLParser singular = new HTMLParser( L[2] );
	    singular.startConnection();
	    singular.singular();
	    L[2] = singular.toString();
	}
	if (L.length > 1) {
	    if(L[1].equals("I")) return "You " + s1 + ".";
	    else if (L[1].equals("you")) return "I " + s3 + ".";
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
	    else if (L[1].equals("they") ||
		     L[1].equals("the") && L.length > 2 && 
		     (objects.contains(L[2]) ||
		      animals.contains(L[2]) ||
		      colors.contains(L[2]) ||
		      foods.contains(L[2])))
		return "They " + s1 + ".";
	    else return "I don't understand.";
	}
	else return "I don't understand.";
    }
    
    //Greeting
    //check to see if input is greeting
    public boolean isGreet( Sentence s ) {
	Greeting greet = new Greeting();
	//checks if it has the simple greetings
	for ( String x : greet.greet1 ) {
	    if ( search ( x, s.sentence ) ) {
		return true;
	    }
	}
	//checks for the more complex greetings
	for ( String x : greet.greet2 ) {
	    if ( searchPhrase ( x, s.sentence ) ) {
		return true;
	    }
	}
	//checks for answers to complex greetings
	for ( String x : greet.answer ) {
	    if ( searchPhrase ( x, s.sentence ) ) {
		return true;
	    }
	}
	return false;
    }
    //creates a response for greeting
    public Sentence greet( Sentence s ) {	
	Greeting greet = new Greeting();
	//generate a greeting using the input s
	greet.generate(s.sentence);
	return greet;
    }

    //Farewell
    //checks if it is a Farewell
    public boolean isFarewell( Sentence s ) {
	Farewell farewell = new Farewell();
	//searches the instance variable with farewells to see if they are present
	for ( String x : farewell.farewell ) {
	    if ( search ( x, s.sentence ) ) return true;
	}
	return false;
    }
    //creates a farewell response
    public Sentence farewell( Sentence s ) {	
	Farewell farewell = new Farewell();
	//generate farewell doesn't need the input because it already knows it is a farewell
	farewell.generate("");
	return farewell;
    }

    //checks if it is a tease
    public boolean isTease(Sentence s) {
	//split sentence on space
	String[] str = s.sentence.split(" ");
	//strip punctuation
	str[str.length - 1] = strip(str[str.length - 1]);
	//loop through the str array to look for keywords
	for(int i = 0; i < str.length; i++) {
	    if(str[i].equals("robot") ||
	       str[i].equals("person") ||
	       str[i].equals("bot") ||
	       str[i].equals("real") ||
	       str[i].equals("mind") ||
	       str[i].equals("conscious") ||
	       str[i].equals("think") ||
	       str[i].equals("thinks") ||
	       str[i].equals("thinking") ||
	       str[i].equals("human")) {
		return true;
	    }
	}
	return false;
    }
    //response if it is a tease
    public Sentence tease(Sentence s) {
	int rand = (int)(Math.random() * 4);
	//returns one of following based on rand
	if(rand == 0)
	    s.sentence = "I'm very much real.";
	else if(rand == 1)
	    s.sentence = "I hate it when folks say I'm JUST a robot.";
	else if(rand == 2)
	    s.sentence = "I think perfectly well, thank you very much!";
	else
	    s.sentence = "Hey! Robots have feelings too!";
	return s;
    }
    //checks input to see if it says Kumar in the input
    public boolean hasKumar(Sentence s) {
	//split on spaces
	String[] str = s.sentence.split(" ");
	//remove punctuation
	str[str.length - 1] = strip(str[str.length - 1]);
	//loop through inputs to look for Kumar
	for(int i = 0; i < str.length; i++) {
	    if(str[i].equals("Kumar"))
		return true;
	}
	return false;
    }
    //repsonse to finding Kumar in input
    public Sentence selfRecognition(Sentence s) {
	s.sentence = "Hey! That's me!";
	return s;
    }
    //responds using nouns in input
    public Sentence respondRelated(Sentence s) {
	//make a declaritive and boolean b to show if it is used or not
	Declarative d = new Declarative();
	Boolean b = false;
	//counts number of words not found on Dictionary.com
	int count = 0;
	//split input by spaces and store in string array
	String[] str = s.sentence.split(" ");
	str[str.length - 1] = strip(str[str.length - 1]);
	//singular version of all objects in input
	for(int i = 0; i < str.length; i++) {
	    //removes punctuation in word
	    if ( isPunc( str[i].substring( str[i].length() - 1 ) ) ) {
		str[i] = str[i].substring( 0, str[i].length() - 1 );
	    }
	    //use HTMLParser to make words singular
	    HTMLParser singular = new HTMLParser( str[i] );
	    singular.startConnection();
	    singular.singular();
	    str[i] = singular.toString();
	    //if words is unknown increase count
	    if (singular.getUnknown()) {
		count++;
	    }
	    //if word is a noun it makes a declarative based on it
	    if(objects.contains(str[i])) {
		d.generate(str[i]);
		b = true;
		break;
	    }
	    //if no words are real return do you even english
	    if( count == str.length ) {
		s.sentence = "Do you even English?";
		return s;
	    }
	}
	//if declarative is not made return a Filler sentence
	if (!b) {
	    Filler f = new Filler();
	    f.sentence = f.generate(s.sentence);
	    return f;
	}
	//if it is made return the declarative
	else return d;
    }
    //checks for colors, animals, and food
    public boolean isSpecific( Sentence s ) {
	//splits on space
	String[] str = s.sentence.split(" ");
	//remove punctuation
	str[str.length - 1] = strip(str[str.length - 1]);
	//singular version of all objects in input
	for(int i = 0; i < str.length; i++) {
	    //remove punctuation
	    if ( isPunc( str[i].substring( str[i].length() - 1 ) ) ) {
		str[i] = str[i].substring( 0, str[i].length() - 1 );
	    }
	    //use HTMLParser to make words singular
	    HTMLParser singular = new HTMLParser( str[i] );
	    singular.startConnection();
	    singular.singular();
	    str[i] = singular.toString();
	    //if its a color, food, animal return true
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
    //respond to colors, foods, and animals
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
	    //if it uses the words color, animal, or food use What class to answer question
	    if ( str[i].equals("color") ||
		 str[i].equals("animal") ||
		 str[i].equals("food") ) {
		What answer = new What(s);
		return answer;
	    }
	    //if it uses a specific color return that the color looks good on person
	    if (colors.contains(str[i])) {
		s.sentence = str[i].substring(0, 1).toUpperCase()
		    + str[i].substring(1) + " looks lovely on you.";
		return s;
	    }
	    //if specific food return food + is nice but i prefer + another food 
	    if (foods.contains(str[i])) {
		s.sentence = str[i].substring(0, 1).toUpperCase()
		    + str[i].substring(1) + " is nice, but I prefer "
		    + foods.get((int)(Math.random() * foods.size())) + ".";
		return s;
	    }
	    //responds to specific animal terms using a story
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
	//print out the beginning commands and help menu
	begin();
	//while the person doesn't exit
	while(! s.equals("exit")) {
	    //set the input to be a new sentence
	    Sentence response;
	    s = Keyboard.readString();
	    input = new Input(s);
	    //checks for his name
	    if ( hasKumar(input) ) response = selfRecognition(input);
	    //check if it is a greeting
	    else if ( isGreet(input) ) response = greet(input);
	    //check if it about colors, foods, animals
	    else if( isSpecific(input) ) response = specific(input);
	    //check if it is a question 
	    else if(isQuestion(input.sentence)) response = basicQAnswer(input);
	    //check if has the tease
	    else if( isTease(input) ) response = tease(input);
	    //check if it is a farewell and set terminate to true if it is
	    else if ( isFarewell(input) ) {
		response = farewell(input);
		terminate = true;
	    }
	    //if none of above use a noun in input to make a related sentence to respond with
	    else response = respondRelated(input);
	    //if the input is not exit or help return the response
	    if(!s.equals("exit")
	       && !s.equals("help"))
		System.out.println(response);
	    //if the input is help return help menu
	    if(s.equals("help")) help();
	    //if farewell was said terminate program
	    if (terminate) System.exit(0);
	}
    }

    public static void main(String[] args) {
	//Kumar is born and speaks
	Kumar kumar = new Kumar();
	kumar.speak();

    }

}
