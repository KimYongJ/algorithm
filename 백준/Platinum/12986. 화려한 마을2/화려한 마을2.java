//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12986
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int max;
	static int [] cnt, freq;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int OFF_SET = 100_001;
		int N		= Integer.parseInt(st.nextToken());// 배열 수(1<=100,000)
		int Q		= Integer.parseInt(st.nextToken());// 질의 수(1<=100,000)
		int arr[]	= new int[N + 1];
		int ans[]	= new int[Q + 1];
		int sqrt	= (int) Math.sqrt(N);
		cnt		= new int[OFF_SET << 1];
		freq	= new int[N + 1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken()) + OFF_SET;
		
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
		for(Query q : query)
		{
			while(R < q.right) plus(arr[++R]);
			while(q.right < R) minus(arr[R--]);
			while(L < q.left) minus(arr[L++]);
			while(q.left < L) plus(arr[--L]);
			
			ans[q.idx] = max; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void minus(int num) {
		if(--freq[cnt[num]] == 0 && max == cnt[num])
			--max;
		++freq[--cnt[num]];
	}
	static void plus(int num) {
		--freq[cnt[num]];
		++freq[++cnt[num]];
		max = Math.max(max, cnt[num]);
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

//10 3						// 배열 수(1<=100,000), 질의 수(1<=100,000)
//-1 -1 1 1 1 1 3 10 10 10	// 원소 수(-100,000<=100,000)
//2 3
//1 10
//5 10
//답
//1
//4
//3