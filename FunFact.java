public class FunFact extends Sentence {

    protected int type;

    public String generate() {
	type = (int)(Math.random() * 2);
	NounPhrase n = new NounPhrase(3, true);
	VerbPhrase v = new VerbPhrase(true);
	if(type == 0) {
	    sentence = "did you know that ";
	}
	else {
	    sentence = "here's a fun fact: ";
	}
	int pct = (int)(Math.random() * 101);
	sentence += pct + "% of " + n + " " + v;
	punctuate();
	capitalize();
	return sentence;
    }

    public void punctuate() {
	if(type == 0) sentence += "?";
	else sentence += "!";
    }

}
