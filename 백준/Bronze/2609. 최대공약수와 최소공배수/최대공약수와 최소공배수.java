import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = a>b ? gcd(a,b) : gcd(b,a);
        sb.append(m).append("\n").append(a*b/m);
        System.out.println(sb);
    }
    public static int gcd(int a, int b){
        while(b>0){
            int t = a;
            a = b;
            b = t%b;
        }
        return a;
    }
}