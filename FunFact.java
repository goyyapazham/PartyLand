public class FunFact extends Sentence {

    protected int type;

    public String generate() {
	type = (int)(Math.random() * 2);
	NounPhrase n = new NounPhrase(true);
	VerbPhrase v = new VerbPhrase(true);
	if(type == 0) {
	    sentence = "Did you know that ";
	}
	else {
	    sentence = "Here's a fun fact: ";
	}
	int pct = (int)(Math.random() * 101);
	sentence += pct + "% of " + n + " " + v;
	punctuate();
	return sentence;
    }

    public void punctuate() {
	if(type == 0) sentence += "?";
	else sentence += "!";
    }

}
