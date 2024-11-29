//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1053
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main{
	
	static int dp[][];
	static int len;
	static String str;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		len	= str.length();
		dp	= new int[len][len];
		
		for(int[] d : dp)
			Arrays.fill(d, -1);
		
		int ans = solve(0, len-1, str);
		
		for(int i=0; i<len; i++)
			for(int j=0; j<len; j++)
			{
				for(int[] d : dp)
					Arrays.fill(d, -1);
				
				StringBuilder tmp = new StringBuilder(str);
				char c = tmp.charAt(i);
				tmp.setCharAt(i, tmp.charAt(j));
				tmp.setCharAt(j, c);
				
				ans = Math.min(ans, solve(0, len-1, tmp.toString()) + 1);
			}
		
		System.out.print(ans);
	}
	static int solve(int l, int r, String s) {
		if(dp[l][r] != -1)
			return dp[l][r];
		if(r <= l)
			return 0;
		
		int a = solve(l + 1, r, s) + 1;
		int b = solve(l, r - 1, s) + 1;
		int c = solve(l + 1, r - 1, s) + (s.charAt(l) != s.charAt(r) ? 1 : 0);
		
		return dp[l][r] = Math.min(a, Math.min(b, c));
	}
}