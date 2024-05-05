import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;
        
        for(int i=1; i<=N && ans == 0; i++){
            if(N % i == 0)
                K--;
            if(K==0)
                ans = i;
        }
        
        System.out.println(ans);
    }
}