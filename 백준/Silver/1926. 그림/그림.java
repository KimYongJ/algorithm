import java.io.*;
import java.util.*;

class Main{
    static int cnt = 0,max = 0;
    static class Cur{
        int x;
        int y;
        Cur(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] arr = new int[x+2][y+2];
        boolean[][] visit = new boolean[x+2][y+2];
        Queue<Cur> q = new LinkedList<>();
        for(int i=1; i<=x; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=y; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};
        for(int a=1; a<=x; a++)
            for(int b=1; b<=y; b++){
                if(arr[a][b]==1&&!visit[a][b]){
                	int max1 = 1;
                    cnt++;
                    q.add(new Cur(a,b));
                    visit[a][b] = true;
                    while(!q.isEmpty()){ 
                        Cur c = q.poll();
                        for(int i=0; i<4; i++){
                            int x1 = c.x+dx[i];
                            int y1 = c.y+dy[i];         
                            if(x1<1 || y1<1 || x1>x || y1>y || 
                               visit[x1][y1] || arr[x1][y1]==0){
                                continue;
                            }
                            visit[x1][y1] = true;
                            q.add(new Cur(x1,y1));
                            max1++;
                        }
                    }
                    if(max<max1) max = max1;
                }
            }
        System.out.print(cnt+"\n"+max);
    }
}