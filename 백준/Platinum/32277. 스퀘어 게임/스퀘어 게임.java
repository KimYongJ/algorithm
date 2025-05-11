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
	static int count;	// 스퀘어 횟수
	static int cnt[];	// 각 숫자가 나온 횟수 카운팅할 배열 최대 5만까지
	static int arr[];	// 입력된 초기 원소 들 
	static int ans[];	// 각 쿼리마다 결과를 담을 배열
	static int N, Q;
	static int sqrt;	// Mo's 알고리즘을 위한 제곱근
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());// 테스트 케이스 개수(1<=32)
		
		for(int t=1; t<=T; t++)
		{		
			N		= Integer.parseInt(br.readLine());// 원소의 수(1<=50,000)
			arr		= new int[N + 1];
			cnt		= new int[MAX + 1];
			sqrt	= (int) Math.sqrt(N);
			count	= 0;
			query	= new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)// 값 1<=1,000,000,000
				arr[i] = Integer.parseInt(st.nextToken());
			
			
			Q	= Integer.parseInt(br.readLine());// 쿼리 수(1<=50,000)
			ans	= new int[Q + 1];
			
			for(int i=1; i<=Q; i++)
			{
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				query.add(new Query(l, r, i, l / sqrt));
			}
			
			Collections.sort(query);
			
			int L = 1;
			int R = 0;
			
			for(Query q : query)
			{
				while(q.left < L)	plus(arr[--L]);
				while(R < q.right)	plus(arr[++R]);
				while(L < q.left)	minus(arr[L++]);
				while(q.right < R)	minus(arr[R--]);
				

				ans[q.idx] = count;
			}
			
			sb.append("Case #").append(t).append('\n');
			
			for(int i=1; i<=Q; i++)
				sb.append(ans[i]).append('\n');
		}
		
		System.out.print(sb);
	}
	static void minus(int now) {
		if(now > MAX || now == 1)// 범위 초과시 종료
			return;
		
		if(cnt[now] == 0)		// 현재 줄여야하는데 줄일 값이 0이면 상위에서 줄여야 함
		{
			--count;			// 스퀘어 횟수 -1 차감
			cnt[now] = now;		// 현재 값이 0이니, now로 원복
			minus(now * now);	// 상위로 넘어감
		}
		
		--cnt[now];				// 현재 카운트 감소
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
			// 구간이 다르면 구간 기준 오름차순 정렬
			if(this.fac != o.fac)
				return this.fac - o.fac;
			// 구간이 짝수면, right기준 오름차순 정렬
			if(fac % 2 == 0)
				return this.right - o.right;
			// 구간이 홀수면, right기준 내림차순
			return o.right - this.right;
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