//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16570
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 2<=백만
		int arr[]	= new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=N-1; i>=0; i--)
			arr[i] = Integer.parseInt(st.nextToken());	// int범위 안 숫자

		int fail[]	= new int[N];
		int max		= 0;
		int cnt		= 0;
		
		for(int i=1,j=0; i<N; i++)
		{
			while(0<j && arr[i] != arr[j])
				j = fail[j - 1];
			
			if(arr[i] == arr[j])
				max = Math.max(max, fail[i] = ++j);
		}
		if(max == 0)
		{
			System.out.print(-1);
			return;
		}

		for(int i=0; i<N; i++)
			if(fail[i] == max)
				++cnt;
		
		System.out.printf("%d %d", max, cnt);
	}
}