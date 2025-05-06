//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/6515
//1초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		final int OFF_SET = 100_001;
		
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());// 원소 수 N ( 1<=N,Q<=100,000)
			
			if(N == 0)
				break;
			
			int Q = Integer.parseInt(st.nextToken());// 질의 수 Q ( 1<=N,Q<=100,000)
			int cnt[] = new int[OFF_SET << 1];
			int freq[] = new int[N + 1];
			int arr[] = new int[N + 1];
			int ans[] = new int[Q + 1];
			int sqrt = (int)Math.sqrt(N);
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++)
				arr[i] = Integer.parseInt(st.nextToken()) + OFF_SET;
			
			ArrayList<Query> query = new ArrayList<>();
			
			for(int i=1; i<=Q; i++)
			{
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				query.add(new Query(l, r, i, l / sqrt));
			}
			
			Collections.sort(query);
			
			int L = 1;
			int R = 0;
			int max = 0;
			for(Query q : query)
			{
				while(R < q.right)
				{
					++R;
					
					--freq[cnt[arr[R]]];
					
					++freq[++cnt[arr[R]]];
					
					if(max < cnt[arr[R]])
						++max;
				}
				while(q.right < R)
				{
					if(--freq[cnt[arr[R]]] == 0 && max == cnt[arr[R]])
						--max;
	
					++freq[--cnt[arr[R]]];
					
					--R;
				}
				while(L < q.left)
				{
					if(--freq[cnt[arr[L]]] == 0 && max == cnt[arr[L]])
						--max;
					
					++freq[--cnt[arr[L]]];
					
					++L;
				}
				while(q.left < L)
				{
					--L;
					
					--freq[cnt[arr[L]]];
					++freq[++cnt[arr[L]]];
					
					if(max < cnt[arr[L]])
						++max;
				}
				
				ans[q.idx] = max; 
			}
			
			
			
			for(int i=1; i<=Q; i++)
				sb.append(ans[i]).append('\n');
		}
		System.out.print(sb);
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
//10 3						// 원소 수N, 질의 수 Q ( 1<=N,Q<=100,000)
//-1 -1 1 1 1 1 3 10 10 10	// 오름차순으로 주어짐, (-100,000<=100,000)
//2 3							// 구간 범위
//1 10
//5 10
//0							// 입력 마지막에는 0출력