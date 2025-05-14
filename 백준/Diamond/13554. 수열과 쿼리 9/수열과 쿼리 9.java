//https://www.acmicpc.net/problem/13554
//6초 / 512MB / 이하 테케 설명
//2		// 수열의 크기(1<=100,000)
//1 3	// 배열(1) 1<=100,000
//2 4	// 배열(2) 1<=100,000
//4		// 쿼리 개수(1<=100,000)
//1 2 12// i, j, k(1<=100,000)가 주어짐
//1 1 5
//1 2 6
//2 2 4
//정답 : i j k : i ≤ p, q ≤ j이면서 Ap × Bq ≤ k 인 (p, q) 쌍의 개수를 출력한다.
//4
//1
//3
//0
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static final int LEN = 100_000;
	static int N, Q;
	static int sqrt;
	static int[] A, B;
	static int[] fenwickTree_A, fenwickTree_B;
	static long[] ans;
	static long[] diff;
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N + 1];
		B = new int[N + 1];
		diff = new long[N + 1];
		sqrt = (int)Math.sqrt(N);
		fenwickTree_A = new int[LEN + 1];
		fenwickTree_B = new int[LEN + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			B[i] = Integer.parseInt(st.nextToken());
		
		Q = Integer.parseInt(br.readLine());
		ans = new long[Q + 1];
		query = new ArrayList<>();
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, k, i, l/sqrt));
		}
		
		Collections.sort(query);
		
		int L = 1;
		int R = 0;

		for(Query q : query)
		{
			while(R < q.right) excute(++R, 1);
			while(q.left < L) excute(--L, 1);
			while(q.right < R) excute(R--, -1);
			while(L < q.left) excute(L++, -1);

			int sq = (int)Math.sqrt(q.k);

			long res = 0;

			for(int i = 1; i<= sq; i++)
			{
				// 중복을 제거하며 모두 탐색해야 하기 때문에 B를 기준으로 나눔
				// 첫번 째 B는 1 ~ sq까지				/	A는 1 ~ 가능한 부분까지
				// 두번 째 B는 sq + 1 ~ 가능한 부분까지	/	A는 1 ~ sq까지
				res += query(i, i, fenwickTree_B) * query(1, q.k/i, fenwickTree_A);
				res += query(sq + 1, q.k/i, fenwickTree_B) * query(i, i, fenwickTree_A);
			}

			ans[q.idx] = res;
		}
		
		print();
	}
	static void excute(int idx, int plus) {
		update(A[idx], plus, fenwickTree_A);
		update(B[idx], plus, fenwickTree_B);
	}
	static void update(int idx, int plus, int[] tree) {
		while(idx <= LEN)
		{
			tree[idx] += plus;
			idx += idx & - idx;
		}
	}
	static long query(int l, int r, int[] tree) {
		return sum(r, tree) - sum(l - 1, tree);
	}
	static long sum(int idx, int[] tree) {
		long res = 0;
		while(idx > 0)
		{
			res += tree[idx];
			idx -= idx & -idx;
		}
		return res;
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static class Query implements Comparable<Query>{
		int left, right, k, idx, fac;
		Query(int l, int r, int k, int i, int f){
			this.left = l;
			this.right = r;
			this.idx = i;
			this.k = k;
			this.fac = f;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac) // 구간이 다르면 구간 기준 오름차순
				return fac - o.fac;
			// 구간이 짝수인 경우 right 기준 오름차순
			if((fac & 1)==0)
				return right - o.right;
			// 구간이 홀수인 경우 right 기준 내림차순
			return o.right - right;
		}
	}
}