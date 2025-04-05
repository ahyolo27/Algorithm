import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H, ans;
    static boolean ladders[][];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setLine(0, 0); // 사다리에 가로선 배치

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladders[a][b] = true;
        }

        ans = Integer.MAX_VALUE; // 초기화
    }

    static void setLine(int idx, int cnt) {
        if (checkValid()) {
            ans = cnt;
            return;
        }

        if (cnt >= ans) return; // 가지치기 : 이미 답보다 큰 경우 제외
        if (cnt == 3) return; // 가지치기 : 추가한 가로선이 3개 이상이면 종료


        for (int i = idx; i < H * (N - 1); i++) {
            int r = i / (N - 1) + 1;
            int c = i % (N - 1) + 1;

            if (ladders[r][c]) continue; // 사다리를 배치할 수 없는 경우 continue
            if (c > 1 && ladders[r][c - 1]) continue;
            if (c < N - 1 && ladders[r][c + 1]) continue;

            ladders[r][c] = true;
            setLine(i + 1, cnt + 1);
            ladders[r][c] = false;
        }
    }

    static boolean checkValid() { // 모든 i번째 세로선의 결과가 i번이 나오면 true
        for (int i = 1; i <= N; i++) {
            int c = i; // 시작 col index

            for (int r = 1; r <= H; r++) {
                if (c <= N - 1 && ladders[r][c]) // 오른쪽으로 이동
                    c++;
                else if (c > 1 && ladders[r][c - 1]) // 왼쪽으로 이동
                    c--;
            }
            if (c != i) return false;
        }
        return true;
    }
}
