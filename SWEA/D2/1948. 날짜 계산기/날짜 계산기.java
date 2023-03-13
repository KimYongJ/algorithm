import java.util.*;
import java.io.*;

class Solution{
    static int[] daylist = {31,28,31,30,31,30,31,31,30,31,30,31};
    static int len;
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        for(int i=1; i<=l; i++){
            int[] arr = new int[4];
            int result = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int a=0; a<4; a++)
                arr[a] = Integer.parseInt(st.nextToken());
            
            for(int a=arr[0]; a<arr[2]; a++)
                result += daylist[a-1];
            
            result += arr[3]-arr[1];
			
            result = result == 0 ? 0 : result+1;

            sb.append("#").append(i).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}