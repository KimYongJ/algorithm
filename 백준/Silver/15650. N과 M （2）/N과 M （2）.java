import java.io.*;
import java.util.*;

class Main{
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int n,m;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        back(0,1);
        System.out.println(sb);
    }
    public static void back(int depth,int cnt){
        if(m==depth){
            for(int x : arr)
                sb.append(x).append(" ");
            sb.append("\n");
            return;
        }
        for(int i=cnt; i<=n; i++){
                arr[depth] = i;
                back(depth+1,i+1);
        }
    }
}