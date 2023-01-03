import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int result = 0;
        x = x<Math.abs(x-w) ? x : Math.abs(x-w);
        y = y<Math.abs(y-h) ? y : Math.abs(y-h);
        
        
        
        System.out.println(x<y ? x : y);
    }
}