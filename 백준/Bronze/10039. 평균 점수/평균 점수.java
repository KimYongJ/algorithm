import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        for(int i=0;i<5;i++){
            int num = Integer.parseInt(br.readLine());
            sum += num>40 ? num : 40;
        }
        System.out.println(sum/5);        
        
    }
}