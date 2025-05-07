//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13028
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 배열길이(1<=100,000)
		int Q		= Integer.parseInt(st.nextToken());// 질의수(1<=100,000)
		int arr[]	= new int[N + 1];
		int ans[]	= new int[Q + 1];
		int cnt[]	= new int[100_001];
		int sqrt	= (int)Math.sqrt(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 원소 수 1<=100,000
		
		ArrayList<Query> query = new ArrayList<>();
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
		int count = 0;
		for(Query q : query)
		{
			while(R < q.right) {
				++R;
				if(++cnt[arr[R]] == 3)
					++count;
			}
			while(q.right < R) {
				if(--cnt[arr[R]] == 2)
					--count;
				--R;
			}
			while(L < q.left) {
				if(--cnt[arr[L]] == 2)
					--count;
				++L;
			}
			while(q.left < L) {
				--L;
				if(++cnt[arr[L]] == 3)
					++count;
			}
			
			ans[q.idx] = count;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
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
			return fac == o.fac ? right - o.right : fac - o.fac;
		}
	}
}
//x, y 구간안에 세번 이상 등장하는 수의 종류의 개수를 말해주면 된다.
//9 7						// 배열길이(1<=100,000), 질의수(1<=100,000)
//3 2 3 1 3 1 2 1 1	// 원소 수 1<=100,000
//3 7
//1 7
//8 9
//3 7
//1 3
//2 4
//1 8
////답
//0
//1
//0
//0
//0
//0
//2