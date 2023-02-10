import java.io.*;
import java.util.*;

class Solution{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        for(int i=1; i<=l; i++){
            int max =0;
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                int n = Integer.parseInt(st.nextToken());
                if(max<n) max = n;
            }
            System.out.println("#"+i+" "+max);
        }
        
    }
}
