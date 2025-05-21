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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static final int INF = 1<<30;
	static int sqrt;
	static int N, Q;
	static int ans[];// 최종 결과를 담을 배열
	static int arr[];// 입력된 더미의 초기값을 받을 배열
	static int cnt[];// 차이가 나올 때마다 마킹할 카운팅 배열(차이는 최대 N만큼 가능)
	static int fac[];// 차이의 구간을 마킹할 제곱근 배열
	static Min_Seg minSeg;
	static Max_Seg maxSeg;
	static List<Query> query;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 자연수 개수(1<=30,000)
		Q = Integer.parseInt(st.nextToken());// 쿼리 수(1<=30,000)
		sqrt = (int)Math.sqrt(N);
		arr = new int[N + 1];
		cnt = new int[N + 1];
		ans = new int[Q + 1];
		fac = new int[N / sqrt + 1];
		minSeg = new Min_Seg(N, INF);
		maxSeg = new Max_Seg(N);
		query = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// N이하 자연수가 고유하게 주어짐
		
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

			ans[q.idx] = find();
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static void plus(int num) {
		maxSeg.update(1, 1, N, num, num);
		minSeg.update(1, 1, N, num, num);
		// num보다 작은 것중 가장 큰것
		int min = maxSeg.query(1, 1, N, 1, num - 1);
		// num보다 큰 것중 가장 작은 것
		int max = minSeg.query(1, 1, N, num + 1, N);
		// 둘이 유효한 값이면 해당 값을 배열에서 지움, 이 유는, 둘 사이에 num이 들어갈 것이기 때문
		if(max != INF && min != 0)
			setDiff(max - min, -1);
		// max가 유효하면 num과 차이 삽입
		if(max != INF)
			setDiff(max - num, 1);
		// min이 유효하면 num과 차이 삽입 
		if(min != 0)
			setDiff(num - min, 1);
	}
	static void minus(int num) {
		maxSeg.update(1, 1, N, num, 0);
		minSeg.update(1, 1, N, num, INF);
		// num보다 작은 것중 가장 큰 것
		int min = maxSeg.query(1, 1, N, 1, num - 1);
		int max = minSeg.query(1, 1, N, num + 1, N);
		// 값이 유효하면 둘 사이 차이 복원
		if(max != INF && min != 0)
			setDiff(max - min, 1);
		// num과의 차이 삭제
		if(max != INF)
			setDiff(max - num, -1);
		// num과의 차이 삭제
		if(min != 0)
			setDiff(num - min, -1);
	}
	static void setDiff(int diff, int plus) {
		if(diff <= 0)// 차이가 0 이하인 것은 스킵
			return;
		// 차이와, 해당 구간에 값을 연산
		cnt[diff] += plus;
		fac[diff / sqrt] += plus;
	}
	static int find() {
		for(int i=0; i<=N / sqrt; i++)
		{
			if(fac[i] == 0)
				continue;
			
			int s = i * sqrt;
			int e = Math.min(s + sqrt - 1, N);
			while(s <= e)
			{
				if(cnt[s] != 0)
					return s;
				++s;
			}
		}
		return 0;
	}
}
class Min_Seg{
	final int INF;
	int N;
	int tree[];
	Min_Seg(int N, int INF)
	{
		this.N = N;
		this.INF = INF;
		this.tree = new int[N * 4];
		Arrays.fill(tree, INF);
	}
	public void update(int treeNode, int s, int e, int targetIdx, int value) {
		if(e < targetIdx || targetIdx < s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public int query(int treeNode, int s, int e, int left, int right)
	{
		if(e < left || right < s)
			return INF;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int L = query(treeNode << 1, s, mid, left, right);
		int R = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return Math.min(L, R);
	}
}
class Max_Seg{
	int N;
	int tree[];
	Max_Seg(int N)
	{
		this.N = N;
		this.tree = new int[N * 4];
	}
	public void update(int treeNode, int s, int e, int targetIdx, int value)
	{
		if(e < targetIdx || targetIdx < s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public int query(int treeNode, int s, int e, int left, int right)
	{
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int L = query(treeNode << 1, s, mid, left, right);
		int R = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return Math.max(L, R);
	}
}
class Query implements Comparable<Query>{
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