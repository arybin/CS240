import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Created by andrei on 7/3/15.
 */
public class SpellCorrector implements ISpellCorrector {

    private ITrie root;

    public SpellCorrector() {
        root = new Trie();
    }
    /**
     * Tells this <code>ISpellCorrector</code> to use the given file as its dictionary
     * for generating suggestions.
     *
     * @param dictionaryFileName File containing the words to be used
     * @throws IOException If the file cannot be read
     */
    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        Stream<String> lines = Files
                .lines(Paths.get(dictionaryFileName))
                .parallel()
                .map(String::toLowerCase); // same as s -> s.toLowercase()
        Iterator<String> iterator = lines.iterator();

        while(iterator.hasNext()) {
            root.add(iterator.next());
        }

    }

    /**
     * Suggest a word from the dictionary that most closely matches
     * <code>inputWord</code>
     *
     * @param inputWord
     * @return The suggestion
     * @throws NoSimilarWordFoundException If no similar word is in the dictionary
     */
    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        return null;
    }
}
