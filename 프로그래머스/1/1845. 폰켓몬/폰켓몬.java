import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> ponketmon = new HashSet<>();
        
        for(int number: nums)
            ponketmon.add(number);
        
        int size = nums.length / 2; // 가져갈 수 있는 폰켓몬 수
        
        return Math.min(size, ponketmon.size());
    }
} 