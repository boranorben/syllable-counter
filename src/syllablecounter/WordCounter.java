package syllablecounter;

/**
 * Class for counting syllables in a word.
 * @author Issaree Srisomboon
 *
 */
public class WordCounter {
	private final State START = new StartState();
	private final State SINGLE_VOWEL = new SingleVowelState();
	private final State MULTIVOWEL = new MultiVowelState();
	private final State CONSONANT = new ConsonantState();
	private final State HYPHEN = new HyphenState();
	private final State NONWORD = new NonWordState();
	private State state; // the current state
	private int syllableCount = 0;
	private int index;
	private int last;
	
	/**
	 * For changing the state.
	 * @param new state
	 */
	public void setState ( State newstate ) {
		state = newstate;
	}
	
	/**
	 * Return the number of syllables in the String.
	 * If the String is not a word, then return 0.
	 * @param word in String
	 * @return number of syllables in the String or return 0 when it is not a word
	 */
	public int countSyllables ( String word ) {
		syllableCount = 0;
		last = word.length()-1;
		setState(START);
		char c = ' ';
		for ( int i=0 ; i<word.length() ; i++ ) {
			index = i;
			c = word.toLowerCase().charAt( i );
			if ( c == '\'' || isWhitespace(c) ) continue;
			state.handleChar(c);
			if ( state == NONWORD ) break;
		}
		return syllableCount;
	}

	/**
	 * Start state.
	 * @author Issaree Srisomboon
	 *
	 */
	class StartState extends State {
		
		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			if ( isVowelOrY(c) ) {
				setState( SINGLE_VOWEL );
				enterState();
			} else if ( isLetter(c) ) setState( CONSONANT );
			else setState( NONWORD );
		}
		
		public void enterState() {
			syllableCount++;
		}
		
	}
	
	/**
	 * Consonant state.
	 * @author Issaree Srisomboon
	 *
	 */
	class ConsonantState extends State {

		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			if ( isVowelOrY(c) ) {
				if ( index == last ) {
					if ( c == 'e' && syllableCount != 0 );
					else { setState( SINGLE_VOWEL ); enterState(); }
				} else { setState( SINGLE_VOWEL ); enterState(); }
			} else if ( isLetter(c) );
			else if ( isHyphen(c) ) setState( HYPHEN );
			else setState( NONWORD );
		}
		
		public void enterState() {
			syllableCount++;
		}
		
	}
	
	/**
	 * Single vowel state.
	 * @author Issaree Srisomboon
	 *
	 */
	class SingleVowelState extends State {

		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			if ( isVowel(c) ) setState( MULTIVOWEL );
			else if ( isLetter(c) ) setState( CONSONANT );
			else if ( isHyphen(c) ) setState( HYPHEN );
			else setState( NONWORD );
		}
		
	}
	
	/**
	 * Multivowel state. 
	 * @author Issaree Srisomboon
	 *
	 */
	class MultiVowelState extends State {
		
		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			if ( isVowel(c) );
			else if ( isLetter(c) ) setState( CONSONANT );
			else if ( isHyphen(c) ) setState( HYPHEN );
			else setState( NONWORD ); 
		}
		
	}
	
	
	/**
	 * Hyphen state.
	 * @author Issaree Srisomboon
	 *
	 */
	class HyphenState extends State {

		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			if ( isVowel(c) ) {
				setState( SINGLE_VOWEL );
				enterState();
			} else if ( isLetter(c) ) setState( CONSONANT );
			else if ( isHyphen(c) );
			else setState( NONWORD );
		}
		
		public void enterState() {
			syllableCount++;
		}
		
	}
	
	/**
	 * Nonword state.
	 * @author Issaree Srisomboon
	 *
	 */
	class NonWordState extends State {

		/**
		 * @see State#handleChar(char)
		 */
		@Override
		public void handleChar(char c) {
			enterState();
		}
		
		public void enterState() {
			syllableCount = 0;
		}
		
	}
	
	/**
	 * Check whether a character is a letter or not.
	 * @param character in a word
	 * @return true if and only if a character is the letter, otherwise return false.
	 */
	private boolean isLetter ( char c ) {
		return Character.isLetter(c) && !isVowel(c);
	}
	
	/**
	 * Check whether a character is a whitespace or not. 
	 * @param character in a word
	 * @return true if and only if a character is the whitespace, otherwise return false.
	 */
	private boolean isWhitespace ( char c ) {
		return Character.isWhitespace(c);
	}
	
	/**
	 * Check whether a character is a vowel or not. 
	 * @param character in a word
	 * @return true if and only if a character is the vowel, otherwise return false.
	 */
	private boolean isVowel ( char c ) {
		return c == 'a' || c == 'e' || c == 'i' ||c == 'o' || c == 'u';
	}

	/**
	 * Check whether a character is a vowel or 'y'. 
	 * @param character in a word
	 * @return true if and only if a character is the vowel or 'y', otherwise return false.
	 */
	private boolean isVowelOrY ( char c ) {
		return  isVowel(c) || c == 'y';
	}
	
	/**
	 * Check whether a character is a hyphen ('-') or not. 
	 * @param character in a word
	 * @return true if and only if a character is the hyphen ('-'), otherwise return false.
	 */
	private boolean isHyphen ( char c ) {
		return c == '-';
	}
	
}
