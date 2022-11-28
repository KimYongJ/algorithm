import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine()," ");
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine()," ");
        for(int i=0;i<len;i++){
            int token = Integer.parseInt(st.nextToken());
            if(x>token){
                sb.append(token).append(" ");
            }
        }
        sb.append("\n");
        System.out.println(sb);
        
    }
}