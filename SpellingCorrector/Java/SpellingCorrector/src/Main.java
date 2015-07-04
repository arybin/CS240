import java.io.IOException;
/**
 * A simple main class for running the spelling corrector. This class is not
 * used by the passoff program.
 */
public class Main {
	private static final String fileName = "files/words.txt";
    private static final String input = "bbug";
	
	/**
	 * Give the dictionary file name as the first argument and the word to correct
	 * as the second argument.
	 */
	public static void main(String[] args) throws IOException, ISpellCorrector.NoSimilarWordFoundException {
		
		String dictionaryFileName = fileName;//args[0];
		String inputWord = input;//args[1];
		
		/**
		 * Create an instance of your corrector here
		 */
		ISpellCorrector corrector = new SpellCorrector();
		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
