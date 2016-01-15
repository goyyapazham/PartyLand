public class Question extends Sentence {

    public String generate() {
	NounPhrase n = new NounPhrase();
	VerbPhrase v = new VerbPhrase(true);
	if(n.plural) {
	    sentence = "Do ";
	}
	else {
	    sentence = "Does ";
	}
	sentence += n + " " + v;
        punctuate();
	return sentence;
    }

    public void punctuate() {
	sentence += "?";
    }

}
