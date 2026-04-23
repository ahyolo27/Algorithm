class Solution {
    public int solution(int[][] sizes) {
        int maxA = 0, maxB = 0;
        
        for(int card[]: sizes) {
            int a = Math.max(card[0], card[1]);
            int b = Math.min(card[0], card[1]);
            
            maxA = Math.max(a, maxA);
            maxB = Math.max(b, maxB);
        }
        
        return maxA * maxB;
    }
}