import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int sum = 0;
        while(x>0){
            int i = x;
            int cnt = 0;
            while(i%5==0){
                i/=5;
                cnt++;
            }
            sum += cnt;
            x--;
        }
        
        
        System.out.println(sum);
    }
}