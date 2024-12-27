//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17390
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수열 길이1<=삼십만
		int Q = Integer.parseInt(st.nextToken());	// 질문개수 N<=삼십만
		int psum[] = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] = Integer.parseInt(st.nextToken());	// 1<=천
		
		Arrays.sort(psum);
		
		for(int i=1; i<=N; i++)
			psum[i] += psum[i-1];
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			sb.append(psum[r] - psum[l-1]).append('\n');
		}
		System.out.print(sb);
	}
}
