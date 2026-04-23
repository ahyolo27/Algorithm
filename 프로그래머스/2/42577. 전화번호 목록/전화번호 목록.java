import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {        
        Trie trie = new Trie();
        
        for(String s: phone_book) {
            if (!trie.insert(s))
                return false;
        }

        return true;
    }
    
    class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;
        
        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }
    
    class Trie {
        Node root;
        
        public Trie() {
            this.root = new Node();
        }
        
        public boolean insert(String s) {
            Node node = this.root;
            
            for(char c: s.toCharArray()) {
                if (node.endOfWord) return false;
            
                node.child.putIfAbsent(c, new Node());
                node = node.child.get(c);
            }
            
            if (node.endOfWord || !node.child.isEmpty()) return false;
            
            node.endOfWord = true;
            return true;
        }
    }
}