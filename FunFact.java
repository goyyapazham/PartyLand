public class FunFact extends Sentence {

    protected int type;

    public String generate() {
	type = (int)(Math.random() * 2);
	//fun facts /must/ be about third-person nouns, must be plural
	NounPhrase n = new NounPhrase(3, true);
	//must /not/ be conjugated
	VerbPhrase v = new VerbPhrase(false);
	//two types of fun-fact sentence structures
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
	//two types of fun-fact sentence structures
	if(type == 0) sentence += "?";
	else sentence += "!";
    }

}
