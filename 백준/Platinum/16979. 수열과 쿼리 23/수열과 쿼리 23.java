//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16979
//5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	
	static int len;		// 값 압축을 위한 플레그
	static int sqrt;	// Mo's 알고리즘을 위한 블록
	static int N, Q;
	static int [] arr;
	static int [] fenwickTree;
	static long[] ans;
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		inputAndComp();// 해당 함수에서 값의 입력과 좌표압축, 쿼리를 Mo's 알고리즘으로 정렬
		solve();	// 해당 함수에서 펜윅트리 + 투포인터 알고리즘을 이용해 각 쿼리당 연산 진행
		print();	// 해당 함수에서 결과 출력
	}
	
	static void inputAndComp()throws Exception {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 배열 수(1<=100,000)
		Q		= Integer.parseInt(st.nextToken());// 쿼리 수(1<=100,000)
		arr		= new int[N + 1];
		ans		= new long[Q + 1];
		query	= new ArrayList<>();
		sqrt	= (int)Math.sqrt(N);
		
		TreeSet<Integer> set = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int s: set)
			map.put(s, ++len);
		
		for(int i=1; i<=N; i++)
			arr[i] = map.get(arr[i]);
		
		for(int i=1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i, l / sqrt));
		}
		
		Collections.sort(query);
	}
	static void solve() {
		fenwickTree = new int[len + 1];
		int L = 1;
		int R = 0;
		long cnt = 0;
		for(Query q : query)
		{
			while(R < q.right) {
				++R;
				update(arr[R], 1);
				// 현재 추가된 수 보다 큰 수가 몇개 있는지 카운팅
				cnt += query(arr[R] + 1, len);
			}
			
			while(q.right < R) {
				update(arr[R], -1);
				// 현재 제거된 수 보다 큰 수가 몇개 있는지 카운팅 해서 뺀다
				cnt -= query(arr[R] + 1, len);
				--R;
			}
			
			while(L < q.left) {
				update(arr[L], -1);
				// 현재 제거된 수 보다 작은 수가 몇개 있는지 카운팅 해서 뺀다
				cnt -= query(1, arr[L] - 1);
				++L;
			}
			
			while(q.left < L) {
				--L;
				update(arr[L], 1);
				// 현재 추가된 수 보다 작은 수가 몇개 있는지 카운팅
				cnt += query(1, arr[L] - 1);
			}
			
			ans[q.idx] = cnt; 
		}
	}
	static void print() {
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	static int query(int l, int r) {
		return sum(r) - sum(l - 1);
	}
	static int sum(int idx) {
		int sum = 0;
		
		for(int i=idx; i>0; i-= i & -i)
			sum += fenwickTree[i];
		
		return sum;
	}
	static void update(int idx, int value) {
		
		for(int i=idx; i<=len; i += i & -i)
			fenwickTree[i] += value;
		
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

//5 3				// 배열 수, 쿼리 수 (1<=100,000)
//1 4 2 3 1		// 입력되는 숫자 (1<=1,000,000,000)
//1 2				// 쿼리 범위
//3 5
//1 5
//답
//0
//2
//5