import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static int max;
    static String maxPrefix;
    static String words[];

    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        Node() {
            this.child = new HashMap<>();
            this.isEnd = false;
        }
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        void insert(String word) {
            Node node = root;

            int cnt = 0; // 접두사 길이
            StringBuilder sb = new StringBuilder();
            String prefix = "";

            for (char c : word.toCharArray()) {
                if (!node.child.containsKey(c)) { // 존재 X
                    node.child.put(c, new Node());
                } else { // 존재 O
                    cnt++;
                    sb.append(c);
                }
                node = node.child.get(c);
            }

            node.isEnd = true;
            if (cnt >= max) {
                max = cnt;
                maxPrefix = sb.toString();
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        words = new String[N];

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            words[i] = word;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        solve(); // Trie -> 접두사 찾기
    }

    static void solve() {
        StringBuilder sb = new StringBuilder();

        Trie trie = new Trie();

        for (int i = words.length - 1; i >= 0; i--)
            trie.insert(words[i]);

        int cnt = 0;
        for (String word : words) {
            if (word.startsWith(maxPrefix)) {
                cnt++;
                sb.append(word).append("\n");
            }
            if (cnt == 2) break;
        }

        System.out.println(sb);
    }
}