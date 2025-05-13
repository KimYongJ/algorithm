//https://www.acmicpc.net/problem/13550
//4초 512MB / 이하 테스트케이스 설명
//5 10		// 수열의 크기(1<=100,000), K(2<=1,000,000)
//2 3 5 2 3	// 원소 값 1<=1,000,000,000
//2			// 쿼리 개수(1<=100,000)
//1 3		// 구간 l <= r
//2 4
//// 답
//3
//3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int sqrt;
	static int N, K, Q;
	static int arr[];
	static int ans[];
	static int fac[];// N / sqrt 한 구간을 담을 배열
	static int cnt[];// 인덱스 차이를 담을 배열
	static ArrayList<Query> query;
	static ArrayDeque<Integer>[] idxList;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 수열의 크기(1<=100,000)
		K = Integer.parseInt(st.nextToken());// K(2<=1,000,000)
		idxList = new ArrayDeque[K];
		sqrt = (int)Math.sqrt(N);
		fac = new int[N / sqrt + 1];
		cnt = new int[N + 1];
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = (arr[i-1] + Integer.parseInt(st.nextToken())) % K;
		
		for(int i=0; i<K; i++)
			idxList[i] = new ArrayDeque<>();
		
		Q = Integer.parseInt(br.readLine());// 쿼리 개수(1<=100,000)
		ans = new int[Q + 1];
		query = new ArrayList<>();
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l-1, r, i, l / sqrt));
		}
		
		Collections.sort(query);
		
		int R = 0;
		int L = 1;
		for(Query q : query)
		{
			while(R < q.right) excute(++R, 1);
			while(q.left < L) excute(--L, 2);
			while(q.right < R) excute(R--, 3);
			while(L < q.left) excute(L++, 4);
			
			ans[q.idx] = find();
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void excute(int idx, int flag) {
		cal(arr[idx], -1);
		
		switch(flag)
		{
			case 1: idxList[arr[idx]].addLast(idx); break;
			case 2: idxList[arr[idx]].addFirst(idx);break;
			case 3: idxList[arr[idx]].removeLast(); break;
			case 4: idxList[arr[idx]].removeFirst();break;
		}
		
		cal(arr[idx], 1);
	}
	static void cal(int num, int plus) {
		if(idxList[num].size() > 0)
		{
			int diff = idxList[num].peekLast() - idxList[num].peekFirst();
			fac[diff / sqrt] += plus;
			cnt[diff] += plus;
		}
	}
	static int find() {
		for(int i=N/sqrt; i>=0; i--)
		{
			if(fac[i] == 0)
				continue;
			
			int s = i * sqrt;
			int e = Math.min(N, s + sqrt - 1);
			
			while(e >= s)
			{
				if(cnt[e] > 0)
					return e;
				--e;
			}
		}
		return 0;
	}
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			left = l;
			right = r;
			idx = i;
			fac = f;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac & 1) == 0)
				return right - o.right;
			
			return o.right - right;
		}
	}
}