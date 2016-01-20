public class Declarative extends Sentence {

    public String generate(String s) {
	NounPhrase n;
	if(s.equals("")) n = new NounPhrase();
	else n = new NounPhrase(s);
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
