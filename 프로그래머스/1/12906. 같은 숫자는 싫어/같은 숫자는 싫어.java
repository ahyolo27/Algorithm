import java.util.*;

public class Solution {
    public int[] solution(int []arr) {        
        ArrayList<Integer> result = new ArrayList<>();
        
        int prev = -1;
        
        for(int n: arr) {
            if (prev != n) {
                result.add(n);
                prev = n;
            }
        }
        
        int answer[] = new int[result.size()];
        for(int i=0;i<result.size();i++)
            answer[i] = result.get(i);
        
        return answer;
    }
}