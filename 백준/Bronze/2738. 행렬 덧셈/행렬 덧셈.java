import java.io.*;
import java.util.*;
import java.util.stream.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[]arr = {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
        int[][] result = new int[arr[0]][arr[1]];
        for(int i=0; i<arr[0]; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<arr[1]; j++){
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int y=0; y<arr[0]; y++){
            st = new StringTokenizer(br.readLine());
            for(int z=0; z<arr[1]; z++){
                result[y][z] += Integer.parseInt(st.nextToken());
                sb.append(result[y][z]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
}