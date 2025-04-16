import java.io.*;
import java.util.*;

public class Main {
    static int N, map[][], r, c, size, time;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Pos {
        int r, c, dist;

        Pos(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        eat(); // 아기 상어 밥 먹기

        System.out.println(time);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) { // 아기 상어의 초기 위치
                    r = i;
                    c = j;
                    map[i][j] = 0;
                }
            }
        }
    }

    static void eat() {
        size = 2; // 아기 상어 크기
        int cnt = 0; // 현재 크기에서 먹은 물고기 수
        boolean visited[][] = new boolean[N][N];

        Queue<Pos> q = new LinkedList<>();
        ArrayList<Pos> canEat = new ArrayList<>();

        while (true) {
            for (int i = 0; i < N; i++)
                Arrays.fill(visited[i], false);

            Pos start = new Pos(r, c, 0);
            q.offer(start);
            visited[r][c] = true;

            int min = Integer.MAX_VALUE;

            while (!q.isEmpty()) {
                Pos p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (!isValid(nr, nc)) continue;

                    if (!visited[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == size)) { // 지나갈 수 있는 칸인 경우
                        visited[nr][nc] = true;
                        q.offer(new Pos(nr, nc, p.dist + 1));
                    } else if (!visited[nr][nc] && map[nr][nc] < size) { // 먹을 수 있는 물고기인 경우
                        if (min >= p.dist + 1) {
                            min = p.dist + 1;
                            visited[nr][nc] = true;
                            canEat.add(new Pos(nr, nc, p.dist + 1));
                        }
                    }
                }
            }

            if (canEat.isEmpty()) // 먹을 물고기가 없는 경우
                return;

            if (canEat.size() > 1) {
                canEat.sort((o1, o2) -> {
                    if (o1.dist == o2.dist) {
                        if (o1.r == o2.r)
                            return o1.c - o2.c;
                        else
                            return o1.r - o2.r;
                    }
                    return o1.dist - o2.dist;
                });
            }

            map[canEat.get(0).r][canEat.get(0).c] = 0;
            cnt++;
            if (cnt == size) {
                size++;
                cnt = 0;
            }

            time += canEat.get(0).dist;
            r = canEat.get(0).r; // 현재 위치 갱신
            c = canEat.get(0).c;

            q.clear();
            canEat.clear();
        }
    }

    static boolean isValid(int r, int c) { // 범위 내에 있으면 true 반환
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}