import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char map[][] = new char[5][5];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static int answer;

    public static void main(String[] args) throws IOException {
        input(); // 입력

        comb(0, 0, new int[7]);

        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        for (int i = 0; i < 5; i++) {
            s = br.readLine();
            for (int j = 0; j < 5; j++)
                map[i][j] = s.charAt(j);
        }
    }

    static void comb(int start, int cnt, int selected[]) {
        if (cnt == 7) {
            check(selected);
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[cnt] = i;
            comb(i + 1, cnt + 1, selected);
        }
    }

    static void check(int selected[]) {
        int sCnt = 0;
        for (int i = 0; i < 7; i++) {
            int r = selected[i] / 5;
            int c = selected[i] % 5;

            if (map[r][c] == 'S') sCnt++;
        }

        if (sCnt < 4) return;
        if (isConnected(selected)) answer++;
    }

    static boolean isConnected(int selected[]) {
        boolean visited[] = new boolean[7];
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int now = q.poll();

            int r = selected[now] / 5;
            int c = selected[now] % 5;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) continue;

                int next = nr * 5 + nc;
                for (int j = 0; j < 7; j++)
                    if (!visited[j] && selected[j] == next) {
                        visited[j] = true;
                        q.add(j);
                        cnt++;
                    }
            }
        }

        return cnt == 7;
    }
}