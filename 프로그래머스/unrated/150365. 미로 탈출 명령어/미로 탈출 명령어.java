// https://github.com/KimYongJ
import java.util.ArrayDeque;
class Member{
        int x,y;
        String str;
        public Member(int x,int y, String str){
            this.x = x;
            this.y = y;
            this.str = str;
        }
}
class Solution {
    static char[] getString = {'d','l','r','u'};
    static int[][] dxy = {{1,0},{0,-1},{0,1},{-1,0}};
    public String solution(int n, int m, int x1, int y1, int r, int c, int k) {
        boolean[][][] visit = new boolean[n][m][k+1];
        ArrayDeque<Member> q = new ArrayDeque<>();
        q.add(new Member(x1-1,y1-1,""));
        visit[x1-1][y1-1][0] = true;
        while(!q.isEmpty()){
            Member qData = q.poll();
            for(int i=0; i<dxy.length; i++){
                int x = dxy[i][0] + qData.x;
                int y = dxy[i][1] + qData.y;
                String str = qData.str + getString[i];
                
                if(x<0 || y<0 || x==n || y==m|| str.length()>k || visit[x][y][str.length()]){
                    continue;
                }
                if(x==r-1 && y==c-1 && str.length()==k){
                    return str;
                }
                q.add(new Member(x,y,str));
                visit[x][y][str.length()] =true;
            }
        }
        return "impossible";
    }
    
}