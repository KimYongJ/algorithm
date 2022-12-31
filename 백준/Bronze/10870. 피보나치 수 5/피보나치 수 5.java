import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(p(Integer.parseInt(br.readLine())));
    }
    public static int p(int n){
        if(n<=1) return n;
        else return p(n-1) + p(n-2);
    }
}