import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(f(n));
    }
    public static int f(int n){
        int[] p = new int[46];
        p[0] = 0; p[1] = 1;
        for(int i=2; i<=n; i++){
            p[i] = p[i-1]+p[i-2];
        }
        return p[n];
    }
}