import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] result = new int[n][m];
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
                result[i][j] = Integer.parseInt(st.nextToken());
        }
        for(int y=0; y<n; y++){
            st = new StringTokenizer(br.readLine());
            for(int z=0; z<m; z++){
                result[y][z] += Integer.parseInt(st.nextToken());
                sb.append(result[y][z]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}