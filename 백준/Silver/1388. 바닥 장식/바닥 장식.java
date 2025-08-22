import java.util.*;
import java.io.*;

public class Main {
    static int N, M, cnt;
    static char map[][];

    public static void main(String[] args) throws IOException {
        input(); // 입력
        find(); // 바닥의 시작점 찾기

        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = str.charAt(j);
        }

        cnt = 0; // 초기화
    }

    static void find() { 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '-') {
                    if (j == 0 || map[i][j - 1] != '-')
                        cnt++;
                } else {
                    if (i == 0 || map[i - 1][j] != '|')
                        cnt++;
                }
            }
        }
    }
}