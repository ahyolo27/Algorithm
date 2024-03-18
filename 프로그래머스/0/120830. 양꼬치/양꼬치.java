class Solution {
    public int solution(int n, int k) {
        int answer = 12000 * n + 2000 * k;
        
        answer -= (n / 10) * 2000;
        
        return answer;
    }
}