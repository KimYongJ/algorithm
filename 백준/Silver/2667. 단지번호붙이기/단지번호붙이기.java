// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

class Main{
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[N][N];
        int cnt = 0;
        
        for(int i=0; i<N; i++)
            arr[i] = br.readLine().toCharArray();

        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(arr[i][j] != '0'){
                    cnt++;
                    q.add(new int[] {i,j,arr[i][j]});
                    arr[i][j] = '0';
                    list.add( BFS(q, arr, N, dxy) );
                }
        
        sb.append(cnt).append('\n');
        
        Collections.sort(list);
        
        for(int i=0; i< list.size() ; i++)
            sb.append( list.get(i) ).append('\n');
        
        System.out.print(sb);
    }

    public static int BFS(ArrayDeque<int[]> q, char[][] arr, int N, int[][] dxy){
        int area = 1;
        while(!q.isEmpty()){
            int[] node = q.poll();
            for(int i=0; i<4; i++){
                int x1 = node[0] + dxy[i][0];
                int y1 = node[1] + dxy[i][1];
                
                if( !(x1<0 || y1<0 || x1>=N || y1>=N || arr[x1][y1] != node[2]) )
                {
                    arr[x1][y1] = '0';
                    q.add(new int[] {x1,y1,node[2]});
                    area++;
                }
            }
        }
        return area;
    }

}