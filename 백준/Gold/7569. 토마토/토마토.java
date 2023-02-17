import java.io.*;
import java.util.*;

class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int x,y,z, result, nTomato;
    static char[][][] arr;
    static ArrayDeque<int[]> q = new ArrayDeque<>();
    static int[][] plus = {{0,0,1},{0,0,-1},{0,1,0},{0,-1,0},{1,0,0},{-1,0,0}};
    public static void main(String[] args)throws Exception{
        StringTokenizer st = new StringTokenizer(br.readLine());
        y=Integer.parseInt(st.nextToken());
        x=Integer.parseInt(st.nextToken());
        z=Integer.parseInt(st.nextToken());
        arr = new char[z][x][y];
        for(int a=0;a<z;a++)
            for(int i=0;i<x;i++)
                for(int j=0; j<y;j++){
                    arr[a][i][j] = read();
                    if(arr[a][i][j]=='1'){
                        q.add(new int[]{a,i,j,0});
                    }
                }
        if(result==x*y*z){
            System.out.print("0");
            return;
        }
        result = 0;
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int[] p : plus){
                int a1 = qData[0] + p[0];
                int x1 = qData[1] + p[1];
                int y1 = qData[2] + p[2];
                int dist = qData[3] + 1;
                
                if(a1<0 || a1>=z || x1<0 || x1>=x || y1<0 || y1>=y ||
                   arr[a1][x1][y1]=='-' || arr[a1][x1][y1]=='1')
                    continue;
                
                arr[a1][x1][y1] = '1';
                nTomato--;
                q.add(new int[]{a1,x1,y1,dist});
            }
            result = qData[3];
        }
        
        System.out.println(nTomato==0? result : -1);
        
    }
    public static char read()throws Exception{
        while(true){
            int num = br.read();
            if(num=='1'){
                result++;
                return (char)num;
            }else if(num=='0'){
                nTomato++;
                return (char)num;
            }else if(num=='-'){
                br.read();
                return (char)num;
            }
        }
    }
}