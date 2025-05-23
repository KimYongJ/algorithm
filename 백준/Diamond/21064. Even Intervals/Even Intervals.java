//https://www.acmicpc.net/problem/21064
//5 5// 배열 원소 수(1<=50,000), 쿼리가 주어짐(1<=200,000)
//2 4 10 16 6// 원소 값(유일성 보장) 0<=1,000,000,000
//0 2// 쿼리가 주어짐 인덱스 0부터 시작
//1 3
//0 3
//2 3
//0 4
////해당 구간의 값들을 정렬했을 때, 짝수번 인덱스 위치에 있는 값들을 모두 더한 값 출력(INDEX는 0부터 시작)
//12
//20
//12
//10
//24

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static final long MOD = 1_000_000_007;
	static int N, Q;
	static int arr[];// 주어지는 원소의 압축 값을 담을 배열
	static int brr[];// 주어지는 원소의 압축 값을 원래대로 되돌리거나, 압축시 사용할 배열
	static long ans[];// 최종 결과를 담을 배열
	static Node tree[];// 세그먼트 트리
	static List<Query> query;// 주어지는 쿼리를 담을 리스트
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 배열 원소 수(1<=50,000)
		Q = Integer.parseInt(st.nextToken());// 쿼리가 주어짐(1<=200,000)
		arr = new int[N];
		brr = new int[N];
		ans = new long[Q + 1];
		tree = new Node[N * 4];
		query = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = brr[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1, blockSize = (int)Math.sqrt(N); i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			query.add(new Query(s, e, i, s / blockSize));
		}
		// mo's알고리즘을 위한 정렬
		Collections.sort(query);
		// 값 압축
		Arrays.sort(brr);
		for(int i=0; i<N; i++)
			arr[i] = binarySearch(arr[i]);
		// 세그먼트 트리 노드 초기화
		for(int i=0; i<tree.length; i++)
			tree[i] = new Node();
		
		int s = 0;// 인덱스가 0부터 시작이라 s는 이미 0이 들어가있다 가정
		int e = -1;// 인덱스가 0부터 시작이라 e는 -1부터 시작
		for(Query q : query)
		{
			// 현재 arr[i]에 들어있는 값은 값 압축으로 인해 brr의 인덱스가 들어가있음
			while(e < q.e) update(1, 0, N - 1, arr[++e], true);
			while(q.s < s) update(1, 0, N - 1, arr[--s], true);
			while(q.e < e) update(1, 0, N - 1, arr[e--], false);
			while(s < q.s) update(1, 0, N - 1, arr[s++], false);
			
			// 1에는 항상 현재 있는 모든 값들의 짝수 인덱스의 합이 들어가있음
			ans[q.idx] = tree[1].even_sum % MOD;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static int binarySearch(int target) {
		int s = 0;
		int e = N - 1;
		int res = 0;
		
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(brr[mid] >= target)
			{
				e = mid - 1;
				res = mid;
			}
			else
				s = mid + 1;
		}
		
		return res;
	}
	static void update(int treeNode, int s, int e, int targetIdx, boolean isPlus) {
		
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			if(isPlus)
			{
				// 값을 더하는 것이면 초기값 세팅
				tree[treeNode].cnt = 1;
				tree[treeNode].total = brr[targetIdx];
				tree[treeNode].even_sum = brr[targetIdx];
				return;
			}
			// 값을 빼는 것이면, 모든 값을 0으로 초기화
			tree[treeNode].cnt = 0;
			tree[treeNode].total = 0;
			tree[treeNode].even_sum = 0;
			return;
		}
		
		int mid = (s + e) >> 1;
		int Lnode = treeNode << 1;
		int Rnode = treeNode << 1 | 1;
		
		update(Lnode, s, mid, targetIdx, isPlus);
		update(Rnode, mid + 1, e, targetIdx, isPlus);
		
		tree[treeNode].cnt = tree[Lnode].cnt + tree[Rnode].cnt;
		tree[treeNode].total = tree[Lnode].total + tree[Rnode].total;
		// 왼쪽 자식노드 원소의 개수가 짝수면, 오른쪽 자식노드의 even_sum을 그대로 더한다.
		// 왼쪽이 짝수라 왼쪽 자식노드에 오른쪽 자식노드를 그대로 붙여도 상관없이 오른쪽이 짝수부터 시작임
		if(tree[Lnode].cnt % 2 == 0)
			tree[treeNode].even_sum = tree[Lnode].even_sum + tree[Rnode].even_sum;
		// 왼쪽 자식노드 원소 수가 홀수면, 오른쪽 자식노드를 붙이면 한칸 뒤가 짝수기 때문에 오른쪽은 total - even_sum으로 하여 
		// 홀수 값을 더해준다.
		else
			tree[treeNode].even_sum = 
				tree[Lnode].even_sum + tree[Rnode].total - tree[Rnode].even_sum;
	}
	static class Query implements Comparable<Query>{
		int s, e, idx, fac;
		Query(int s, int e, int idx, int fac){
			this.s = s;
			this.e = e;
			this.idx = idx;
			this.fac = fac;
		}
		@Override
		public int compareTo(Query o) {
			// 구간이 다르면 구간기준 오름차순 정렬
			if(fac != o.fac)
				return fac - o.fac;
			// 구간의 값이 짝수면 e기준 오름차순 정렬
			if((fac & 1) == 0)
				return e - o.e;
			// 구간의 값이 홀수면 e기준 내림차순 정렬
			return o.e - e;
		}
	}
	static class Node{
		long cnt;// 현재까지 포함된 원소의 개수
		long total;// 현재까지 포함된 원소의 값을 모두 더한 것
		long even_sum;// 현재까지 포함된 원소의 짝수 인덱스 값을 모두 더한것, (0부터 시작)
	}
}
