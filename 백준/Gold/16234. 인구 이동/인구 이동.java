import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, map[][], ans;
    static boolean visited[][], isMoved; // 인구이동 가능 여부 확인;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        start(); // 인구이동 시작

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        ans = 0; // 초기화
        visited = new boolean[N][N];

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void start() {
        int days = 0;

        while (true) {
            isMoved = false;
            for (int i = 0; i < N; i++)
                Arrays.fill(visited[i], false); // 방문 배열 초기화

            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    if (!visited[i][j]) {
                        move(i, j);

                    }

            if (!isMoved) // 더이상 이동하지 않으면 종료
                break;
            days++;
        }

        ans = days;
    }

    static void move(int r, int c) {
        Queue<Pos> q = new LinkedList<>();
        List<Pos> countries = new ArrayList<>();
        q.offer(new Pos(r, c));
        visited[r][c] = true;
        int sum = 0;

        while (!q.isEmpty()) {
            Pos p = q.poll();

            sum += map[p.r][p.c]; // 인구수 누적
            countries.add(p); // 인구수 변경할 나라 저장

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (isValid(nr, nc)) {
                    int diff = Math.abs(map[p.r][p.c] - map[nr][nc]);

                    if (!visited[nr][nc] && L <= diff && diff <= R) {
                        visited[nr][nc] = true;
                        q.offer(new Pos(nr, nc));
                    }
                }
            }
        }

        if (!isMoved && countries.size() > 1) isMoved = true;
        setPopulation(sum, countries);
    }

    static void setPopulation(int sum, List<Pos> countries) {
        if (countries.size() == 1) return;

        sum /= countries.size();
        for (Pos p : countries)
            map[p.r][p.c] = sum;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}