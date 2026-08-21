import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        
        
        List<Integer> list = new ArrayList<>();
        boolean visited[][] = new boolean[maps.length][maps[0].length()];
        
        for(int r=0;r<maps.length;r++) {
            for (int c=0;c<maps[0].length();c++) {
                if (visited[r][c]) continue; // 이미 방문한 경우
                
                if (maps[r].charAt(c)!='X')
                    list.add(bfs(r, c, maps, visited));
            }
        }

        // 무인도가 없는 경우
        if (list.isEmpty())
            return new int[] {-1};
        
        // 무인도가 있는 경우
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0;i<list.size();i++)
            answer[i] = list.get(i);
        
        return answer;
    }
    
    int bfs(int startR, int startC, String[] maps, boolean visited[][]) {
        
        int dr[] = {-1,0,1,0}, dc[] ={0,1,0,-1};
        
        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(startR, startC));
        visited[startR][startC] = true;
        
        int sum = maps[startR].charAt(startC)-'0';
        
        while(!q.isEmpty()) {
            Pos p = q.poll();
            
            for(int i=0;i<4;i++) {
                int nr = p.r+dr[i];
                int nc = p.c+dc[i];
                
                if (nr<0 || nr>=maps.length || nc<0 || nc>=maps[0].length() || visited[nr][nc]) continue;                
                
                if (maps[nr].charAt(nc) != 'X') { // 이동 가능하면 이동
                    sum += maps[nr].charAt(nc) - '0';
                    q.add(new Pos(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }
        
        return sum;
    }
    
    class Pos {
        int r, c;
        Pos(int r, int c) {
            this.r=r;
            this.c=c;
        }
    }
}