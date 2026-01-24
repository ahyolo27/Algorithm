import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans, dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static String map[][];
    static List<Pos> blanks;
    static List<Pos> teachers;

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        comb(0, 0, new Pos[3]); // 장애물 뽑기

        System.out.println(ans == -1 ? "NO" : "YES");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];
        blanks = new ArrayList<>();
        teachers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s[] = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = s[j];
                if (map[i][j].equals("X"))
                    blanks.add(new Pos(i, j));
                else if (map[i][j].equals("T")) {
                    teachers.add(new Pos(i, j));
                    map[i][j] = "X";
                }
            }
        }

        ans = -1;
    }

    static void comb(int idx, int cnt, Pos selected[]) {
        if (cnt == 3) {
            if (test(selected)) ans = 1;
            return;
        }
        if (ans == 1) return; // 이미 답 찾은 경우

        for (int i = idx; i < blanks.size(); i++) {
            selected[cnt] = blanks.get(i);
            comb(i + 1, cnt + 1, selected);
        }
    }

    static boolean test(Pos selected[]) {
        for (Pos s : selected)
            map[s.r][s.c] = "O";

        for (Pos t : teachers) {
            for (int i = 0; i < 4; i++) {
                int tmp = 1;
                while (true) {
                    int nr = t.r + dr[i] * tmp;
                    int nc = t.c + dc[i] * tmp;
                    if (!isValid(nr, nc) || map[nr][nc].equals("O")) break; // 벽 도달
                    if (map[nr][nc].equals("S")) {// 학생인 경우
                        for (Pos s : selected)
                            map[s.r][s.c] = "X"; // 원상 복구
                        return false;
                    }
                    tmp++;
                }
            }
        }
        return true;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}