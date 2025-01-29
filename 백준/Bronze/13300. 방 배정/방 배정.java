import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int rooms;
    static int boy[] = new int[7];
    static int girl[] = new int[7];

    public static void main(String[] args) throws IOException {

        input(); // 입력

        calc(); // 방 계산

        System.out.println(rooms);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (s == 0) // 여학생
                girl[y]++;
            else // 남학생
                boy[y]++;
        }
    }

    static void calc() {
        // 여학생
        for (int i = 0; i < girl.length; i++)
            if (girl[i] > 0) {
                if (girl[i] % k == 0)
                    rooms += girl[i] / k;
                else
                    rooms += girl[i] / k + 1;
            }

        // 남학생
        for (int i = 0; i < boy.length; i++)
            if (boy[i] > 0) {
                if (boy[i] % k == 0)
                    rooms += boy[i] / k;
                else
                    rooms += boy[i] / k + 1;
            }
    }
}