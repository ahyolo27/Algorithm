import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int map[][] = new int[1001][1001];
    static Queue<Pos> papers = new LinkedList<>(); // 색종이
    static ArrayList<Integer> count = new ArrayList<>(); // 색종이 배열

    static class Pos {
        int r, c, w, h;

        Pos(int c, int r, int w, int h) {
            this.c = c;
            this.r = r;
            this.w = w;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        if (n == 1) {
            Pos p = papers.poll();
            count.add(p.w * p.h);
        } else {
            setPapers(); // 색종이 세팅
            calc(); // 색종이 면적 계산
        }

        for (int i = 0; i < count.size(); i++)
            System.out.println(count.get(i));
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 색종이 개수

        // 색종이 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            papers.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void setPapers() { // 색종이 세팅
        int idx = 1;
        while (!papers.isEmpty()) {
            Pos p = papers.poll();

            for (int i = p.r; i < p.r + p.h; i++)
                for (int j = p.c; j < p.c + p.w; j++)
                    map[i][j] = idx;

            idx++;
        }
    }

    static void calc() { // 색종이 면적 계산
        int idx = 1;
        while (idx <= n) {
            int cnt = 0;

            for (int i = 0; i < map.length; i++)
                for (int j = 0; j < map[i].length; j++)
                    if (map[i][j] == idx)
                        cnt++;

            count.add(cnt);
            idx++;
        }
    }
}