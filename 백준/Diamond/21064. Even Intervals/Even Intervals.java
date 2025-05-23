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
	static int arr[];// 값 압축을 담을 배열
	static int brr[];// 값 압축을 원본으로 되돌릴 배열
	static long ans[];// 최종 결과를 담을 배열
	static Node tree[];// 세그먼트 트리
	static List<Query> query;// 쿼리 질의를 담을 배열
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
		// 초기 원소값 입력 받기
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = brr[i] = Integer.parseInt(st.nextToken());
		// 구간 쿼리 입력 받기
		for(int i=1, blockSize = (int)Math.sqrt(N); i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			query.add(new Query(s, e, i, s / blockSize));
		}
		// mo's 알고리즘을 위해 정렬
		Collections.sort(query);
		// 좌표 압축 진행
		Arrays.sort(brr);
		for(int i=0; i<N; i++)
			arr[i] = binarySearch(arr[i]);
		// 트리노드 초기화
		for(int i=N*4-1; i>=0; i--)
			tree[i] = new Node();
		
		int e = -1;
		int s = 0;
		for(Query q : query)
		{
			while(e < q.e) update(1, 0, N-1, arr[++e], true);
			while(q.s < s) update(1, 0, N-1, arr[--s], true);
			while(q.e < e) update(1, 0, N-1, arr[e--], false);
			while(s < q.s) update(1, 0, N-1, arr[s++], false); 
			
			ans[q.idx] = tree[1].even_sum % MOD;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void update(int treeNode, int s, int e, int targetIdx, boolean isPlus) {
		if(targetIdx < s || e < targetIdx)
			return;
		
		if(s == e)
		{
			if(isPlus)
			{
				tree[treeNode].cnt += 1;
				tree[treeNode].sum += brr[s];
				tree[treeNode].even_sum += brr[s];
			}
			else
			{
				tree[treeNode].cnt -= 1;
				tree[treeNode].sum -= brr[s];
				tree[treeNode].even_sum -= brr[s];
			}
			return;
		}
		
		int mid = (s + e) >> 1;
		int leftNode = treeNode << 1;
		int rightNode = treeNode << 1 | 1;
		update(leftNode, s, mid, targetIdx, isPlus);
		update(rightNode, mid + 1, e, targetIdx, isPlus);
		
		tree[treeNode].cnt = tree[leftNode].cnt + tree[rightNode].cnt;
		tree[treeNode].sum = tree[leftNode].sum + tree[rightNode].sum;
		
		if(tree[leftNode].cnt % 2 == 0)// 왼쪽 자식노드의 원소 수가 짝수일 때,
		{
			// 왼쪽 자식노드가 짝수면, 오른쪽 자식노드가 왼쪽 자식노드에 붙었을 때 짝수 순번이라서 even_sum을 그대로 더하면됨
			tree[treeNode].even_sum = tree[leftNode].even_sum + tree[rightNode].even_sum;
			return;
		}
		// 왼쪽 자식 노드 원소 수가 홀수면, 오른쪽 자식 노드가 붙었을 때, 홀수 이므로 sum에서 짝수를 빼줘서 홀수 위치가 더해지도록 해야 함
		tree[treeNode].even_sum = 
				tree[leftNode].even_sum + tree[rightNode].sum - tree[rightNode].even_sum;
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
			
			if((fac & 1) == 0)
				return e - o.e;
			
			return o.e - e;
		}
	}
	static class Node{
		long cnt;// 이 구간까지 포함된 모든 노드의 수
		long sum;// 이 구간까지 포함된 모든 자식 노드들의 합 
		long even_sum;// 이 구간까지 포함된 모든 짝수 노드들의 합
		Node(){
			cnt = 0;
			sum = 0;
			even_sum = 0;
		}
	}
}