public class Input extends Sentence {

    public Input(String s) {
	//set sentence to the given string
	sentence = s;
    }

    public String generate(String s) {
	//return the given string
	return sentence;
    }

    public void punctuate() {
	//no punctuation because it shoul've been inputed by user
	sentence += "";
    }

}
