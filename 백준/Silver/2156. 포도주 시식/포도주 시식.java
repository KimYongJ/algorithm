//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
 public static void main(String[] args)throws Exception{
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int n = Integer.parseInt(br.readLine())+3;
     int max = Integer.MIN_VALUE;
     int[] arr = new int[n];
     int[] dp = new int[n];
     
     for(int i=3; i<n; i++)
         arr[i] = Integer.parseInt(br.readLine());
     
     for(int i=3; i<n; i++){
    	 int a = arr[i-1]+dp[i-3]+arr[i];
    	 int b = dp[i-2]+arr[i];
         dp[i] = Math.max(Math.max(a,b),dp[i-1]);
         max = Math.max(dp[i], max);
     }
     System.out.println(max);
     
 }
}