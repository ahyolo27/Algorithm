import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char map[][] = new char[5][5];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Set<ArrayList<Princess>> answerList;

    static class Princess {
        int r, c;

        Princess(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Princess)) return false;

            Princess princess = (Princess) o;
            return princess.r == r && princess.c == c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        calc();

        System.out.println(answerList.size());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        for (int i = 0; i < 5; i++) {
            s = br.readLine();
            for (int j = 0; j < 5; j++)
                map[i][j] = s.charAt(j);
        }

        answerList = new HashSet<>();
    }

    static void calc() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean visited[][] = new boolean[5][5];
                visited[i][j] = true;
                ArrayList<Princess> princesses = new ArrayList<>();
                princesses.add(new Princess(i, j));

                if (map[i][j] == 'S')
                    select(1, 0, princesses, visited);
                else
                    select(0, 1, princesses, visited);
            }
        }

    }

    static void select(int sCnt, int yCnt, ArrayList<Princess> princesses, boolean visited[][]) {
        if (sCnt + yCnt == 7) {
            if (sCnt < yCnt) return;
            princesses.sort(((o1, o2) -> {
                if (o1.r == o2.r)
                    return o1.c - o2.c;
                return o1.r - o2.r;
            }));
            answerList.add(new ArrayList<>(princesses));
            return;
        }

        for (Princess p : new ArrayList<>(princesses)) {
            for (int i = 0; i < 4; i++) {
                int nextR = p.r + dr[i];
                int nextC = p.c + dc[i];

                if (0 > nextR || 0 > nextC || 5 <= nextR || 5 <= nextC || visited[nextR][nextC]) continue;

                if (map[nextR][nextC] == 'S') {
                    visited[nextR][nextC] = true;
                    sCnt++;
                    Princess princess = new Princess(nextR, nextC);
                    princesses.add(princess);

                    select(sCnt, yCnt, princesses, visited);

                    visited[nextR][nextC] = false;
                    sCnt--;
                    princesses.remove(princess);
                    
                } else if (map[nextR][nextC] == 'Y' && yCnt <= 2) { // 3부터는 +1 시 과반수가 되기 때문에 불가능
                    visited[nextR][nextC] = true;
                    yCnt++;
                    Princess princess = new Princess(nextR, nextC);
                    princesses.add(princess);

                    select(sCnt, yCnt, princesses, visited);

                    visited[nextR][nextC] = false;
                    yCnt--;
                    princesses.remove(princess);
                }
            }
        }
    }
}