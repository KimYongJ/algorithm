//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27496
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 남은시간 1<=백만
		int L		= Integer.parseInt(st.nextToken());	// 지속시간 1<=만<=N
		int arr[]	= new int[N+1];
		int psum[]	= new int[N+1];
		int cnt		= 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=L; i++)
		{
			psum[i] = psum[i-1] + arr[i];
			if(129 <= psum[i] && psum[i] <= 138)
				++cnt;
		}
		
		for(int i=L+1,j=1; i<=N; i++,j++)
		{
			psum[i] = psum[i-1] + arr[i] - arr[j];
			if(129 <= psum[i] && psum[i] <= 138)
				++cnt;
		}
		
		System.out.print(cnt);
	}
}