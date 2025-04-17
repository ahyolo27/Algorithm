import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, K, ans;
    static int dr[] = {-1, 1, 0, 0}, dc[] = {0, 0, -1, 1};
    static List<Cell> cells = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Cell {
        int r, c, cnt, dir;

        Cell(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            simulate(); // 미생물 이동
            calcRemains(); // 남은 미생물 세기

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cells.clear(); // 초기화
        ans = 0;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            cells.add(new Cell(r, c, cnt, dir - 1));
        }
    }

    static void simulate() {
        int time = 0;

        while (time < M) {
            Map<String, List<Cell>> thisCell = new HashMap<>(); // 현재 처리하는 미생물 군집 모음

            for (Cell cell : cells) {
                cell.r += dr[cell.dir];
                cell.c += dc[cell.dir];

                if (!isValid(cell.r, cell.c)) {
                    cell.dir = (cell.dir % 2 == 0) ? cell.dir + 1 : cell.dir - 1; // 방향 전환
                    cell.cnt /= 2;
                }

                if (cell.cnt > 0) {
                    String key = cell.r + " " + cell.c;
                    thisCell.computeIfAbsent(key, k -> new ArrayList<Cell>());
                    thisCell.get(key).add(cell);
                }
            }

            cells.clear();

            for (List<Cell> list : thisCell.values()) {
                if (list.size() == 1)
                    cells.add(list.get(0));
                else {
                    int sum = 0;
                    int max = 0;
                    int maxDir = 0;
                    for (Cell c : list) {
                        sum += c.cnt;
                        if (c.cnt > max) {
                            max = c.cnt;
                            maxDir = c.dir;
                        }
                    }
                    cells.add(new Cell(list.get(0).r, list.get(0).c, sum, maxDir));
                }
            }

            time++;
        }
    }

    static void calcRemains() {
        for (Cell cell : cells)
            ans += cell.cnt;
    }

    static boolean isValid(int r, int c) {
        return 1 <= r && r < N - 1 && 1 <= c && c < N - 1;
    }
}