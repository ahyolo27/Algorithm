import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        int sum = 0;
        for (int w : works)
            sum += w;

        if (sum <= n) return 0; // 시간 내에 모두 처리 가능한 경우

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int w : works)
            pq.add(w);

        while (n > 0) {
            int now = pq.poll();
            now--;
            if (now > 0) pq.add(now);
            n--;
        }

        while (!pq.isEmpty()) {
            int now = pq.poll();
            answer += (long) now * now;
        }

        return answer;
    }
}