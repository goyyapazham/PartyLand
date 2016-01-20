public abstract class Phrase {

    protected String phrase;
    protected String word;

    public String[] parseCSV(String filename) {
	ParseCSV f = new ParseCSV(filename);
	return f.words;
    }

}
