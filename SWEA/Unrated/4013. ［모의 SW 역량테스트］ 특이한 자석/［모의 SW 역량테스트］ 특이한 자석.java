import java.io.*;
import java.util.*;

public class Solution {
    static int T, K, op[][], score;
    static int gear[][], p[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            start(); // 자석 돌리기
            getScore(); // 점수 계산

            sb.append("#").append(t).append(" ").append(score).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        gear = new int[5][8];
        for (int i = 1; i <= 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 8; j++)
                gear[i][j] = Integer.parseInt(st.nextToken());
        }

        op = new int[K][2];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            op[i][0] = Integer.parseInt(st.nextToken());
            op[i][1] = Integer.parseInt(st.nextToken());
        }

        score = 0; // 초기화
        p = new int[5]; // 초기화
    }

    static void start() {
        int isRotate[] = new int[5]; // 회전 여부 저장 ( 1: 시계방향, -1: 반시계방향)

        for (int k = 0; k < K; k++) {
            int n = op[k][0]; // 자석 번호
            int dir = op[k][1]; // 회전 방향

            Arrays.fill(isRotate, 0);
            isRotate[n] = dir; // 자기 자신은 돌기

            // 인접 자석과의 자성 확인 + 회전 여부 판별
            for (int i = 1; i <= 3; i++) {
                setRotate(n + i, -1, -2, isRotate);
                setRotate(n - i, 1, 2, isRotate);
            }

            // 회전 및 포인터 재배치
            rotate(isRotate);
        }
    }

    static void setRotate(int n, int a, int b, int isRotate[]) {
        if (isValid(n) && isRotate[n + a] != 0)  // 해당 위치에 회전하는 자석이 있는 경우
            if (gear[n][(p[n] + b + 8) % 8] != gear[n + a][(p[n + a] - b + 8) % 8]) // 마주하는 자성이 서로 반대인 경우
                isRotate[n] = -isRotate[n + a]; // 반대 방향으로 회전
    }

    static void rotate(int isRotate[]) {
        for (int i = 1; i < isRotate.length; i++) {
            if (isRotate[i] == 0) continue;

            if (isRotate[i] == 1)  // 시계방향 회전이면
                p[i] = (p[i] - 1 + 8) % 8;
            else // 반시계방향 회전이면
                p[i] = (p[i] + 1 + 8) % 8;
        }
    }

    static void getScore() {
        for (int i = 1; i <= 4; i++)
            score += gear[i][p[i]] * (int) Math.pow(2, i - 1);
    }

    static boolean isValid(int n) {
        return 1 <= n && n < 5;
    }
}