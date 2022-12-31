import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(fac(n));
    }
    public static int fac(int n){
        if(n==1 || n==0){
            return 1;
        }
        else{
            return fac(n-1)*n; 
        }
    }
}