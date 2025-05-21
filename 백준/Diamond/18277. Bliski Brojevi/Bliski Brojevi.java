//https://www.acmicpc.net/problem/18277
//2초 512MB
//7 5// 자연수 개수(1<=30,000), 쿼리 수(1<=30,000)
//5 2 3 6 1 4 7// N이하 자연수가 고유하게 주어짐
//3 5
//5 7
//1 2
//3 6
//2 7
////답
//2
//3
//3
//1
//1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int arr[];
	static int ans[];
	static Node tree[];// 세그먼트 트리로 인덱스는 해당 자연수, value는 구간의 최솟값, 최댓값, 값 차이의 최솟값이 담김
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 자연수 개수(1<=30,000)
		Q = Integer.parseInt(st.nextToken());// 쿼리 수(1<=30,000)
		arr = new int[N + 1];
		ans = new int[Q + 1];
		tree = new Node[N * 4];
		query = new ArrayList<>();
		
		// 트리 초기화
		for(int i=N*4-1; i>=0; i--)
			tree[i] = new Node();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1, sqrt = (int)Math.sqrt(N); i<=Q; i++)
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
			while(e < q.e) excute(arr[++e], false);
			while(q.s < s) excute(arr[--s], false);
			while(q.e < e) excute(arr[e--], true);
			while(s < q.s) excute(arr[s++], true);
			
			ans[q.idx] = tree[1].diffMin; 
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	
	static void excute(int targetIdx, boolean isClear) {
		update(1, 1, N, targetIdx, isClear);
	}
	
	static void update(int treeNode, int s, int e, int targetIdx, boolean isClear) {
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			// 초기화인경우
			if(isClear)
			{
				tree[treeNode].min = 1<<30;
				tree[treeNode].max = 0;
			}
			else// 초기화가 아니라 값 세팅인 경우 값 세팅
			{
				tree[treeNode].min = tree[treeNode].max = s;
			}
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, isClear);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, isClear);
		
		merge(tree[treeNode], tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}

	static void merge(Node origin, Node L, Node R) {
		int diff = 1<<30;
		
		if(R.min != 1<<30 && L.max != 0)
			diff = R.min - L.max;
		
		origin.min = Math.min(L.min, R.min);// 양쪽의 최솟 값 중 최솟 값
		origin.max = Math.max(L.max, R.max);//양쪽의 최댓 값 중 최댓 값
		origin.diffMin = Math.min(Math.min(L.diffMin, R.diffMin), diff);
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
	static class Node{
		int min;
		int max;
		int diffMin;
		Node(){
			this.min = 1<<30;
			this.max = 0;
			this.diffMin = 1<<30;
		}
		Node(int min, int max, int diffMin){
			this.min = min;
			this.max = max;
			this.diffMin = diffMin;
		}
	}
}