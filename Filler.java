public class Filler extends Sentence {

    private String[] responses = parseCSV("responses.txt");
    private String[] fillers = parseCSV("fillers.txt");

    public String[] parseCSV(String filename) {
 	ParseCSV f = new ParseCSV(filename);
 	String[] retArr = new String[f.words.size()];
 	for(int i = 0; i < f.words.size(); i++) {
 	    retArr[i] = f.words.get(i);
	}
	return retArr;
    }
    
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
