public abstract class Phrase {

    protected String phrase;
    protected String word;

    public String[] parseCSV(String filename) {
	ParseCSV f = new ParseCSV(filename);
	String[] retArr = new String[f.lines.size()];
	for(int i = 0; i < f.lines.size(); i++) {
	    retArr[i] = f.lines.get(i);
	}
	return retArr;
    }

}
