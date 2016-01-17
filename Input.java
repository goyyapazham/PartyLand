public class Input extends Sentence {

    public Input(String s) {
	sentence = s;
    }

    public String generate() {
	return sentence;
    }

    public void punctuate() {
	sentence += "";
    }

}
