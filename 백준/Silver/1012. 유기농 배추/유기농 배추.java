import java.io.*;
import java.util.*;

class Main{
    static int n,m;
    static int[][] arr;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt, l = Integer.parseInt(st.nextToken());
        while(l-->0){
            cnt = 0;
            // 이하 데이터 넣는 코드 
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            arr = new int[n][m];
            int k = Integer.parseInt(st.nextToken());
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]=1;
            }
            // 이하 값이 1인것에 대해 dfs 실행 
            for(int i=0; i<arr.length; i++)
                for(int j=0; j<arr[0].length; j++)
                    if(arr[i][j] == 1){
                        cnt++;
                        DFS(i,j); // 주변을 탐색해 상하좌우 인접한것 체크
                    }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
    public static void DFS(int x, int y){
        arr[x][y] = 0;
        int[] dx = {0,0,-1,1};
        int[] dy = {-1,1,0,0};
        
        for(int i=0; i<4; i++){
            int x1 = x+dx[i];
            int y1 = y+dy[i];
            if(!(x1<0) && !(x1>=n) && !(y1<0) && !(y1>=m) && arr[x1][y1]==1)
                DFS(x1,y1);
        }
    }
}