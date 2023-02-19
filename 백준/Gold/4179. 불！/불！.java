// https://github.com/KimYongJ
import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int startX,startY;
    public static void main(String[] args)throws Exception{
        ArrayDeque<int[]> q = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        boolean[][] visit = new boolean[x][y];
        char[][]arr = new char[x][y];
        for(int i=0; i<x;i++)
            for(int j=0; j<y; j++){
                arr[i][j] = read();
                if(arr[i][j]=='J'){
                    startX = i;
                    startY = j;
                    visit[i][j]=true;
                }else if(arr[i][j]=='F'){
                    q.add(new int[]{i,j,0});
                    visit[i][j]=true;
                }
            }
        
        q.add(new int[]{startX,startY,0});
        
        while(!q.isEmpty()){
            int[] qData = q.poll();
            
            for(int[] xy : dxy){
                int x1 = qData[0] + xy[0];
                int y1 = qData[1] + xy[1];
                int dist = qData[2] + 1;
                
                if(x1<0 || y1<0 || x1>=x || y1>=y){
                    if(arr[qData[0]][qData[1]]=='J'){
                        System.out.println(dist);
                        return;
                    }else 
                        continue;
                }
                if(arr[x1][y1]!='.') continue;
                
                arr[x1][y1] = arr[qData[0]][qData[1]];
                q.add(new int[]{x1,y1,dist});
                
            }
        }
        System.out.println("IMPOSSIBLE");
    }
    public static char read()throws Exception{
        while(true){
            int num = br.read();
            if(num!='\n')
                return (char)num;
        }
    }
}