import java.io.*;
import java.util.*;

public class Main {
    static int N, K, step;
    static List<Belt> belt = new ArrayList<>();

    static class Belt {
        int idx, hp;
        boolean isRobot;

        Belt(int idx, int hp, boolean isRobot) {
            this.idx = idx;
            this.hp = hp;
            this.isRobot = isRobot;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        simulate(); // 컨베이어 벨트 돌리기

        System.out.println(step);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 2 * N; i++) {
            int hp = Integer.parseInt(st.nextToken());
            belt.add(new Belt(i, hp, false));
        }
    }

    static void simulate() {
        step = 1;

        while (true) {
            rotateBelt();
            moveRobot();
            setRobot();
            if (isFinish()) return;
            step++;
        }
    }

    static void rotateBelt() {
        Belt b = belt.get(belt.size() - 1);
        belt.remove(b);
        belt.add(0, b);
    }

    static void moveRobot() {
        if (belt.get(N - 1).isRobot) // 내릴 수 있으면 내리기
            belt.get(N - 1).isRobot = false;

        for (int i = N - 1; i > 0; i--) {
            Belt next = belt.get(i);
            Belt now = belt.get(i - 1);
            if (next.hp > 0 && !next.isRobot && now.isRobot) { // 옮길 수 있으면 옮기기
                next.hp--;
                next.isRobot = true;
                now.isRobot = false;
            }
        }

        if (belt.get(N - 1).isRobot) // 내릴 수 있으면 내리기
            belt.get(N - 1).isRobot = false;
    }

    static void setRobot() {
        Belt b = belt.get(0);
        if (b.hp > 0 && !b.isRobot) {
            b.hp--;
            b.isRobot = true;
        }
    }

    static boolean isFinish() { // 내구도가 0인 칸의 개수가 K개 이상이면 true
        int cnt = 0;
        for (Belt b : belt)
            if (b.hp == 0) cnt++;
        return cnt >= K;
    }
}