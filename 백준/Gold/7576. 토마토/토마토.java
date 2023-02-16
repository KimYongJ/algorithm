import java.io.*;
import java.util.*;

class Main{
    static int[][] dxy = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args)throws Exception{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[][] arr = new int[y][x];
        int result = 0, noneTomato = 0;
        for(int i=0; i<y; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<x; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==1){
                    result++;
                    q.add(new int[]{i,j,0});
                }else if(arr[i][j]==0) noneTomato++;
            }
        }
        if(result==x*y){
            System.out.println(0);
            return;
        }
        // i = y , j = x 이다. {y,x,dist}
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int[] d : dxy){
                int y1 = d[0]+qData[0];
                int x1 = d[1]+qData[1];
                int dist = qData[2]+1;
                
                if(y1<0 || x1<0 || y1>=y || x1>=x ||
                   arr[y1][x1]==1 || arr[y1][x1]==-1)
                    continue;
                arr[y1][x1]=1;
                noneTomato--;
                q.add(new int[]{y1,x1,dist});
                
            }
            result = qData[2];
        }
        System.out.println(noneTomato==0 ? result : -1);
    }
}