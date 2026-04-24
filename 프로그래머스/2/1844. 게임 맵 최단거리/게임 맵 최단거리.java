import java.util.*;

class Solution {
    public int solution(int[][] maps) {        
        boolean visited[][] = new boolean[maps.length][maps[0].length];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0,0,1));
        visited[0][0] = true;
        
        int dr[] = {-1,0,1,0}, dc[] = {0,1,0,-1};
        
        while(!q.isEmpty()){
            Pos p = q.poll();
            
            if (p.r == maps.length-1 && p.c == maps[0].length-1) return p.cnt; // 도착
       
            for(int i=0;i<4;i++) {
                int nr = p.r+dr[i];
                int nc = p.c+dc[i];
                
                if ((nr<0 || nr>=maps.length || nc<0 || nc>=maps[0].length)) continue; // 맵 밖인 경우
                if (visited[nr][nc] || maps[nr][nc] == 0) continue; // 이미 방문했거나 벽인 경우
                
                visited[nr][nc] = true;
                q.add(new Pos(nr,nc,p.cnt+1)); 
            }
        }
        
        return -1;
    }
    
    class Pos {
        int r, c, cnt;
        Pos(int r, int c, int cnt) {
            this.r=r;
            this.c=c;
            this.cnt=cnt;
        }
    }
}