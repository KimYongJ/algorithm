//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1027

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static double arr[];
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N	= Integer.parseInt(br.readLine());
		arr = new double[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int ans = 0;
		
		for(int i=1; i<=N; i++)
			ans = Math.max(ans, get(i));
		
		System.out.print(ans);
	}
	public static int get(int idx) {
		int cnt = 0;
		double incl = 0.0;
		for(int i=idx - 1; 1<=i; i--)
		{
			double nextIncl = (arr[idx] - arr[i]) / (idx - i);
			if(i == idx -1 || incl > nextIncl) {
				incl = nextIncl;
				++cnt;
			}
		}
		for(int i=idx + 1; i<=N; i++) {
			double nextIncl = (arr[idx] - arr[i]) / (idx - i);
			if(i == idx + 1 || incl < nextIncl) {
				incl = nextIncl;
				++cnt;
			}
		}
		
		
		return cnt;
	}
}