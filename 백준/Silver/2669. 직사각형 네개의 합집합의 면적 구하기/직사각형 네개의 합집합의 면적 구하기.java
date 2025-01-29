import java.util.*;
import java.io.*;

public class Main {
    static int map[][] = new int[101][101];
    static Queue<Pos> squares = new LinkedList<>();

    static class Pos {
        int r, c, y, x;

        Pos(int r, int c, int y, int x) {
            this.r = r;
            this.c = c;
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        setSquares(); // 세팅

        int ans = calc(); // 면적 계산
        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            squares.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void setSquares() { // 세팅
        while (!squares.isEmpty()) {
            Pos p = squares.poll();

            for (int i = p.r; i < p.y; i++)
                for (int j = p.c; j < p.x; j++)
                    if (map[i][j] == 0)
                        map[i][j] = 1;
        }
    }

    static int calc() { // 면적 계산
        int cnt = 0;

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++)
                if (map[i][j] == 1)
                    cnt++;

        return cnt;
    }
}