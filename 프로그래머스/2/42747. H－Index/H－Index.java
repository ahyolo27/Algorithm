import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations); // 오름차순 정렬
        
        int left = 0, right = citations.length-1;
        while(left<right) {
            int mid = (left+right)/2;
            
            if (citations[mid] >= citations.length-mid) 
                right = mid;
            else
                left = mid+1;
        } 
        
        if (citations[left] >= citations.length - left)
            return citations.length - left;
        else
            return citations.length -left - 1;
    }
}