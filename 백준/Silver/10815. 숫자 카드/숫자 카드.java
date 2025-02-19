import java.util.*;
import java.io.*;

public class Main {
    static int N, M, cards[], finds[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력

        for (int i = 0; i < M; i++)
            search(cards, finds[i], 0, N - 1);

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cards = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            cards[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cards); // 오름차순 정렬

        M = Integer.parseInt(br.readLine());
        finds = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            finds[i] = Integer.parseInt(st.nextToken());
    }

    static void search(int cards[], int n, int lo, int hi) {

        if (lo + 1 == hi || lo == hi) { // 끝까지 찾았을 때에
            if (cards[lo] == n || cards[hi] == n)
                sb.append(1).append(" ");
            else
                sb.append(0).append(" ");
            return;
        }

        int mid = (lo + hi) / 2;

        if (cards[mid] < n)
            search(cards, n, mid + 1, hi);
        else if (cards[mid] > n)
            search(cards, n, lo, mid - 1);
        else
            sb.append(1).append(" ");
    }
}