import java.net.URLConnection;
import java.net.URL;
import java.util.Scanner;
import java.lang.Throwable;

public class HTMLParser {

    protected String HTML;
    protected String word;
    protected String URL;
    //pluralize
    protected String keyHTML = "<span class=\"dbox-pg\">noun</span>, <span class=\"dbox-pg\">plural </span>";
    //singular
    protected String keyHTML2 = "<h1 class=\"head-entry\"><span class=\"me\" data-syllable=\"";
    //suggestions
    protected String keyHTML3 = "<span class=\"me\" data-syllable=\"";

    public HTMLParser( String w ) {
	HTML = "";
	word = w;
	URL = "http://dictionary.reference.com/browse/" + word.toLowerCase() + "?s=t";
    }

    public String toString() {
	return word;
    }
    
    public void startConnection() {
	URLConnection connection = null;
	try {
	    connection = new URL(URL).openConnection();
	    //System.out.println(connection);
	    Scanner scanner = new Scanner(connection.getInputStream());
	    //System.out.println("error2");
	    scanner.useDelimiter("\\Z");
	    //System.out.println("error3");
	    HTML = scanner.next();
	    //System.out.println("error4");
	}
	catch ( Exception e ) {
	    /*
	    System.out.println("Did you mean?");
	    URL = "http://dictionary.reference.com/misspelling?term=" + word.toLowerCase() + "&s=t";
	    System.out.println(URL);
	    startConnection();
	    System.out.println("Success!");
	    suggestion();
	    System.out.println(word);
	    URL =  "http://dictionary.reference.com/browse/" + word.toLowerCase() + "?s=t";
	    System.out.println(URL);
	    startConnection();
	    */
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

    public void suggestion() {
	if ( search( keyHTML3, HTML ) > 0 ) {
	    int x = search( keyHTML3, HTML );
	    int suggestionLocation = search( ">", HTML.substring(x) ) + x;
	    int suggestionLocationEnd = search( "<", HTML.substring(suggestionLocation) ) + suggestionLocation - 1;
	    word = HTML.substring( suggestionLocation, suggestionLocationEnd );
	    System.out.println(word);
	}
	System.out.println(word);
    }
    
    public static void main( String[] args ) {
	/*
	  (HTMLParser pardeep = new HTMLParser("colors");
	  pardeep.startConnection();
	  pardeep.singular();
	  System.out.println(pardeep);
	*/    
    }
}
