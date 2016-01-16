public class Declarative extends Sentence {

    public String generate() {
	NounPhrase n = new NounPhrase();
	n.addArt();
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
