public class Declarative extends Sentence {

    public String generate() {
	//the noun-phrase can be anything!
	NounPhrase n = new NounPhrase();
	//if necessary, add article
	n.addArt();
	//verbs are only conjugated for singular third-person subjects
	VerbPhrase v = new VerbPhrase(n.subj == 3 && !(n.plural));
	sentence = n + " " + v;
	punctuate();
	capitalize();
	return sentence;
    }

    public void punctuate() {
	sentence += ".";
    }

}
