import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(p(Integer.parseInt(br.readLine())));
    }
    public static int p(int n){
        int r1 = 0, r2 = 1;
        for(int i=0; i<n; i++){
            int x = r2;
            r2+=r1;
            r1 = x;
        }
        return r1;
    }
}