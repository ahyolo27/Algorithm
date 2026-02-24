import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static class Node {
        Map<Character, Node> child;
        boolean isEnd;

        Node() {
            child = new HashMap<>();
            isEnd = false;
        }
    }

    static class Trie {
        Node root;

        Trie() {
            this.root = new Node();
        }

        boolean insert(String s) {
            Node node = root;
            int cnt = 0;

            for (char c : s.toCharArray()) {
                if (!node.child.containsKey(c)) {
                    node.child.put(c, new Node());
                } else {
                    cnt++;
                }

                node = node.child.get(c);
            }

            return cnt != s.length(); // 접두어가 없으면 true, 있으면 false
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            Trie trie = new Trie();
            boolean finished = false;

            String num[] = new String[N];
            for (int j = 0; j < N; j++)
                num[j] = br.readLine();

            Arrays.sort(num, Comparator.reverseOrder()); // 역순 정렬

            for (String s : num) {
                if (!trie.insert(s)) {
                    finished = true;
                    break;
                }
            }

            if (finished)
                sb.append("NO").append("\n");
            else
                sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }
}