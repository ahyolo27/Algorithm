import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, X, map[][], ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력

            findRoad(); // 활주로 건설
            rotateMap();
            findRoad();

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = 0; // 초기화
    }

    static void findRoad() {
        boolean runaway[][] = new boolean[N][N];
        int row = 0;

        for (int i = 0; i < N; i++) {
            boolean isPossible = true;
            int nowHeight = map[i][0];
            int cnt = 1;

            for (int j = 1; j < N; j++) {

                if (nowHeight == map[i][j])
                    cnt++;

                else {
                    if (Math.abs(map[i][j] - nowHeight) > 1) { // 경사로를 놓기에 너무 높은 경우
                        isPossible = false;
                        break;
                    }

                    if (nowHeight > map[i][j]) { // 내리막

                        int nextHeight = map[i][j];
                        for (int x = j; x < j + X; x++) {
                            if (x == N || nextHeight != map[i][x]) { // 맵 범위 밖이거나 높이가 일정하지 않은 경우
                                isPossible = false;
                                break;
                            }
                        }
                        if (!isPossible) break;

                        for (int x = j; x < j + X; x++) { // 경사로가 겹치는 경우
                            if (runaway[i][x]) {
                                isPossible = false;
                                break;
                            }
                        }
                        if (!isPossible) break;

                        for (int x = j; x < j + X; x++)  // 경사로를 놓을 수 있는 경우 -> 경사로 배치
                            runaway[i][x] = true;

                    } else { // 오르막

                        if (cnt < X) { // 경사로를 놓을 공간이 충분하지 않은 경우
                            isPossible = false;
                            break;
                        }

                        for (int x = j - 1; x >= j - X; x--) { // 경사로가 겹치는 경우
                            if (runaway[i][x]) {
                                isPossible = false;
                                break;
                            }
                        }
                        if (!isPossible) break;

                        for (int x = j - 1; x >= j - X; x--)  // 경사로를 놓을 수 있는 경우 -> 경사로 배치
                            runaway[i][x] = true;
                    }

                    cnt = 1; // 값 갱신
                    nowHeight = map[i][j];
                }
            }

            if (isPossible)
                row++;
        }
        ans += row;
    }

    static void rotateMap() {
        int copyMap[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = copyMap[N - 1 - j][i];
    }
}