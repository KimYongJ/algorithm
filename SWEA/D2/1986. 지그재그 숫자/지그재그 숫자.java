import java.io.*;

class Solution{
 	public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        int sum;
        for(int r = 1; r<=l; r++){
            sum = 0;
            int num = Integer.parseInt(br.readLine());
            for(int i=2; i<=num; i++){
                sum += i%2==0 ? -i : i;
            }            
            sb.append("#").append(r).append(" ").append(sum+1).append("\n");
        }
        System.out.println(sb);
    }
    
}