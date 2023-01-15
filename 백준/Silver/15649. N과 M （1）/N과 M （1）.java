import java.io.*;
import java.util.*;

class Main{
    static StringBuilder sb = new StringBuilder();
    static int n,m;
    static int[] arr;
    static boolean[] check;
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        check = new boolean[n+1];
        back(0);
        System.out.println(sb);
    }
    public static void back(int k){
        if(k==m){
            for(int x:arr)
                sb.append(x).append(" ");
            sb.append("\n");
            return;
        }
        for(int i=1; i<=n; i++)
            if(!check[i]){
                arr[k]=i;
                check[i] = true;
                back(k+1);
                check[i] = false;
            }
    }
}