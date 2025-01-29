import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int map[][] = new int[101][101];
    static Queue<Pos> papers = new LinkedList<>(); // 색종이

    static class Pos {
        int c, r;

        Pos(int c, int r) {
            this.c = c;
            this.r = r;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        setPapers(); // 색종이 세팅

        int ans = calc(); // 색종이 면적 계산
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 색종이 개수

        // 색종이 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            papers.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void setPapers() { // 색종이 세팅
        while (!papers.isEmpty()) {
            Pos p = papers.poll();

            for (int i = p.r; i < p.r + 10; i++)
                for (int j = p.c; j < p.c + 10; j++)
                    if (map[i][j] == 0)
                        map[i][j] = 1;
        }
    }

    static int calc() { // 색종이 면적 계산
        int cnt = 0;

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1)
                    cnt++;

        return cnt;
    }
}