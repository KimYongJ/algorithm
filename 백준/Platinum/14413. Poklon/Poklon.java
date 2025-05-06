//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/6515
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Main{
	
	static int idx;	// 값 압축시 사용하는 flag
	static int sqrt;
	static int twoCnt;
	static int N, Q;
	static int [] arr, ans, count;
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		init();		// 해당 함수에서 배열을 입력받고, 값 압축을 진행하고, 쿼리들도 입력받아 Mo's알고리즘으로 정렬
		solve();	// 해당 함수에서 정렬된 쿼리들을 투포인터를 이용해 결과를 저장
		print();	// 해당 함수에서 저장된 결과를 출력
	}
	
	static void init()throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());// 배열 수(1<=500,000)
		Q		= Integer.parseInt(st.nextToken());// 질의 수(1<=500,000)
		arr		= new int[N + 1];
		ans		= new int[Q + 1];
		sqrt	= (int)Math.sqrt(N);
		
		// 값 입력 및 좌표 압축 진행
		TreeSet<Integer> set = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int s : set)
			map.put(s, ++idx);
		
		for(int i=1; i<=N; i++)
			arr[i] = map.get(arr[i]);
		
		// 쿼리를 입력 받은 후 Mo's알고리즘으로 정렬
		query = new ArrayList<>();
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i, l / sqrt));
		}
		
		Collections.sort(query);
	}
	static void solve() {
		// 2개인 것의 개수를 구함
		count = new int[idx + 1];
		twoCnt = 0;
		int L = 1;
		int R = 0;
		for(Query q : query)
		{
			while(R < q.right)	plus(arr[++R]);
			while(q.right < R)	minus(arr[R--]);
			while(L < q.left)	minus(arr[L++]);
			while(q.left < L)	plus(arr[--L]);
			
			ans[q.idx] = twoCnt; 
		}
	}
	static void print() {
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
	
	static void minus(int num) {
		if(count[num] == 3)
			++twoCnt;
		else if(count[num] == 2)
			--twoCnt;
		
		--count[num];
	}
	static void plus(int num) {
		if(count[num] == 1)
			++twoCnt;
		else if(count[num] == 2)
			--twoCnt;
		
		++count[num];
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
			return fac == o.fac ? right - o.right : fac - o.fac;
		}
	}
}

//5 2			// 배열 수, 질의수(1<=500,000)
//1 1 1 1 1	// 원소 값 1<=1,000,000,000
//2 4
//2 3
//답
//0
//1