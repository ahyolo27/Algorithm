import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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

        void insert(String s) {
            Node node = root;

            for (char c : s.toCharArray())
                node = node.child.computeIfAbsent(c, k -> new Node());

            node.isEnd = true;
        }

        String findPrefix(String name) {
            Node node = root;
            StringBuilder s = new StringBuilder();

            for (char c : name.toCharArray()) {
                s.append(c);
                if (!node.child.containsKey(c)) {
                    node.child.put(c, new Node());
                    return s.toString();
                }
                node = node.child.get(c);
            }

            return null;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> count = new HashMap<>();

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            String name = br.readLine();

            // 접두사 찾기
            String prefix = trie.findPrefix(name);
            if (prefix != null) {
                sb.append(prefix).append("\n");
            } else { // 접두사로 닉네임 설정이 불가한 경우
                if (count.containsKey(name)) {
                    sb.append(name).append((count.get(name) + 1)).append("\n");
                } else {
                    sb.append(name).append("\n");
                }
            }
            // 추가
            count.compute(name, (k, v) -> v == null ? 1 : v + 1); // 개수 갱신
            trie.insert(name);
        }

        System.out.println(sb);
    }
}