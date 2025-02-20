import java.util.*;
import java.io.*;

public class Main {
    static int N, M, cards[], finds[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력

        for (int i = 0; i < M; i++)
            sb.append(upper(cards, finds[i]) - lower(cards, finds[i])).append(" ");

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

    static int upper(int cards[], int n) {
        int hi = cards.length;
        int lo = 0;
        int mid;

        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (cards[mid] <= n)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }

    static int lower(int cards[], int n) {
        int hi = cards.length;
        int lo = 0;
        int mid;

        while (lo < hi) {
            mid = (lo + hi) / 2;
            if (cards[mid] < n)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }
}