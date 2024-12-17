//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14444
//2초, 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	public static void manachers(int A[], char[] str, int len) {
		int r = 0;
		int p = 0;
		for(int i=0; i<len; i++)
		{
			if(i <= r)
				A[i] = Math.min(A[2*p - i], r - i);
			else
				A[i] = 0;
			
			while(0 <= i - A[i] - 1 && i + A[i] + 1 < len && str[i - A[i] - 1] == str[i + A[i] + 1])
				++A[i];
			
			if(r < i + A[i])
			{
				r = i + A[i];
				p = i;
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(char c : br.readLine().toCharArray())
			sb.append('#').append(c);
		sb.append('#');
		
		int len = sb.length();
		int A[]	= new int[len];
		
		manachers(A, sb.toString().toCharArray(), len);
		
		int ans = -1;
		
		for(int i=0; i<len; i++)
			ans = Math.max(ans, A[i]);
		
		System.out.print(ans);
	}
}