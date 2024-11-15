//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2670
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		double arr[] = new double[T+1];
		double dp[] = new double[T+1];
		double res = 0;
		
		for(int i=1; i<=T; i++)
		{
			arr[i] = Double.parseDouble(br.readLine());
			dp[i] = Math.max(arr[i], arr[i] * dp[i-1]);
			res = Math.max(res, dp[i]);
		}
		System.out.print(String.format("%.3f", res));
	}
}