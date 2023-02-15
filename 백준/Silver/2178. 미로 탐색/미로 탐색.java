import java.io.*;
import java.util.*;

class Main{
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        char[][] arr = new char[x+2][y+2];
        for(int i=1; i<=x; i++){
            String s = br.readLine();
            for(int j=1; j<=y; j++)
                arr[i][j] = s.charAt(j-1);
        }
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1,1,1});
        while(!q.isEmpty()){
            int[] qData = q.poll();
            for(int a=0; a<4; a++){
                int x1 = qData[0] + dx[a];
                int y1 = qData[1] + dy[a];
                int depth = qData[2] + 1;
                if(x1<1 || y1<1|| x1>x || y1>y || arr[x1][y1]=='0')
                    continue;
                if(x1==x && y1==y){
                    System.out.println(depth);
                    return;
                }
                arr[x1][y1] = '0';
                q.add(new int[]{x1,y1,depth});
           }
        }
    }
}