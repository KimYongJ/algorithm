//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10025

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Pos{
	int val, idx;
	Pos(int v, int i){val=v; idx=i;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());		// 양동이개수(1<=십만)
		int K		= Integer.parseInt(st.nextToken())*2;	// 이동가능거리(1<=이백만)
		Pos arr[]	= new Pos[N];
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i] = new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		int max = 0;
		
		Arrays.sort(arr,(a,b)-> a.idx - b.idx);
		max = Math.max(cal(arr, N , K), max);
		
		System.out.print(max);
	}
	public static int cal(Pos arr[], int N, int K) {
		int s	= 0;
		int e	= 0;
		int max = 0;
		int sum = 0;
		
		while(e<N)
		{
			while(e<N && arr[e].idx - arr[s].idx<= K)
			{
				sum += arr[e].val;
				++e;
			}
			
			max = Math.max(sum, max);
			
			sum -= arr[s].val;
			++s;
			
		}
		return max;
	}
}