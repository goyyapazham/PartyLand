import java.net.URLConnection;
import java.net.URL;
import java.util.Scanner;

public class HTMLParser {

    protected String HTML;
    protected String word;
    protected String keyHTML = "<span class=\"dbox-pg\">noun</span>, <span class=\"dbox-pg\">plural </span>";
    protected String keyHTML2 = "<h1 class=\"head-entry\"><span class=\"me\" data-syllable=\"";
    public HTMLParser( String w ) {
	HTML = "";
	word = w;
    }

    public String toString() {
	return word;
    }
    
    public void startConnection() {
	URLConnection connection = null;
	try {
	    connection =  new URL("http://dictionary.reference.com/browse/" + word.toLowerCase() + "?s=t").openConnection();
	    Scanner scanner = new Scanner(connection.getInputStream());
	    scanner.useDelimiter("\\Z");
	    HTML = scanner.next();
	}
	catch ( Exception e ) {
	    word = "Do you even English?";
		
	}
    }

    public int search( String searching, String input ) {
	int length = searching.length();
	input = input.toLowerCase();
	for ( int i = 0; i < input.length() - length; i++) {
	    if ( input.substring(i,i+length).equals(searching) ) {
		return i + length;
	    }
	}
	return -1;
    }
    
    public void plural() {
	if ( search( keyHTML, HTML ) > 0 ) {
	    int x = search( keyHTML, HTML );
	    int pluralLocation = search( ">", HTML.substring(x) ) + x;
	    int pluralLocationEnd = search( "<", HTML.substring(pluralLocation) ) + pluralLocation - 2;
	    word = HTML.substring( pluralLocation, pluralLocationEnd );
	}
    }
    public void singular() {
	if ( search( keyHTML2, HTML ) > 0 ) {
	    int x = search( keyHTML2, HTML );
	    int singularLocation = search( ">", HTML.substring(x) ) + x;
	    int singularLocationEnd = search( "<", HTML.substring(singularLocation) ) + singularLocation - 1;
	    word = HTML.substring( singularLocation, singularLocationEnd );
	}
    }
	
    public static void main( String[] args ) {
	/*
	HTMLParser pardeep = new HTMLParser("babies");
	pardeep.startConnection();
	pardeep.singular();
	System.out.println(pardeep);
	*/
    }
}
