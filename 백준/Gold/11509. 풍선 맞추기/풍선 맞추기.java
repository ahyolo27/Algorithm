import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int arrows[] = new int[1000001];
        int cnt = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int balloon = Integer.parseInt(st.nextToken());

            if (arrows[balloon] > 0) { // 기존 화살에 맞는 경우
                arrows[balloon]--;
                arrows[balloon - 1]++;
            } else { // 화살을 새로 쏘는 경우
                arrows[balloon - 1]++;
                cnt++;
            }
        }
        
        System.out.println(cnt);
    }
}