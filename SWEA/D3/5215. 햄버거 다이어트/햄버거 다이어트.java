import java.util.*;
import java.io.*;

public class Solution {
    static int TC, N, L, T[], K[], max;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {

            input(); // 입력
            makeHamburger();

            System.out.println("#" + tc + " " + max);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        T = new int[N];
        K = new int[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            K[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void makeHamburger() {
        int selection[] = new int[N];

        for (int r = 1; r <= N; r++) {
            Arrays.fill(selection, 0);
            for (int i = N - r; i < N; i++)
                selection[i] = 1;
            do {
                int sumT = 0;
                int sumK = 0;
                for (int i = 0; i < N; i++) {
                    if (selection[i] == 1) {
                        sumT += T[i];
                        sumK += K[i];
                    }
                }
                if (sumK <= L)
                    max = Math.max(max, sumT);
            } while (nextPermutation(selection));
        }
    }

    static boolean nextPermutation(int arr[]) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) j--;

        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) swap(arr, i++, k--);

        return true;
    }

    static void swap(int arr[], int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}