import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans;
    static boolean truth[];
    static List<Integer> parties[];
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        countParty(); // 진실/거짓 여부로 파티 판별
        getAnswer(); // 답 찾기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        truth = new boolean[N + 1];
        for (int i = 0; i < cnt; i++) {
            int n = Integer.parseInt(st.nextToken());
            q.offer(n);
            truth[n] = true;
        }

        parties = new ArrayList[M];
        for (int i = 0; i < M; i++)
            parties[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++)
                parties[i].add(Integer.parseInt(st.nextToken()));
        }
    }

    static void countParty() {
        while (!q.isEmpty()) {
            int n = q.poll();

            for (List<Integer> party : parties) {
                if (party.contains(n)) {
                    for (int next : party) {
                        if (!truth[next]) {
                            truth[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }
        }
    }

    static void getAnswer() {
        for (List<Integer> party : parties) {
            boolean available = true;
            for (int n : party)
                if (truth[n])
                    available = false;

            if (available)
                ans++;
        }
    }
}