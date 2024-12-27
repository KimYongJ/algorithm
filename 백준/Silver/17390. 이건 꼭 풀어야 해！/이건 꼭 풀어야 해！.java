//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17390
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();// 수열 길이1<=삼십만
		int Q		= read();// 질문개수 N<=삼십만
		int psum[]	= new int[N+1];
		
		for(int i=1; i<=N; i++)
			psum[i] = read();// 1<=천
		
		Arrays.sort(psum);
		
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			int l = read();
			int r = read();
			sb.append(psum[r] - psum[l-1]).append('\n');
		}
		System.out.print(sb);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}