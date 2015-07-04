/**
 * Created by andrei on 7/3/15.
 */
public class Trie implements ITrie {

    private int wordCount;
    private int nodeCount;
    private Node[] nodes;

    public Trie() {
        wordCount = 0;
        nodeCount = 0;
        nodes = new Node[26];
    }
    /**
     * Adds the specified word to the trie (if necessary) and increments the word's frequency count
     *
     * @param word The word being added to the trie
     */
    @Override
    public void add(String word) {

    }

    /**
     * Searches the trie for the specified word
     *
     * @param word The word being searched for
     * @return A reference to the trie node that represents the word,
     * or null if the word is not in the trie
     */
    @Override
    public INode find(String word) {
        return null;
    }

    /**
     * Returns the number of unique words in the trie
     *
     * @return The number of unique words in the trie
     */
    @Override
    public int getWordCount() {
        return wordCount;
    }

    /**
     * Returns the number of nodes in the trie
     *
     * @return The number of nodes in the trie
     */
    @Override
    public int getNodeCount() {
        return nodeCount;
    }
}
