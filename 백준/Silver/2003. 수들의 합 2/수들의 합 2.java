//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2003

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	// 1<=만
		int M		= Integer.parseInt(st.nextToken());	// 1<=억
		int cnt		= 0;
		int arr[]	= new int[N+1];
		
		st		= new StringTokenizer(br.readLine());
		arr[1]	= Integer.parseInt(st.nextToken());
		for(int i=2; i<=N; i++)
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		
		int L = 1;
		int R = 1;
		while(L <= N && R<= N)
		{
			int sum = arr[R]- arr[L-1];
			if(sum == M)
			{
				++cnt;
				++R;
			}
			else if(sum < M)
				++R;
			else
				++L;
		}
		System.out.print(cnt);
	}
}