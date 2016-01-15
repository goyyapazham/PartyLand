public abstract class Sentence {

    protected String sentence;

    public abstract String generate();
    public abstract void punctuate();

    public String toString() {
	return sentence;
    }

    public void capitalize() {
	String newSentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
	sentence = newSentence;
    }

}
