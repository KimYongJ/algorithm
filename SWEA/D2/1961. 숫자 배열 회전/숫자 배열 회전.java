import java.io.*;
import java.util.*;
class Solution{
    static int n;
    static int[][] arr;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            sb.append("#").append(i).append("\n");
            n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            for(int a=0; a<n; a++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int b=0; b<n; b++) arr[a][b] = Integer.parseInt(st.nextToken());
            }
           	int[][] one = turn(arr);
            int[][] two = turn(one);
            int[][] three = turn(two);
            
            for(int a=0; a<n; a++){
                for(int b=0; b<n; b++) sb.append(one[a][b]);
                sb.append(" ");
                for(int b=0; b<n; b++) sb.append(two[a][b]);
                sb.append(" ");
                for(int b=0; b<n; b++) sb.append(three[a][b]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
    public static int[][] turn(int[][] clone){
        // 90도를 돌린 배열을 반환하면된다. 
        int[][] result = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++) result[i][j] = clone[n-1-j][i];

        return result;
    }
}