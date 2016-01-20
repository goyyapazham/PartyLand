public abstract class Phrase {

    protected String phrase;
    protected String word;

    public String[] parseCSV(String filename) {
	ParseCSV f = new ParseCSV(filename);
	String[] retArr = new String[f.words.size()];
	for(int i = 0; i < f.words.size(); i++) {
	    retArr[i] = f.words.get(i);
	}
	return retArr;
    }

}
