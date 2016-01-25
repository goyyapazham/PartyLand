import java.io.*;

/* ~~~~~~~~~~~~~~~~~~~~~~~~
   Special thanks to this guy --
   http://alvinalexander.com/java/edu/qanda/pjqa00009.shtml
   -- for help with this
   ~~~~~~~~~~~~~~~~~~~~~~~~ */

public class AppendToFile {

    public void appendToFile(String input, boolean question) {

	//copying data into a buffer (not written to disk)
	BufferedWriter bw = null;

	try {
	    bw = new BufferedWriter(new FileWriter("fillers.txt", true));
	    String write = "\n";
	    if(question) write += "Let me ask you this: " + input.substring(0, 1).toLowerCase() + input.substring(1);
	    else write += input;
	    bw.write(write); //writes input to text file
	    bw.flush(); //forces push to disk (see first comment re: buffer)
	} catch (IOException e) {
	    //throw Input/Output exception
	    System.out.println("OOPS");
	} finally {
	    //must close file!
	    if (bw != null) //which would mean the file was never opened
		try {
		    bw.close();
		} catch (IOException f) {
		    System.out.println("OOPS");
		}
	}
    }

    // ~~ test cases for debugging purposes ~~
    /*
    public static void main(String[] args) {
	AppendToFile nala = new AppendToFile();
	nala.appendToFile("This is so cool!", false);
	nala.appendToFile("Watch out!", false);
	nala.appendToFile("Radical!", false);
    }
    */

}
