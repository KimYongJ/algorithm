//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1806
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 수열길이 (10 ≤ N < 100,000)
		int S		= Integer.parseInt(st.nextToken());	// 타겟(0 < S ≤ 100,000,000)
		int left	= 0;
		int right	= 1;
		int min		= 1<<30;
		int psum[]	= new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			psum[i] = psum[i-1] + Integer.parseInt(st.nextToken());
		
		while(right <= N)
		{
			int sum = psum[right] - psum[left];
			 if(sum < S)
				++right;
			else if(S <= sum)
			{
				min = Math.min(min, right - left);
				++left;
			}
		}
		System.out.print(min == 1<<30 ? 0 : min);
	}
}