public class Question extends Sentence {

    public String generate(String s) {
	//noun-phrase can be! anything!
	NounPhrase n = new NounPhrase();
	//the noun-phrase may need an article... (if the subject doesn't allow for articles, the addArt() method won't add an article)
	n.addArt();
	//indicative mood: verbs are never "conjugated"
	VerbPhrase v = new VerbPhrase(false);
	//the verb "do" is ONLY conjugated for singular third-person subjects
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
