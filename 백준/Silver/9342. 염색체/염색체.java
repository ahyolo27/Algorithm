import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T, pos[];
    static Queue<String> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력

        while (!q.isEmpty())
            check(q.poll());

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++)
            q.add(br.readLine());

        pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < 6; i++)
            pos[i] = 0;
        pos[0] = 1; //'A'
        pos['F' - 'A'] = 2;
        pos['C' - 'A'] = 3;
    }

    static void check(String s) {
        int beforeIdx = -10;
        int start = 0;

        if (pos[s.charAt(0) - 'A'] >= 0 && s.charAt(0) != 'A') { // 시작하는 경우
            beforeIdx = s.charAt(0) - 'A';
            start = 1;
        }

        for (int j = start; j < s.length(); j++) {
            char c = s.charAt(j);
            int idx = c - 'A';

            if (beforeIdx == Integer.MIN_VALUE) {
                sb.append("Good").append("\n");
                return;
            }

            if (beforeIdx == -10 && idx >= 0) beforeIdx = idx;
            else if (idx == beforeIdx) continue;
            else if (pos[idx] - pos[beforeIdx] == 1) beforeIdx = idx;
            else if (beforeIdx == 'C' - 'A' && idx >= 0) beforeIdx = Integer.MIN_VALUE;
            else {
                sb.append("Good").append("\n");
                return;
            }
        }

        if (pos[s.charAt(s.length() - 1) - 'A'] < 0)
            sb.append("Good").append("\n");
        else
            sb.append("Infected!").append("\n");
    }
}