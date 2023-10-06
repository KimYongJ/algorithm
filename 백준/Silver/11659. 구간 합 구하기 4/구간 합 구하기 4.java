// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        int[] dp = new int[n+1];
        dp[1] = Integer.parseInt(st.nextToken());
        for(int i=2; i<=n; i++){
            dp[i] = Integer.parseInt(st.nextToken()) + dp[i-1];
        }
        
        for(int t=0; t<m; t++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int sum = 0;
            if(i==1) {
            	sum = dp[j];
            }else {
            	sum = dp[j] - dp[i-1];
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb.toString());
    }
}