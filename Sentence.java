public abstract class Sentence {

    protected String sentence;

    public abstract String generate(String s);
    public abstract void punctuate();

    public String toString() {
	return sentence;
    }

    public void capitalize() {
	sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

}
