public class Filler extends Sentence {

    //String array for responses and fillers created from
    //the text files which are parsed using the ParseCSV class
    private String[] responses = parseCSV("responses.txt");
    private String[] fillers = parseCSV("fillers.txt");

    //parses a CSV file to pull data and assign it to a String Array
    public String[] parseCSV(String filename) {
	//class ParseCSV will make a String Array out of the file contents
	//in an instance variable
 	ParseCSV f = new ParseCSV(filename);
	//make retArr with length of contents in file already found by
	//ParseCSV class
	String[] retArr = new String[f.words.size()];
	//copy the instance variable in f to the retArr
	for(int i = 0; i < f.words.size(); i++) {
 	    retArr[i] = f.words.get(i);
	}
	return retArr;
    }
    
    public String generate(String s) {
	AppendToFile a = new AppendToFile();
	//if the input is a question set s to one of the random responses
	if(Kumar.isQuestion(s)) {
	    a.appendToFile(s, true);
	    if((int)(Math.random() * 2) == 0)
		s = responses[(int)(Math.random() * responses.length)];
	    else {
		s = "The answer is ";
		if((int)(Math.random() * 2) == 0)
		    s += "in the question. Look again.";
		else
		    s += "within you. Look deeper.";
	    }
	}
	//else set s to one of the random fillers
	else {
	    a.appendToFile(s, false);
	    s = fillers[(int)(Math.random() * fillers.length)];
	}
	return s;
    }

    public void punctuate() {
    }

}
