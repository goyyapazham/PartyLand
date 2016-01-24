public abstract class Sentence {
    //instance variable to hold the sentence that is constructed
    protected String sentence;

    //abstract methods to be filled by each type of sentence
    public abstract String generate(String s);
    public abstract void punctuate();
    //to string returns the sentence
    public String toString() {
	return sentence;
    }
    //capitilizes first letter in each sentence
    public void capitalize() {
	sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1);
    }

}
