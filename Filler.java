public class Filler extends Sentence {

    private String[] responses = new String[]{"Hmm...interesting.", "Awkward silence...", "Carpe diem.", "For your information, I am a staunch feminist.", "You're pretty."};

    public String generate() {
	return responses[(int)(Math.random() * responses.length)];
    }

    public void punctuate() {
    }

}
