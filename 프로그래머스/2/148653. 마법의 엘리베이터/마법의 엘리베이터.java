class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        while(storey != 0) {
            int remains = storey % 10;
            
            if (remains > 5 || (remains == 5 && storey / 10 % 10 >=5)) {
                remains = 10-remains;
                answer += remains;
                storey = storey/10 + 1;
            } else {
                answer += remains;
                storey /= 10;
            }
        }
        
        return answer;
    }
}