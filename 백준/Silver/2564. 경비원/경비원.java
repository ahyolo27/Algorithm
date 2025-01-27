import java.util.*;
import java.io.*;

public class Main {
    static int r, c, store;
    static int dr, dc; // 동근 위치
    static ArrayList<Pos> list = new ArrayList<>(); // 상점 위치 배열

    static class Pos {
        int n, m;

        Pos(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {
        int sum = 0;

        input();

        for (int cnt = 0; cnt < store; cnt++) {
            sum += calc(cnt);
        }

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        store = Integer.parseInt(br.readLine()); // 상점 개수

        // 상점 세팅
        for (int cnt = 0; cnt < store; cnt++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 동근 세팅
        st = new StringTokenizer(br.readLine());
        dr = Integer.parseInt(st.nextToken());
        dc = Integer.parseInt(st.nextToken());
    }

    static int calc(int cnt) {
        int min = 10000;
        int tmp1 = 0;
        int tmp2 = 0;

        // 상점 좌표 불러오기
        Pos p = list.get(cnt);

        // 이동 거리 계산 및 비교
        if (dr == p.n) // 동근, 상점이 같은 줄에 있는 경우
            min = p.m - dc > 0 ? p.m - dc : dc - p.m;
        else if ((dr <= 2 && p.n <= 2) || (dr > 2 && p.n > 2)) { // 반대줄에 있는 경우
            if (dr <= 2) { // 남북
                tmp1 = dc + r + p.m;
                tmp2 = (c - dc) + r + (c - p.m);
            } else { // 동서
                tmp1 = dc + c + p.m;
                tmp2 = (r - dc) + c + (r - p.m);
            }
            min = Math.min(tmp1, tmp2);
        } else { // 인접한 줄에 있는 경우
            switch (dr) {
                case 1:
                    if (p.n == 3)
                        min = dc + p.m;
                    else
                        min = (c - dc) + p.m;
                    break;
                case 2:
                    if (p.n == 3)
                        min = dc + (r - p.m);
                    else
                        min = (c - dc) + (r - p.m);
                    break;
                case 3:
                    if (p.n == 1)
                        min = dc + p.m;
                    else
                        min = (r - dc) + p.m;
                    break;
                case 4:
                    if (p.n == 1)
                        min = dc + (c - p.m);
                    else
                        min = (r - dc) + (c - p.m);
                    break;
            }
        }
        
        return min;
    }
}