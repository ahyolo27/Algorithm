import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    
    public int[][] solution(int n) {       
        
        hanoi(n, 1, 3, 2);
        
        return list.toArray(new int[0][]);
    }
    
    void hanoi(int n, int from, int to, int tmp) {
        if (n==1) {
            list.add(new int[] {from, to});
            return;
        }
        
        hanoi(n-1, from, tmp, to);
        list.add(new int[] {from, to});
        hanoi(n-1, tmp, to, from);
    }
}