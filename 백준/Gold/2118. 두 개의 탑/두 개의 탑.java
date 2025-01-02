//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2118
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int arr[]	= new int[N+1];
		int max		= 0;
		int s		= 0;
		int e		= 1;
		
		for(int i=1; i<=N; i++)
			arr[i] =Integer.parseInt(br.readLine()) + arr[i-1];

		int half = arr[N]>>1;
		int full = arr[N];
		while(s<e && e<=N)
		{
			int diff = arr[e] - arr[s];
			if(diff < half)
			{
				max = Math.max(max, diff);
				++e;
			}
			else if(half < diff)
			{
				max = Math.max(max, full - diff);
				++s;
			}
			else {
				max = half;
				break;
			}
		}
		System.out.print(max);
	}
}
