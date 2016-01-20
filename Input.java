public class Input extends Sentence {

    public Input(String s) {
	sentence = s;
    }

    public String generate(String s) {
	return sentence;
    }

    public void punctuate() {
	sentence += "";
    }

}
