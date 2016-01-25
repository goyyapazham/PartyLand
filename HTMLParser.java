import java.net.URLConnection;
import java.net.URL;
import java.util.Scanner;
import java.lang.Throwable;
//special thanks to Java API and stackoverflow for helping my learn how to do this
public class HTMLParser {

    //HTML from site
    protected String HTML;
    //word being searched
    protected String word;
    //url of side
    protected String URL;
    //true when word is not found
    protected boolean unknown;
    //pluralize
    protected String keyHTML = "<span class=\"dbox-pg\">noun</span>, <span class=\"dbox-pg\">plural </span>";
    //singular
    protected String keyHTML2 = "<h1 class=\"head-entry\"><span class=\"me\" data-syllable=\"";

    public HTMLParser( String w ) {
	HTML = "";
	word = w;
	URL = "http://dictionary.reference.com/browse/" + word.toLowerCase() + "?s=t";
	unknown = false;
    }

    public String toString() {
	//returns the pluralized or singularized word
	return word;
    }

    //opens connection to website and pulls data
    public void startConnection() {
	URLConnection connection = null;
	try {
	    //opens the connection to the URL
	    connection = new URL(URL).openConnection();
	    //Scanner with the InputStream from the website
	    Scanner scanner = new Scanner(connection.getInputStream());
	    scanner.useDelimiter("\\Z");
	    //go through the input stream and set the HTML to it
	    HTML = scanner.next();
	}
	//if connection failed because word does not exist
	catch ( Exception e ) {
	    unknown = true;
	}
    }
    //returns if word is unknown
    public boolean getUnknown() {
	return unknown;
    }

    //searches for the string searching in the input and returns the index where the string begins
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
    //used to find plural word
    public void plural() {
	//if the keyHTML is found
	if ( search( keyHTML, HTML ) > 0 ) {
	    //x is the index at where the key HTML starts
	    int x = search( keyHTML, HTML );
	    //pluralLocation is where the key HTML tag ends and the plural word begins
	    int pluralLocation = search( ">", HTML.substring(x) ) + x;
	    //pluralLocationEnd is where the plural word ends and the next tag begins
	    int pluralLocationEnd = search( "<", HTML.substring(pluralLocation) ) + pluralLocation - 2;
	    //the word is set to the HTML substring where the plural word is located
	    word = HTML.substring( pluralLocation, pluralLocationEnd );
	}
    }
    public void singular() {
	//if the keyHTML is found
	if ( search( keyHTML2, HTML ) > 0 ) {
	    //x is the index at where the key HTML starts
	    int x = search( keyHTML2, HTML );
	    //singularLocation is where the key HTML tag ends and the singular word begins
	    int singularLocation = search( ">", HTML.substring(x) ) + x;
	    //singularLocationEnd is where the singular word ends and the next tag begins
	    int singularLocationEnd = search( "<", HTML.substring(singularLocation) ) + singularLocation - 1;
	    //the word is set to the HTML substring where the singular word is located
	    word = HTML.substring( singularLocation, singularLocationEnd );
	}
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
