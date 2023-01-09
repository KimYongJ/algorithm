import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int result = 1;
        
        for(int i=n; i>n-k; i--)
            result *= i;
        
        for(int i=k; i>1; i--)
            result /= i;
        
        
        System.out.println(result);
    }
}