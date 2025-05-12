//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13545
//2.5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static final int ADD_LAST = 1;
	static final int ADD_FIRST = 2;
	static final int REMOVE_FIRST = 3;
	static final int REMOVE_LAST = 4;

	static int N, Q;
	static int OFF_SET;
	static int sqrt;
	static int ans[];
	static int arr[];
	static int fac[];// 가장 긴 쿼리의 구간을 담을 배열 N / sqrt(N)의 구간이 있다.
	static int cnt[];// 길이 차이마다 몇개가 있는지 카인팅할 배열
	static ArrayList<Query> query;
	static ArrayDeque<Integer>[] list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());// 수열의 크기(1<=100,000)
		OFF_SET = N + N;
		sqrt = (int)Math.sqrt(N);
		arr = new int[N + 1];
		fac = new int[N / sqrt + 1];
		cnt = new int[N + 1];
		list = new ArrayDeque[OFF_SET + 1];
		
		for(int i=0; i<=OFF_SET; i++)
			list[i] = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[0] = N;
		for(int i=1; i<=N; i++)// 1또는 -1만 주어짐
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
		
		Q = Integer.parseInt(br.readLine());// 쿼리 수 1<=100,000
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
		
		int L = 1;
		int R = 0;
		
		for(Query q : query)
		{
			while(R < q.right) excute(++R, ADD_LAST);
			while(q.left < L) excute(--L, ADD_FIRST);
			while(L < q.left) excute(L++, REMOVE_FIRST);
			while(q.right < R) excute(R--, REMOVE_LAST);
			
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
			case ADD_LAST : list[arr[idx]].addLast(idx); break;
			case ADD_FIRST : list[arr[idx]].addFirst(idx); break;
			case REMOVE_FIRST : list[arr[idx]].removeFirst(); break;
			case REMOVE_LAST : list[arr[idx]].removeLast(); break;
		}
		
		cal(arr[idx], 1);
	}
	static void cal(int num, int plus) {
		if(list[num].size() > 0)
		{
			int diff = list[num].peekLast() - list[num].peekFirst();
			cnt[diff] += plus;
			fac[diff / sqrt] += plus; 
		}
	}
	static int find() {
		for(int i=N / sqrt; i>=0; i--)
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
				return fac-o.fac;
			
			if((fac & 1) == 0)
				return right - o.right;
			
			return o.right - right;
		}
	}
}
//6				// 수열의 크기(1<=100,000)
//1 1 1 -1 -1 -1	// 1또는 -1만 주어짐
//4				// 쿼리 수 1<=100,000
//1 3				// 구간
//1 4
//1 5
//1 6
////답
//0
//2
//4
//6