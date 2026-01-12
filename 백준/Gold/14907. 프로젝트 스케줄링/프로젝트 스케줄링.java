import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Node {
        int idx, w;

        Node(int idx, int w) {
            this.idx = idx;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        List<Integer> out[] = new List[26];
        List<Integer> in[] = new List[26];
        for (int i = 0; i < 26; i++) {
            out[i] = new ArrayList<>();
            in[i] = new ArrayList<>();
        }

        Queue<Node> q = new LinkedList<>(); // 탐색 큐

        String line;
        while ((line = br.readLine()) != null) {
            String s[] = line.split(" ");
            int idx = s[0].charAt(0) - 'A';
            int weight = Integer.parseInt(s[1]);

            q.add(new Node(idx, weight)); // 탐색 큐에 넣기

            if (s.length == 3) {
                for (int i = 0; i < s[2].length(); i++) {
                    out[s[2].charAt(i) - 'A'].add(idx); // 나가는 방향
                    in[idx].add(s[2].charAt(i) - 'A'); // 들어오는 방향
                }
            }
        }

        // solution
        int weight[] = new int[26];
        boolean finished[] = new boolean[26];
        Arrays.fill(weight, -1);

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (weight[now.idx] == -1)
                weight[now.idx] = now.w;

            if (in[now.idx].isEmpty())  // 선행 작업이 없는 작업
                finished[now.idx] = true;

            if (out[now.idx].isEmpty()) { // 마지막 작업
                boolean isFinished = true;
                int max = weight[now.idx];
                for (int before : in[now.idx]) {
                    if (!finished[before]) {
                        isFinished = false;
                        break;
                    }
                    max = Math.max(max, weight[before] + now.w);
                }
                if (isFinished) {
                    weight[now.idx] = max;
                    finished[now.idx] = true;
                }
            } else { // 이어진 작업들에 대해 갱신
                for (int next : out[now.idx]) {
                    boolean isFinished = true;
                    int max = weight[now.idx];

                    for (int before : in[now.idx]) {
                        if (!finished[before]) {
                            isFinished = false;
                            break;
                        }
                        max = Math.max(max, weight[before] + now.w);
                    }

                    if (isFinished) {
                        weight[now.idx] = max;
                        finished[now.idx] = true;
                    }
                }
            }

            if (!finished[now.idx])
                q.add(now);
        }

        // output
        int max = 0;
        for (int w : weight)
            max = Math.max(max, w);
        System.out.println(max);
    }
}