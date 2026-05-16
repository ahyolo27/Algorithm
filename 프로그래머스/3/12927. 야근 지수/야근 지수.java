import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        int h = 50000;

        int count[] = new int[h + 1];
        for (int w : works)
            count[w]++;

        while (n > 0 && h > 0) {
            if (count[h] == 0) {
                h--;
                continue;
            }

            int cnt = Math.min(n, count[h]);
            count[h] -= cnt;
            count[h - 1] += cnt;
            n -= cnt;

            if (count[h] == 0) h--;
        }

        for (int i = 1; i <= h; i++)
            answer += (long) i * i * count[i];

        return answer;
    }
}