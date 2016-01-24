public class Filler extends Sentence {

    private String[] responses = parseCSV("responses.txt");
    private String[] fillers = parseCSV("fillers.txt");
    
    public String generate(String s) {
	if(Kumar.isQuestion(s))
	    s = responses[(int)(Math.random() * responses.length)];
	else
	    s = fillers[(int)(Math.random() * fillers.length)];
	return s;
    }

    public void punctuate() {
    }

}
