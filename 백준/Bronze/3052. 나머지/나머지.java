import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hs = new HashSet<String>();
        
        for(int i=0;i<10;i++){
            int data = Integer.parseInt(in.readLine());
            hs.add(String.valueOf(data%42));
        }
        System.out.println(hs.size());
    }
}