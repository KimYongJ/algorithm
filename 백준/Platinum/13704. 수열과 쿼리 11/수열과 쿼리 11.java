//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13704
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static long cntSum = 0;
	static int N, K, sqrt;
	static int[] arr;
	static int[] cnt;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());// 수열의 크기(1<=100,000)
		K	= Integer.parseInt(st.nextToken());// 정수K(0<=1,000,000)
		arr	= new int[N + 1];
		cnt = new int[1<<20];
		sqrt= (int)Math.sqrt(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken()) ^ arr[i-1];
		
		ArrayList<Query> query = new ArrayList<>();
		int Q = Integer.parseInt(br.readLine());// 쿼리 수(1<=100,000)
		long ans[] = new long[Q + 1];
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l-1, r, i, l / sqrt));
		}
		
		Collections.sort(query);
		
		int L = 1;
		int R = 0;
		for(Query q : query)
		{
			while(R < q.right) plus(arr[++R]);
			while(q.right < R) minus(arr[R--]);
			while(L < q.left) minus(arr[L++]);
			while(q.left < L) plus(arr[--L]);
			
			ans[q.idx] = cntSum;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void minus(int num) {
		--cnt[num];
		cntSum -= cnt[num ^ K];
	}
	static void plus(int num) {
		cntSum += cnt[num ^ K];
		++cnt[num];
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			this.left = l;
			this.right = r;
			this.idx = i;
			this.fac = f;
		}
		@Override
		public int compareTo(Query o) {
			return fac == o.fac ? right - o.right : fac - o.fac;
		}
	}
}

//6 3			// 수열의 크기(1<=100,000), 정수K(0<=1,000,000)
//1 2 1 1 0 3	// 원소 값(0<=1,000,000)
//2				// 쿼리 수(1<=100,000)
//1 6
//3 5
//답
//7
//0