class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        for (long i = 0; i <= d / k; i++) {
            long r = i * k;

            long maxC = (long) Math.sqrt((long) d * d - r * r);

            answer += maxC / k + 1;
        }

        return answer;
    }
}