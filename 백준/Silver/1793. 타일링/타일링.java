//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1793
//1초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BigInteger dp[]	= new BigInteger[251];
		dp[0] = BigInteger.ONE;
		dp[1] = BigInteger.ONE;
		dp[2] = new BigInteger("3");
		
		for(int i=3; i<=250; i++)
			dp[i] = dp[i-1].add(dp[i-2].multiply(new BigInteger("2")));
		
		while(true)
		{
			String str = br.readLine();
			if(str == null || "".equals(str))
				break;
			sb.append(dp[Integer.parseInt(str)]).append('\n');
		}
		
		System.out.print(sb);
	}
}