import java.util.HashMap;

class Solution {
    int cnt = 0;
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        final int b = board.length;
        final String color = board[h][w];
        
        // 한 칸인 경우
        if (b == 1) 
            return answer;
        
        if (checkLimit(b, h-1, w)) checkColor(board, color, h-1, w);
        if (checkLimit(b, h+1, w)) checkColor(board, color, h+1, w);
        if (checkLimit(b, h, w-1)) checkColor(board, color, h, w-1);
        if (checkLimit(b, h, w+1)) checkColor(board, color, h, w+1);
        
        answer = cnt;
        return answer;        
    }
    
    public void checkColor(String board[][], String color, int h, int w) {
        if (board[h][w].equals(color))
            cnt++;
    }
    
    public boolean checkLimit(int length, int h, int w) {
        if (h < 0 || length <= h)
            return false;
        else if (w < 0 || length <= w)
            return false;
        else
            return true;
    }
}