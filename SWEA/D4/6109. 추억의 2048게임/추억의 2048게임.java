import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, map[][];
    static String dir;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            move(); // 이동
            sb.append("#").append(t).append("\n");
            output(); // map 출력
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        dir = st.nextToken();
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void move() {
        switch (dir) { // 합치기
            case "up":
                up();
                break;
            case "down":
                down();
                break;
            case "left":
                left();
                break;
            case "right":
                right();
                break;
        }
    }

    static void left() {
        for (int i = 0; i < N; i++)
            process(map[i]);
    }

    static void right() {
        for (int i = 0; i < N; i++) {
            reserve(map[i]); // 뒤집기
            process(map[i]);
            reserve(map[i]);
        }
    }

    static void up() {
        for (int j = 0; j < N; j++) {
            int col[] = new int[N];
            for (int i = 0; i < N; i++) // 열 추출
                col[i] = map[i][j];

            process(col); // 작업 후 다시 map에 넣기
            for (int i = 0; i < N; i++)
                map[i][j] = col[i];
        }
    }

    static void down() {
        for (int j = 0; j < N; j++) {
            int col[] = new int[N];
            for (int i = 0; i < N; i++) // 열 추출
                col[i] = map[i][j];

            reserve(col); // 뒤집기
            process(col); // 작업 후 다시 map에 넣기
            reserve(col);
            for (int i = 0; i < N; i++)
                map[i][j] = col[i];
        }
    }

    static void process(int line[]) { // 왼쪽으로 합치고 밀기
        int idx = 0;

        for (int i = 0; i < line.length; i++) // 당기고
            if (line[i] != 0)
                line[idx++] = line[i];

        for (int i = idx; i < line.length; i++)  // 남은 공간에 0 채우기
            line[i] = 0;

        for (int i = 0; i < line.length - 1; i++) {
            if (line[i] > 0 && line[i] == line[i + 1]) {
                line[i] *= 2;
                line[i + 1] = 0;
                i++;
            }
        }

        idx = 0;
        for (int i = 0; i < line.length; i++)
            if (line[i] != 0)
                line[idx++] = line[i];

        for (int i = idx; i < line.length; i++)
            line[i] = 0;
    }

    static void reserve(int arr[]) { // 뒤집기
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    static void output() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sb.append(map[i][j]).append(" ");
            sb.append("\n");
        }
    }
}