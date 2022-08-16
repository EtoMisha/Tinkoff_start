import java.util.*;

public class Task05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();

        Trie surnames = new Trie();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            surnames.insert(line, i);
        }

        for (int i = 0; i < q; i++) {
            int num = scanner.nextInt();
            scanner.skip(" ");
            String str = scanner.nextLine();

            int res = surnames.findPrefix(str, num);
            System.out.println(res);
        }

        scanner.close();
    }
}

class Trie {

    private final TrieNode root;
    private int counter;

    Trie() {
        root = new TrieNode();
    }

    static class TrieNode {
        private final Map<Character, TrieNode> children = new TreeMap<>();
        private boolean endOfWord;
        private int value;

        Map<Character, TrieNode> getChildren() {
            return children;
        }

        void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }

        int getValue() {
            return this.value;
        }

        void setValue(int value) {
            this.value = value;
        }
    }

    public void insert(String key, int value) {
        TrieNode current = root;

        for (char l: key.toCharArray()) {
            current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
        }

        current.setEndOfWord(true);
        current.setValue(value);
    }

    /*
     * Находит в дереве префикс, чтоб среди его детей уже искать слова соответствующие префиксу
     */
    public int findPrefix(String prefix, int index) {
        TrieNode current = root;
        counter = 0;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.getChildren().get(ch);
            if (node == null) {
                return -1;
            }
            current = node;
        }

        return findRec(current, index) + 1;
    }

    /*
     * Ищет слова, соответствующие префиксу.
     * Когда нашли слово, плюсуем счётчик.
     * Если счетчик дошел до искомой величины, возвращаем номер слова в исходном списке.
     */
    private int findRec(TrieNode root, int index) {
        if (root.endOfWord) {
            counter++;
        }
        if (counter == index) {
            return root.getValue();
        }

        int res = -1;
        for (TrieNode child : root.children.values()) {
            res = findRec(child, index);
            if (res > 0) {
                return res;
            }
        }

        return res;
    }

}
