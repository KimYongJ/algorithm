//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2531
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int cnt;
	static int N, D, K, C, res, B[];
	static boolean visit[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());	// 초밥 벨트에 놓인 접시의 수(2<=삼만)
		D		= Integer.parseInt(st.nextToken());	// 초밥의 적힌 최대 숫자(2<=삼천)
		K		= Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수(1<=C<=D)
		C		= Integer.parseInt(st.nextToken());	// 쿠폰번호(1<=C<=D)
		res 	= 0;
		B		= new int[N];
		visit	= new boolean[D+1];
		
		for(int i=0; i<N; i++)
			B[i] = Integer.parseInt(br.readLine());
		
		int i = -1;
		while(++i != N)
		{
			cnt = 0;
			DFS(i, 0);
		}
		
		System.out.print(res);
	}
	public static void DFS(int idx, int depth) {
		if(depth == K)
		{
			if(!visit[C])
				++cnt;
			res = Math.max(cnt, res);
			return;
		}
		
		if(idx == N)
			idx = 0;
		
		if(!visit[B[idx]])
		{
			++cnt;
			visit[B[idx]] = true;
			DFS(idx + 1, depth + 1);
			visit[B[idx]] = false;
		}
		else
			DFS(idx + 1, depth + 1);
	}
}