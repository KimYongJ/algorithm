import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        for(int i=1; i<n; i++){
            int sum = i;
            for(char c : String.valueOf(i).toCharArray())
                sum += c-'0';
            if(sum == n){
                System.out.println(i);
                return;
            }
        }
        System.out.println(0);
    }
}