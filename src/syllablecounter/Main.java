package syllablecounter;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import stopwatch.Stopwatch;

/**
 * Read all the words from URL or File.
 * And called countSyllables to count all syllables in a word.
 * @author Issaee Srisomboon
 *
 */
public class Main {
	public static Stopwatch stopwatch = new Stopwatch();

	/**
	 * Read all the words from URL or File.
	 * @param filename
	 */
	public static List<String> getSource( String filename ) {
		List<String> list = new ArrayList<>();
		String word;
		try {
			stopwatch.start();
			URL url = new URL( filename );
			InputStream input = url.openStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader(input) );
			while ( ( word = reader.readLine()) != null ) {
				list.add( word );
			}
		} catch (IOException e) {
			System.err.println("Exception in getSource: " + e.getMessage());
		}
		return list;
	}
	
	/**
	 * Main class for printing out the result containing the source URL,
	 * all the syllables, all word and Elapsed time. Using countSyllables method.
	 * @param args
	 */
	public static void main (String[] args) {
		final String DICT_URL = "http://se.cpe.ku.ac.th/dictionary.txt";
		List<String> word = getSource(DICT_URL);
		WordCounter counter = new WordCounter();
		int allSyllables = 0;
		for ( String line : word ) {
			allSyllables += counter.countSyllables( line ); 
		}
		stopwatch.stop();
		System.out.println( "Reading words from " + DICT_URL );
		System.out.println( "Counted " + allSyllables + " syllables in " + word.size() + " words" );
		System.out.println( String.format( "Elapsed time: %.3f sec", stopwatch.getElapsed() )) ;

	}
}
