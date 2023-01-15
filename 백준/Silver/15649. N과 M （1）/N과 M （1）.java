import java.io.*;
import java.util.*;

class Main{
    static int n,m;
    static int[] arr = new int[9];
    static boolean[] check = new boolean[9];
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        func(0);
        System.out.println(sb);
    }
    public static void func(int k){ // 현재 k개까지 수를 택함
        if(k==m){
            for(int i=0; i<m; i++)
                sb.append(arr[i]).append(" ");
            
            sb.append("\n");
            return;
        }
        for(int i=1; i<=n; i++){
            if(!check[i]){
                arr[k]=i;
                check[i] = true;
                func(k+1);
                check[i]=false;
            }
        }
        
    }
}