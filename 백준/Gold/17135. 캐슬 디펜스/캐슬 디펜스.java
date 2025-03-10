import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D, map[][], max;

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        setArcher(0, 0, new ArrayList<>()); // 시작
        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void setArcher(int start, int depth, ArrayList<Pos> archers) { // 가능한 궁수의 조합을 탐색하는 메서드
        if (depth == 3) { // 궁수 세명을 배치하고 나면 게임을 시작
            startGame(archers);
            return;
        }

        for (int j = start; j < M; j++) {
            archers.add(new Pos(N, j));
            setArcher(j + 1, depth + 1, archers);
            archers.remove(archers.size() - 1);
        }
    }

    static void startGame(ArrayList<Pos> archers) {
        int cnt = 0;
        int copyMap[][] = new int[N + 1][M]; // 맵 복사
        for (int i = 0; i < N; i++)
            copyMap[i] = map[i].clone();

        while (!isFinish(copyMap)) { // 적이 모두 사라지면 종료
            cnt += attack(copyMap, archers); // 공격
            move(copyMap); // 적 이동
        }

        max = Math.max(cnt, max);
    }

    static int attack(int copyMap[][], ArrayList<Pos> archers) {
        int cnt = 0;
        List<Pos> monsters = new ArrayList<>();

        for (int k = 0; k < archers.size(); k++) { // 공격할 적 결정
            Pos p = archers.get(k);
            PriorityQueue<Pos> pq = new PriorityQueue<>((a, b) -> {
                int disA = Math.abs(p.r - a.r) + Math.abs(p.c - a.c);
                int disB = Math.abs(p.r - b.r) + Math.abs(p.c - b.c);
                if (disA != disB)
                    return Integer.compare(disA, disB);
                return Integer.compare(a.c, b.c);
            });

            for (int i = N - 1; i >= 0; i--)
                for (int j = 0; j < M; j++)
                    if (copyMap[i][j] == 1 && Math.abs(p.r - i) + Math.abs(p.c - j) <= D)
                        pq.offer(new Pos(i, j));

            if (!pq.isEmpty()) { // 중복 제거
                Pos monster = pq.poll();
                boolean isSame = false;
                for (int i = 0; i < monsters.size(); i++) {
                    if (monsters.get(i).r == monster.r && monsters.get(i).c == monster.c) {
                        isSame = true;
                        break;
                    }
                }
                if (!isSame)
                    monsters.add(monster);
            }
        }


        for (int i = 0; i < monsters.size(); i++) { // 몬스터 공격
            Pos p = monsters.get(i);
            if (copyMap[p.r][p.c] == 1) {
                copyMap[p.r][p.c] = 0;
                cnt++; // 처치한 몬스터 수 증가
            }
        }
        return cnt;
    }

    static void move(int copyMap[][]) { // 적 이동
        for (int i = N - 1; i > 0; i--)
            copyMap[i] = copyMap[i - 1].clone();
        for (int j = 0; j < M; j++)
            copyMap[0][j] = 0;
    }

    static boolean isFinish(int copyMap[][]) { // 적이 모두 사라졌으면 true
        for (int i = N - 1; i >= 0; i--)
            for (int j = 0; j < M; j++)
                if (copyMap[i][j] == 1)
                    return false;
        return true;
    }
}