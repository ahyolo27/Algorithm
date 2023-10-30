class Solution {
    public int solution(String s) {
        int answer = 0;

        String num[] = s.split(" ");

        for (int i = 0; i < num.length; i++) {
            if (num[i].equals("Z"))
                answer -= Integer.valueOf(num[i-1]);
            else
                answer += Integer.valueOf(num[i]);
        }

        return answer;
    }
}