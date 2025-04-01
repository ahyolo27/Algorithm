import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, map[][], maxCore, minWire;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static List<Core> cores = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Core {
        int r, c;

        Core(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            setPower(0, 0, 0, map);

            sb.append("#").append(t).append(" ").append(minWire).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cores.clear();
        maxCore = 0;
        minWire = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1 && !isSide(i, j)) // 가장자리에 있지 않은 코어만 list에 담기
                    cores.add(new Core(i, j));
            }
        }
    }

    static void setPower(int depth, int coreCnt, int wireLength, int map[][]) {
        if (depth == cores.size()) {
            if (coreCnt > maxCore) {
                maxCore = coreCnt;
                minWire = wireLength;
            } else if (coreCnt == maxCore)
                minWire = Math.min(wireLength, minWire);
            return;
        }

        int remains = cores.size() - depth;
        if (coreCnt + remains < maxCore) return;

        Core core = cores.get(depth);

        for (int dir = 0; dir < 4; dir++) { // 연결하는 경우
            int copy[][] = getCopyMap(map);
            int length = connect(core, dir, copy);
            if (length > 0)
                setPower(depth + 1, coreCnt + 1, wireLength + length, copy);
        }
        setPower(depth + 1, coreCnt, wireLength, map); // 연결하지 않는 경우
    }

    static int connect(Core core, int dir, int copy[][]) { // 전선이 깔아지면 길이를, 깔아지지 않으면 0 리턴
        int len = 0;
        int nr = core.r + dr[dir];
        int nc = core.c + dc[dir];

        while (check(nr, nc)) { // 전선이 쭉 가지는지 확인
            if (copy[nr][nc] != 0) return 0; // 끊기면 실패
            nr += dr[dir];
            nc += dc[dir];
            len++;
        }

        nr = core.r + dr[dir];
        nc = core.c + dc[dir];
        while (check(nr, nc)) { // 전선 그리기 (2)
            copy[nr][nc] = 2;
            nr += dr[dir];
            nc += dc[dir];
        }

        return len;
    }

    static int[][] getCopyMap(int map[][]) { // 인자로 주어진 맵을 복사하여 리턴
        int copy[][] = new int[N][N];
        for (int i = 0; i < N; i++)
            copy[i] = map[i].clone();
        return copy;
    }

    static boolean isSide(int r, int c) { // 가장자리이면 true 리턴
        return 0 == r || r == N - 1 || c == 0 || c == N - 1;
    }

    static boolean check(int r, int c) { // 맵 범위 내에 있으면 true 리턴
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}