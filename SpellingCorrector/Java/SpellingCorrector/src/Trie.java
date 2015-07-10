/**
 * Created by andrei on 7/3/15.
 */
public class Trie implements ITrie {
    private static final int CONVERTER = 97; //97 is a's int value in ascii
    private static final int ALPHABET_SIZE = 26;

    private int wordCount;
    private int nodeCount;
    private Node root;

    public Trie() {
        wordCount = 0;
        root = new Node();
        nodeCount = 1;
    }
    /**
     * Adds the specified word to the trie (if necessary) and increments the word's frequency count
     *
     * @param word The word being added to the trie
     */
    @Override
    public void add(String word) {
        //go through the trie and add the word
        Node[] children = root.children;
        final int length = word.length();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //if a value for that character doesn't exist yet add it to the trie
            final int position = c - CONVERTER;
            Node node = children[position];
            if(node == null) {
                node = new Node();
                nodeCount++;
                //if this is the end of the word, then increase the word's count
                if(i + 1 == length) {
                    node.increment();
                    wordCount++;
                }
                children[position] = node;
                children = node.children;
            } else { //the value is already there keep adding
                if(i + 1 == length) {
                    node.increment();
                    wordCount++;
                }
                children = node.children;
            }
        }
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

    public class Node implements INode {
        private int value;
        public Node[] children;

        public Node() {
            value = 0;
            children = new Node[ALPHABET_SIZE];
        }

        public void increment() {
            value++;
        }

        /**
         * Returns the frequency count for the word represented by the node
         *
         * @return The frequency count for the word represented by the node
         */
        @Override
        public int getValue() {
            return value;
        }
    }
}
