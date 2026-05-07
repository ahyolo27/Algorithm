class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int N = arr1.length;
        int M = arr2[0].length;
        int answer[][] = new int[N][M];

        int r = 0;
        int c = 0;

        while (r < N) {
            // 값 계산
            for(int k = 0; k <arr1[0].length; k++)
                answer[r][c] += arr1[r][k] * arr2[k][c];

            // 인덱스 갱신
            c++;
            if (c == arr2[0].length) {
                c = 0;
                r++;
            }
        }

        return answer;
    }
}