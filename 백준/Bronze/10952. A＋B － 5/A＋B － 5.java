import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(in.readLine()," ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a==0&&b==0){
                break;
            }
            out.append(a+b).append("\n");
        }
        System.out.print(out);    
        
        
    }
}