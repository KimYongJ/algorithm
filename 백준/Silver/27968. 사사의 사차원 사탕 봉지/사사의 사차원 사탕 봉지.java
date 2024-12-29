//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27968
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int N, M;
	static long psum[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N		= Integer.parseInt(st.nextToken());	// 아이 수(1<=삼십만)
		M		= Integer.parseInt(st.nextToken());	// 최대 횟수(1<=삼십만)
		psum	= new long[M + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++)
			psum[i] = psum[i-1] + Long.parseLong(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++)
		{
			int res = binarySearch(Long.parseLong(br.readLine()));
			sb.append(res == -1 ? "Go away!" : res)
				.append('\n');
		}
		System.out.print(sb);
	}
	public static int binarySearch(long target) {
		int s = 1;
		int e = M;
		int r = -1;
		while(s <= e)
		{
			int idx = (s + e) >> 1;
			if(target <= psum[idx]) {
				e = idx - 1;
				r = idx;
			}
			else s = idx + 1;
		}
		return r;
	}
}