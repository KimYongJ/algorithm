import java.util.*;
import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> hs = new HashSet<Integer>();
        
        for(int i=0;i<10;i++) hs.add(Integer.parseInt(in.readLine())%42);

        System.out.println(hs.size());
    }
}