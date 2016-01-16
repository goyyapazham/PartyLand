public class Question extends Sentence {

    public String generate() {
	NounPhrase n = new NounPhrase();
	n.addArt();
	VerbPhrase v = new VerbPhrase(false);
	if(n.subj < 3 || n.plural) {
	    sentence = "do ";
	}
	else {
	    sentence = "does ";
	}
	sentence += n + " " + v;
        punctuate();
	capitalize();
	return sentence;
    }

    public void punctuate() {
	sentence += "?";
    }

}
