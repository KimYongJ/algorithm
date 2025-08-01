//https://www.acmicpc.net/problem/24955
//1초 512MB
//3 2 // 집의 수(2≤N≤1,000), 놀이 횟수 (1≤Q≤1,000)
//10 9 1// N개의 대문에 쓰여있는 수가 공백으로 주어짐(1≤Ai≤1,000,000,000(1≤i≤N))
//1 2 // N-1개의 도로 정보가 주어짐(1≤ai,bi≤N)
//2 3
//1 3 // 놀이횟수만큼 출발노드, 도착노드가 각각 주어진다. (1≤xi,yi≤N)
//3 1
//첫 번째 줄부터Q번째 줄에 걸쳐,i번째 줄에는i번째 놀이의 결과를1,000,000,007로 나눈 나머지를 출력한다.
//1091
//1910
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final long mod = 1_000_000_007;
	static int N, Q;
	static int s, e;
	static long num[];
	static List<Integer> adList[];
	static StringBuilder sb;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 집의 수(2≤N≤1,000)
		Q = Integer.parseInt(st.nextToken());// 놀이 횟수 (1≤Q≤1,000)
		num = new long[N + 1];
		adList = new ArrayList[N + 1];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			num[i] = Integer.parseInt(st.nextToken());
			adList[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			dfs(s, 0,num[s]);
		}
		System.out.print(sb);
	}
	static void dfs(int idx, int parent, long ans) {
		if(idx == e)
		{
			sb.append(ans).append('\n');
			return;
		}
		
		for(int next : adList[idx])
			if(next != parent)
				dfs(next, idx, getAns(ans, next));
		
	}
	static long getAns(long ans, int idx) {
		return (((ans * getDigit(num[idx]))%mod) + num[idx]) %mod;
	}
	static long getDigit(long now) {
		long mul = 1;
		while(now != 0)
		{
			mul = (mul * 10)%mod;
			
			now /= 10;
		}
		return mul;
	}
}