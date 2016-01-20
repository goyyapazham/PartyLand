public class Farewell extends Sentence {
    
    String[] farewell = new String[]{"bye", "goodbye","see you later","have a nice day","regards","all the best"};
    
    public String generate(String s) {
	//choose a farewell message from farewell
	sentence = farewell[ (int) (Math.random() * farewell.length) ];
        punctuate();
	capitalize();
	return sentence;
    }

    public void punctuate( ) {
	if ( Math.random() < .5 ) sentence += ".";
	else sentence += "!";
    }

}
