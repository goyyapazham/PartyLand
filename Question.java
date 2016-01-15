public class Question extends Sentence {

    public String generate() {
	NounPhrase n = new NounPhrase();
	VerbPhrase v = new VerbPhrase(true);
	if(n.plural) {
	    sentence = "do ";
	}
	else {
	    sentence = "does ";
	}
	sentence += n + " " + v;
        capitalize();
        punctuate();
	return sentence;
    }

    public void punctuate() {
	sentence += "?";
    }

}
