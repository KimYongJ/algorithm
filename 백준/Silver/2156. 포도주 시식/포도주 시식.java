//https://github.com/KimYongJ/algorithm
class Main{
 public static void main(String[] args)throws Exception{
     int n = read()+3;
     int[] arr = new int[n];
     int[] dp = new int[n];
     
     for(int i=3; i<n; i++)
         arr[i] = read();
     
     for(int i=3; i<n; i++){
    	 int a = arr[i-1]+dp[i-3]+arr[i];
    	 int b = dp[i-2]+arr[i];
         dp[i] = Math.max(Math.max(a,b),dp[i-1]);
     }
     System.out.println(dp[n-1]);
     
 }
    static int read() throws Exception {
        int sum = 0;
        boolean isNegative = false;
        while(true){
            int input = System.in.read();
            if(input=='\n' || input==' ')
                return isNegative ? sum*-1 : sum;
            else if(input=='-')
                isNegative = true;
            else
                sum = (sum*10)+input-'0';
        }
    }
}