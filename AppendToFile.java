import java.io.*;

/* ~~~~~~~~~~~~~~~~~~~~~~~~
   Special thanks to this guy --
   http://alvinalexander.com/java/edu/qanda/pjqa00009.shtml
   -- for help with this
   ~~~~~~~~~~~~~~~~~~~~~~~~ */

public class AppendToFile {

    public void appendToFile(String input, boolean question) {

	BufferedWriter bw = null;

	try {
	    bw = new BufferedWriter(new FileWriter("fillers.txt", true));
	    String write = "\n";
	    if(question) write += "Let me ask you this: " + input.substring(0, 1).toLowerCase() + input.substring(1);
	    else write += input;
	    bw.write(write);
	    bw.flush();
	} catch (IOException e) {
	    System.out.println("OOPS");
	} finally {
	    if (bw != null)
		try {
		    bw.close();
		} catch (IOException f) {
		    //
		}
	}
    }
    
    /*
    public static void main(String[] args) {
	AppendToFile nala = new AppendToFile();
	nala.appendToFile("This is so cool!", false);
	nala.appendToFile("Watch out!", false);
	nala.appendToFile("Radical!", false);
    }
    */

}
