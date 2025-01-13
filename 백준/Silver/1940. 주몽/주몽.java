//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1940

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1<=만오천
		int M		= Integer.parseInt(br.readLine());	// 1<=천만
		int cnt		= 0;
		boolean v[] = new boolean[10_000_001];
		int arr[]	= new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			v[arr[i] = Integer.parseInt(st.nextToken())] = true;
		
		for(int a : arr)
		{
			int target = M - a;
			if(0 <= target && target <= 10_000_001)
			{
				if(v[target])
					++cnt;
			}
		}
		
		System.out.print(cnt / 2);
	}
}