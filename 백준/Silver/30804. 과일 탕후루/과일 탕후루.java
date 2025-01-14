//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/30804
//2초 / 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	//개수(1<=이십만)
		int arr[]	= new int[N];
		int dp[]	= new int[10];
		int max		= 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int s=0, e=0, cnt = 0; e<N; e++)
		{
			arr[e] = Integer.parseInt(st.nextToken());	// 1<=9사이 정수
			if(dp[arr[e]]++ == 0)
				++cnt;
			
			while(cnt == 3)
				if(--dp[arr[s++]] == 0)
					--cnt;

			max = Math.max(max, e - s + 1);
		}
		
		System.out.print(max);
	}
}