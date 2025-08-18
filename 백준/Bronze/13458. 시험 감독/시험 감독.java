import java.util.*;
import java.io.*;

public class Main {
    static int N, people[], B, C;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(calc());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        people = new int[N];
        for (int i = 0; i < N; i++)
            people[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }

    static long calc() {
        long cnt = 0;

        for (int i = 0; i < people.length; i++) {
            people[i] -= B;
            cnt++;
            if (people[i] < 0) continue;

            int tmp = people[i] % C == 0 ? people[i] / C : people[i] / C + 1;
            cnt += tmp;
        }

        return cnt;
    }
}