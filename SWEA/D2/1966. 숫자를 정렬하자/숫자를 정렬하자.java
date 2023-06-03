import java.io.*;
import java.util.*;
class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            int x = Integer.parseInt(br.readLine());
            int[] counting = new int[51];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<x; j++)
                counting[Integer.parseInt(st.nextToken())]++;
            
            sb.append("#").append(i);
            
            for(int a=0; a<51; a++)
                for(int b=0; b<counting[a]; b++) sb.append(" ").append(a);
            
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
}