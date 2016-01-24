public abstract class Phrase {

    protected String phrase;
    protected String word;

    //method to access files with nouns and verbs to be
    //inherited by NounPhrase and VerbPhrase
    public String[] parseCSV(String filename) {
	ParseCSV f = new ParseCSV(filename);
	String[] retArr = new String[f.words.size()];
	for(int i = 0; i < f.words.size(); i++) {
	    retArr[i] = f.words.get(i);
	}
	return retArr;
    }

}
