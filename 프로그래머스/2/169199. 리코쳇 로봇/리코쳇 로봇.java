import java.util.*;

class Solution {
    public int solution(String[] board) {
        boolean map[][] = new boolean[board.length][board[0].length()];
        Pos start = null;
        Pos goal = null;

        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length();j++) {
                if (board[i].charAt(j) != 'D') {
                    map[i][j] = true;
                    // 시작점/도착점 설정
                    if (board[i].charAt(j) == 'R')
                        start = new Pos(i, j, 0);
                    else if (board[i].charAt(j) == 'G')
                        goal = new Pos(i, j, 0);
                }
            }
        }
        
        int dr[] = {-1,0,1,0}, dc[] = {0,1,0,-1};
        
        Queue<Pos> q = new ArrayDeque<>();
        boolean visited[][] = new boolean[map.length][map[0].length];
        q.add(start);
        visited[start.r][start.c] = true;
        
        while(!q.isEmpty()) {
            Pos p = q.poll();
            
            for(int i=0;i<4;i++) {
                int nr = p.r+dr[i];
                int nc = p.c+dc[i];
                
                if (nr<0 || nc<0 || nr>=map.length || nc>=map[0].length) continue; // 범위를 벗어나는 경우
                
                while(true) {
                    if((nr<0 || nc<0 || nr>=map.length || nc>=map[0].length) || !map[nr][nc]){
                        nr-=dr[i];
                        nc-=dc[i];
                        break;
                    }
                    nr+=dr[i];
                    nc+=dc[i];
                }
                
                if(!visited[nr][nc]) { // 멈춘 곳이 방문하지 않은 곳이면 저장
                    if (nr == goal.r && nc == goal.c) return p.cnt+1; // 도착점에 도착한 경우
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc, p.cnt+1));
                }   
            }
        }
        
        return -1; // 도달 불가능한 경우
    }
    
    class Pos {
        int r, c;
        int cnt;
        Pos(int r, int c, int cnt) {
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
}