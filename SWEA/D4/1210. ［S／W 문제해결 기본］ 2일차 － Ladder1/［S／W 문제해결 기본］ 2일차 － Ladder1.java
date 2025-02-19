import java.util.*;
import java.io.*;

public class Solution {
    static int T, map[][], ans;
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        for (int t = 0; t < 10; t++) {

            input(); // 입력
            move(findStart()); // 역순으로 이동

            System.out.println("#" + T + " " + ans);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        map = new int[100][100];
        visited = new boolean[100][100];

        for (int i = 0; i < 100; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 100; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static int findStart() {
        int start = 0;
        for (int i = 0; i < 100; i++) {
            if (map[99][i] == 2) {
                start = i;
                break;
            }
        }
        return start;
    }

    static void move(int start) {
        int cnt = 99;
        visited[cnt][start] = true;

        // 보통은 직진, 양 옆에 길이 있으면 이동
        while (cnt > 0) {
            if (check(cnt, start + 1) && !visited[cnt][start + 1] && map[cnt][start + 1] == 1) { // 오른쪽에 길이 있는 경우
                while (check(cnt, start + 1) && !visited[cnt][start + 1] && map[cnt][start + 1] == 1) {
                    start++;
                    visited[cnt][start] = true;
                }
            } else if (check(cnt, start - 1) && !visited[cnt][start - 1] && map[cnt][start - 1] == 1) { // 왼쪽에 길이 있는 경우
                while (check(cnt, start - 1) && !visited[cnt][start - 1] && map[cnt][start - 1] == 1) {
                    start--;
                    visited[cnt][start] = true;
                }
            } else { // 길이 없는 경우
                cnt--;
                visited[cnt][start] = true;
            }
        }
        ans = start;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < 100 && 0 <= c && c < 100;
    }
}