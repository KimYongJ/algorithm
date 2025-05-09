//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/32277
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static final int MAX = 50_000;
	static int count;
	static int cnt[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());// 테스트 케이스 개수(1<=32)
		for(int t=1; t<=T; t++)
		{		
			int N		= Integer.parseInt(br.readLine());// 원소의 수(1<=50,000)
			int arr[]	= new int[N + 1];
			int sqrt	= (int) Math.sqrt(N);
			cnt			= new int[MAX + 1];

			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)// 값 1<=1,000,000,000
				arr[i] = Integer.parseInt(st.nextToken());
			
			ArrayList<Query> query = new ArrayList<>();
			int Q = Integer.parseInt(br.readLine());// 쿼리 수(1<=50,000)
			int ans[] = new int[Q + 1];
			for(int i=1; i<=Q; i++)
			{
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				query.add(new Query(l, r, i, l / sqrt));
			}
			
			Collections.sort(query);
			
			count = 0;
			int L = 1;
			int R = 0;
			for(Query q : query)
			{
				while(R < q.right)
					plus(arr[++R]);
			
				while(q.right < R)
					minus(arr[R--]);

				while(L < q.left)
					minus(arr[L++]);

				while(q.left < L)
					plus(arr[--L]);

				ans[q.idx] = count;
			}
			
			sb.append("Case #").append(t).append('\n');
			for(int i=1; i<=Q; i++)
				sb.append(ans[i]).append('\n');
		}
		System.out.print(sb);
	}
	static void minus(int now) {
		if(now > MAX || now == 1)// 처리 불가일시 false 리턴
			return;
		
		if(cnt[now] > 0)	// 줄인경우 true
		{
			--cnt[now];
			return;
		}
		
		minus(now * now);
		cnt[now] = now - 1;
		--count;
	}
	static void plus(int now) {
		if(now > MAX || now == 1)
			return;
		
		++cnt[now];
		
		if(cnt[now] == now)
		{
			++count;
			cnt[now] = 0;
			plus(now * now);
		}
		
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			this.left = l;
			this.right= r;
			this.idx = i;
			this.fac = f;
		}
		@Override
		public int compareTo(Query o) {
			return fac != o.fac ? fac - o.fac : right - o.right;
		}
	}
}
//1							// 테스트 케이스 개수(1<=32)
//12						// 원소의 수(1<=50,000)
//2 3 2 3 4 3 2 3 2 1 2 2	// 값 1<=1,000,000,000
//3							// 쿼리 수(1<=50,000)
//1 12
//2 7
//3 8
//// 출력은 CASE #(테케번호)
//Case #1
//5
//2
//2