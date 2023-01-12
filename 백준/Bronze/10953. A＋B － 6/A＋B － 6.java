import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        for(int i=0; i<l; i++){
            st = new StringTokenizer(br.readLine(),",");
            sb.append(Integer.parseInt(st.nextToken())+
                      Integer.parseInt(st.nextToken())).append("\n");
        }
        System.out.println(sb);
    }
}