import java.util.*;
import java.io.*;

public class Solution {
    static int T;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        for (int t = 1; t <= 10; t++) {

            input(); // 입력
            makePassword();

            System.out.println(sb);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        sb.setLength(0);
        sb.append("#").append(T).append(" ");

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++)
            q.add(Integer.parseInt(st.nextToken()));
    }

    static void makePassword() {
        int idx = 1;
        while (true) {
            int now = q.poll();
            now -= idx++;

            if (now <= 0) {
                q.add(0);
                break;
            } else
                q.add(now);

            if (idx == 6) // 사이클이 끝난 경우 idx 조정
                idx = 1;
        }

        for (int i = 0; i < 8; i++)
            sb.append(q.poll()).append(" ");
    }
}