import java.io.*;
import java.util.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        System.out.println(Integer.parseInt(st.nextToken())*-1
                          +Integer.parseInt(st.nextToken())*2);
    }
}