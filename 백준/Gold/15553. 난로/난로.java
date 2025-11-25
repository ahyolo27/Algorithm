import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        int cnt = 0; // 친구 그룹 개수
        int totalSize = 0; // 초 켜는 시간

        // calc
        int startIdx = 1; // 친구 그룹 시작 idx
        int beforeIdx = 0; // 직전 idx
        int size = 0; // 친구 그룹 size

        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine());

            if (beforeIdx == 0) { // 초기
                startIdx = t;
                beforeIdx = t;
                size++;
            } else if (t - beforeIdx <= 1) { // 친구가 이어서 방문하는 경우 -> size 누적
                beforeIdx = t;
                size++;
            } else { // 이어서 방문하지 않는 경우
                cnt++; // 그룹 개수++
                totalSize += size; // 현재 그룹의 초 켜는 시간 누적
                pq.add(t - (startIdx + size)); // 공백 시간 저장

                // init
                startIdx = t;
                beforeIdx = t;
                size = 1;
            }
        }
        // 마지막 그룹 저장
        cnt++;
        totalSize += size;

        // 전체 친구 그룹 개수 - K 개 만큼 pq에서 뽑아 더하기
        for (int i = 0; i < cnt - K; i++)
            totalSize += pq.poll();

        // output
        System.out.println(totalSize);
    }
}