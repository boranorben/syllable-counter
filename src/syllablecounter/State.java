package syllablecounter;

/**
 * For all behaviors that depends on state.
 * @author Issaree Srisomboon
 *
 */
public abstract class State {
	
	/**
	 * Delegate the work of handling each character to the current state.
	 * If the character is a vowel, set state to vowel,
	 * (SINGLE_VOWEL or MULTIVOWEL depending on current state)
	 * if the character is a letter, set state to consonant,
	 * if the character is hyphen, set state to hyphen.
	 * Other of these, set state to nonword.
	 * @param character in a word
	 */
	public abstract void handleChar ( char c );
	
	/**
	 * Increment the syllable count when entering the initial vowel state.
	 */
	public void enterState() {}

}
