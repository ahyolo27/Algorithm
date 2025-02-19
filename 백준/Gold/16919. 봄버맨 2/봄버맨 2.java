import java.util.*;
import java.io.*;

public class Main {
    static int R, C, N, times;
    static String map[][];
    static Queue<int[]> q = new LinkedList<>();
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        set();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new String[R][C];
        times = 2;

        for (int i = 0; i < R; i++)
            map[i] = br.readLine().split("");
    }

    static void set() {

        if (N == 1) return;

        find();
        fill();

        if (N % 2 == 0) return;

        while (!q.isEmpty()) {
            int pos[] = q.poll();
            bomb(pos[0], pos[1]);
        }

        if (N % 4 == 3) return;

        find();
        fill();

        while (!q.isEmpty()) {
            int pos[] = q.poll();
            bomb(pos[0], pos[1]);
        }
    }

    static void find() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (map[i][j].equals("O"))
                    q.offer(new int[]{i, j});
        }
    }

    static void fill() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (map[i][j].equals("."))
                    map[i][j] = "O";
        }
    }

    static void bomb(int r, int c) {
        map[r][c] = "."; // 자기 자신

        for (int i = 0; i < 4; i++) { // 인접 4칸
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (check(nr, nc))
                map[nr][nc] = ".";
        }

    }

    static boolean check(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}