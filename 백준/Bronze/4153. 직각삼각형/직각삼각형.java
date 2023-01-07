import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int[] a = new int[3];
        while(true){
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<3; i++)
                a[i] = Integer.parseInt(st.nextToken());
            if(a[0]==0 && a[1]==0 && a[2]==0) break;
            Arrays.sort(a);
            if(a[2]*a[2] == a[0]*a[0] + a[1]*a[1]){
                sb.append("right").append("\n");
            }else sb.append("wrong").append("\n");
        }
        System.out.println(sb);
        
    }
}