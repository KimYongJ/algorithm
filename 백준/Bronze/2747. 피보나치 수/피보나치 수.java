import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] p = new int[46];
        p[1] = 1;
        for(int i=2; i<=n; i++)
            p[i] = p[i-1]+p[i-2];
        System.out.println(p[n]);
    }
}
