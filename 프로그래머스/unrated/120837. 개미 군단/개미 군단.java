class Solution {
    public int solution(int hp) {
        int answer = 0;

        final int first = 5;
        final int second = 3;
        final int third = 1;

        while (hp >= first) {
            hp -= first;
            answer++;
        }

        while (hp >= second) {
            hp -= second;
            answer++;
        }

        while (hp >= third) {
            hp -= third;
            answer++;
        }

        return answer;
    }
}