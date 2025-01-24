import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int original[];
    static int students[];

    public static void main(String[] args) throws IOException {
        input();

        students[0] = 1;
        int tmp = 0;
        for (int i = 1; i < n; i++) {
            tmp = i + 1; // 학생번호
            for (int j = 1; j <= original[i]; j++)
                students[i - j + 1] = students[i - j];
            students[i - original[i]] = tmp;
        }

        for (int i = 0; i < n; i++)
            System.out.print(students[i] + " ");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        original = new int[n];
        students = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            original[i] = Integer.parseInt(st.nextToken());
    }
}