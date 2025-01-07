//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10713
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//철도수(2<=십만)
		int M		= Integer.parseInt(st.nextToken());	//여행일(2<=십만)
		long cnt[]	= new long[N+1];
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		while(M-->1)
		{
			int next = Integer.parseInt(st.nextToken());
			if(start < next)
			{
				cnt[start]++;
				cnt[next]--;
			}
			else
			{
				cnt[start]--;
				cnt[next]++;
			}
			start = next;
		}
		
		for(int i=1; i<=N; i++)
			cnt[i] += cnt[i-1];
		
		long res = 0;
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			long t = Integer.parseInt(st.nextToken());	// 그냥 타는 티켓비
			long p = Integer.parseInt(st.nextToken());	// 카드사용시 통과하는 가격
			long g = Integer.parseInt(st.nextToken());	// IC카드 구매비
			
			res += Math.min(t*cnt[i], g + p*cnt[i]);
		}
		
		System.out.print(res);
	}
}