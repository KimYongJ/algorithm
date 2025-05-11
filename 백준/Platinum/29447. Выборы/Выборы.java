//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/29447
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main{
	
	static int idx;
	static int max;
	static int N, Q;
	static int[] cnt, freq;
	static int[] arr, ans;
	static ArrayList<Query> query;
	
	public static void main(String[] args)throws Exception{
		inputAndComp();	// 배열과 쿼리를 입력받고 좌표압축, Mo's알고리즘 정렬까지 진행한다.
		solve();		// 투포인 터로 문제를 푼다.
		print();		// 답을 출력한다.
	}
	static void inputAndComp()throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> set = new TreeSet<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		
		N = Integer.parseInt(br.readLine());// 투표용지수 (1<=100,000)
		arr = new int[N + 1];
		// 값을 입력받고 좌표 압축을 진행한다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			// i번째 투표용지를 넣은사람이 투표한 후보 번호(1<=100,000,000)
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		
		for(int s : set)
			map.put(s,++idx);
		
		for(int i=1; i<=N; i++)
			arr[i] = map.get(arr[i]);
		
		// 질의 수 (1<=100,000)
		Q		= Integer.parseInt(br.readLine());
		ans		= new int[Q + 1];
		query	= new ArrayList<>();
		
		for(int i=1, sqrt = (int)Math.sqrt(N); i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i, l / sqrt));
		}
		//Mo's 알고리즘을 활용해 sqrt(N) 구간으로 나눠 정렬
		Collections.sort(query);
	}
	static void solve() {
		cnt = new int[idx + 1];
		freq = new int[N + 1];
		
		int L = 1;
		int R = 0;
		for(Query q : query)
		{
			while(q.left < L)	plus(arr[--L]);
			while(R < q.right)	plus(arr[++R]);
			while(q.right < R)	minus(arr[R--]);
			while(L < q.left)	minus(arr[L++]);
			

			ans[q.idx] = max;
		}
	}
	static void minus(int num) {
		// 현재 num카운팅의 빈도가 0이되었고, max가 현재 카운팅 수와 같다면 max변수 1감소
		if(--freq[cnt[num]] == 0 && max == cnt[num])
			--max;
		
		++freq[--cnt[num]];	// num 카운팅 감소 후, 해당 값 빈도 증가
	}
	static void plus(int num) {
		--freq[cnt[num]];	// 이전 빈도 감소
		++freq[++cnt[num]];	// 현재 num 증가와, 현재 값 빈도 증가 
		max = Math.max(max, cnt[num]);
	}
	
	static void print() {
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		System.out.print(sb);
	}
	
	static class Query implements Comparable<Query>{
		int left, right, idx, fac;
		Query(int l, int r, int i, int f){
			left = l;
			right= r;
			idx = i;
			fac = f;
		}
		@Override
		public int compareTo(Query o) {
			if(fac != o.fac)
				return fac - o.fac;
			
			if(fac % 2 == 0)
				return right - o.right;
			
			return o.right - right;
		}
	}
}
//6				// 투표용지수 (1<=100,000)
//1 1 2 2 1 1		// i번째 투표용지를 넣은사람이 투표한 후보 번호(1<=100,000,000)
//3				// 질의 수 (1<=100,000)
//1 6				// 질의 조건 l<=r
//2 4
//2 5
////답 특정 구간의 가장 많은 수를 카운팅
//4
//2
//2