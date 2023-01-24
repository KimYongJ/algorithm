import java.io.*;
import java.util.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(st.nextToken());
        int a,b;
        while(l-->0){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(a%10==0){
                sb.append(10).append("\n");
                continue;
            }else {
                int dummy = 1;
                for(int i=0; i<b; i++)
                    dummy = dummy*a%10;
                sb.append(dummy).append("\n");
            }
        }
        System.out.println(sb);
    }
}