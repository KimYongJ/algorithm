//https://www.acmicpc.net/problem/17731
//4초 512MB
//5 5	// 일자(1<=100,000), 쿼리수(1<=100,000)
//9 8 7 8 9	// 사건의 종류(1<=1,000,000,000)
//1 2	// 범위
//3 4
//4 4
//1 4
//2 4
//// 답
//9
//8
//8
//16
//16

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int sqrt;
	static int N, Q;
	static int arr[];
	static int brr[];
	static long cnt[];
	static long ans[];
	static long tree[];
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		sqrt = (int)Math.sqrt(N);
		arr = new int[N + 1];
		brr = new int[N + 1];
		cnt = new long[N + 1];
		ans = new long[Q + 1];
		tree = new long[N * 4];
		query = new ArrayList<>();
		
		// 값 압축을 위해 두 배열에 모두 값을 초기화 함
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = brr[i] = Integer.parseInt(st.nextToken());
		// 값 압축을 위한 brr배열 정렬
		Arrays.sort(brr);
		// 값 압축
		for(int i=1; i<=N; i++)
			arr[i] = binarySearch(arr[i]);
		
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			query.add(new Query(s, e, i, s / sqrt));
		}
		
		Collections.sort(query);
		
		int s = 1;
		int e = 0;
		
		for(Query q : query)
		{
			while(e < q.e) plus(arr[++e]);
			while(q.s < s) plus(arr[--s]);
			while(q.e < e) minus(arr[e--]);
			while(s < q.s) minus(arr[s++]);
			
			ans[q.idx] = tree[1]; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}

	static void plus(int idx)
	{
		long next = ++cnt[idx] * brr[idx];
		
		update(1, 1, N, idx, next);
	}
	static void minus(int idx) {
		long next = --cnt[idx] * brr[idx];

		update(1, 1, N, idx, next);
		
	}
	static void update(int treeNode, int s, int e, int targetIdx, long value) {
		if(targetIdx < s || e < targetIdx)
			return;
		
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1],  tree[treeNode << 1 | 1]);
	}
	static int binarySearch(int target) {
		int s = 1;
		int e = N;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(brr[mid] >= target)
			{
				res = mid;
				e = mid - 1;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, fac;
		Query(int s, int e, int idx, int fac){
			this.s=s;
			this.e=e;
			this.idx=idx;
			this.fac=fac;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if((fac & 1)==0)
				return e - o.e;
			
			return o.e - e;
		}
	}
}