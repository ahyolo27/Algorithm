import java.util.*;
import java.io.*;

public class Main {
    static int k, sum = 1;
    static int direction[] = new int[5];
    static ArrayList<Pos> list = new ArrayList<>();

    static class Pos {
        int dir, l;

        Pos(int dir, int l) {
            this.dir = dir;
            this.l = l;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 참외밭 너비 계산

        System.out.println(k * sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        k = Integer.parseInt(br.readLine());

        for (int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            direction[l]++; // 방향 체크
            list.add(new Pos(l, Integer.parseInt(st.nextToken())));
        }
    }

    static void calc() {
        int index = 0;

        // 잘리지 않은 변 두개 찾기
        for (int cnt = 0; cnt < 2; cnt++) {
            int d = 0;
            for (int i = 1; i < direction.length; i++) {
                if (direction[i] == 1) { // 잘리지 않은 구간
                    direction[i] = 0;
                    d = i;
                    break;
                }
            }
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).dir == d) {
                    sum *= list.get(i).l;
                    index = i;
                    list.remove(i);
                    break;
                }
            }
        }

        sum -= list.get((index + 1) % 4).l * list.get((index + 2) % 4).l;
    }
}