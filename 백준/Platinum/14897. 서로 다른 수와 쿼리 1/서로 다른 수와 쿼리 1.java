//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/14897
//5초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Main{
	static class Query implements Comparable<Query>{
		int left, right, idx;
		Query(int l, int r, int i){
			left = l;
			right= r;
			idx = i;
		}
		@Override
		public int compareTo(Query o) {
			int l = left / sqrt;
			int r = o.left / sqrt;
			return l == r ? right - o.right : l - r;
		}
	}
	
	static int sqrt;
	static int idx;
	static int N, Q;
	static int arr[];
	static ArrayList<Query> query;
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args)throws Exception{
		inputAndComp();
		getQueryAndSort();
		solve();
	}
	
	static void inputAndComp()throws Exception{
		TreeSet<Integer> tset = new TreeSet<>();
		N		= Integer.parseInt(br.readLine());// 1<=1,000,000
		sqrt	= (int)Math.sqrt(N);
		arr		= new int[N + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			tset.add(arr[i]);
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int t : tset)
			map.put(t, ++idx);
		
		for(int i=1; i<=N; i++)
			arr[i] = map.get(arr[i]);
	}
	static void getQueryAndSort()throws Exception {
		query = new ArrayList<>();
		
		Q = Integer.parseInt(br.readLine());// 1<=1,000,000
		for(int i=1; i<=Q; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i));
		}
		
		Collections.sort(query);
	}
	static void solve() {
		int ans[] = new int[Q + 1];
		int count[] = new int[idx + 1];
		int idxL = 1;
		int idxR = 0;
		int diff = 0;
		for(Query q : query)
		{
			while(idxR < q.right) {
				++idxR;
				if(count[arr[idxR]]++ == 0)
					diff++;
			}
			while(q.right < idxR) {
				if(--count[arr[idxR]] == 0)
					--diff;
				--idxR;
			}
			while(q.left < idxL) {
				--idxL;
				if(count[arr[idxL]]++ == 0)
					diff++;
			}
			while(idxL < q.left) {
				if(--count[arr[idxL]] == 0)
					--diff;
				++idxL;
			}
			ans[q.idx] = diff; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}

}