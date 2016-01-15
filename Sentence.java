public abstract class Sentence {

    protected String sentence;

    public abstract String generate();
    public abstract void punctuate();

    public String toString() {
	return sentence;
    }

}
