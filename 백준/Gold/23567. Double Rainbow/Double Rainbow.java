//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/23567
//1초 / 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());	//점의 수 1<=만
		int K		= Integer.parseInt(st.nextToken());	//점에 입력될 숫자의 수 1<=N
		int arr[]	= new int[N];
		int cnt[]	= new int[K+1];
		int origin[]= new int[K+1];
		
		for(int i=0; i<N; i++)
			++origin[arr[i] = Integer.parseInt(br.readLine())];
		
		int s = 0;
		int e = 0;
		int cntK = 0;
		int minLen = Integer.MAX_VALUE;
		
		while(e < N)
		{
			if(cnt[arr[e]]++ == 0)
				++cntK;
			
			if(K == cntK)
			{
				while(K == cntK)
				{
					if(validate(origin, cnt))
						minLen = Math.min(minLen, e - s + 1);
					
					if(--cnt[arr[s++]] == 0)
						--cntK;
				}
			}
			
			++e;
		}
		
		
		if(minLen == Integer.MAX_VALUE)
			minLen = 0;
		
		System.out.print(minLen);
	}
	public static boolean validate(int o[], int c[]) {
		for(int i=1; i<o.length; i++) {
			if(o[i] - c[i] == 0)
				return false;
		}
		return true;
	}
}