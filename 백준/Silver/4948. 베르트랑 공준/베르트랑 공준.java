import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = 1;
        while((n = Integer.parseInt(br.readLine()))!=0){
            int cnt = 0;
            for(int i=n+1; i<=2*n; i++)
                if(check(i)) cnt++;
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
    public static boolean check(int n){
        for(int i=2; i*i<=n; i++)
            if(n%i==0) return false;
        return true;
    }
}