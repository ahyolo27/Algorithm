import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        // 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        // 탐색 O(N)
        int idx = A.length - 1;

        for (int i = B.length - 1; i >= 0; i--) {
            int now = B[i];

            while (idx >= 0) {
                int target = A[idx];

                if (target < now) { // 이기는 경우
                    idx--;
                    answer++;
                    break;
                }
                
                idx--;
            }
        }

        return answer;
    }
}