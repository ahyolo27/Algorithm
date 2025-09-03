import java.util.*;
import java.io.*;

public class Main {

    static int H, W, map[], sum;

    public static void main(String[] args) throws IOException {
        input(); // 입력
        calc();

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++)
            map[i] = Integer.parseInt(st.nextToken());
    }

    static void calc() {
        for (int now = 0; now < W; now++) {
            if (map[now] == H) continue; // 이미 다 찬 세로 줄이면 continue

            int level = map[now] + 1;

            boolean flag1, flag2;

            while (level <= H) {
                flag1 = false;
                flag2 = false;

                // 현재 세로 줄 기준 왼쪽
                for (int prev = now - 1; prev >= 0; prev--) {
                    if (map[prev] >= level) {
                        flag1 = true;
                        break;
                    }
                }

                // 현재 세로 줄 기준 오른쪽
                for (int next = now + 1; next < W; next++) {
                    if (map[next] >= level) {
                        flag2 = true;
                        break;
                    }
                }

                if (flag1 && flag2) {
                    sum++;
                    level++;
                } else break;
            }
        }
    }
}
