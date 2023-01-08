import java.io.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        long[] p = new long[91];
        p[0] = 0;
        p[1] = 1;
        for(int i=2; i<x+1; i++)
            p[i] = p[i-1]+p[i-2];
        System.out.println(p[x]);        
    }
}