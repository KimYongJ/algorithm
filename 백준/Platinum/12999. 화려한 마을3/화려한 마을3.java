//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/12999
//5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int OFF_SET = 100_001;
		int N		= Integer.parseInt(st.nextToken());// 배열원소개수(1<=100,000)
		int Q		= Integer.parseInt(st.nextToken());// 질의 수(1<=100,000)
		int sqrt	= (int) Math.sqrt(N);
		int arr[]	= new int[N + 1];
		int cnt[]	= new int[OFF_SET << 1];// 해당 숫자의 카운팅
		int freq[]	= new int[N + 1];		// 카운팅 숫자 자체가 몇개인지 카운팅
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] += Integer.parseInt(st.nextToken()) + OFF_SET;
		
		ArrayList<Query> query = new ArrayList<>();
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i, l / sqrt));
		}
		
		Collections.sort(query);
		
		int ans[] = new int[Q + 1];
		int max = 0;
		int L = 1;
		int R = 0;
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
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
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
			if(fac != o.fac)
				return fac - o.fac;
			
			return right - o.right;
		}
	}
}
//10 3						// 배열원소개수(1<=100,000), 질의 수(1<=100,000)
//-1 1 1 10 10 10 1 -1 1 3	// 페인트의 밝기(-100,000<=100,000)
//2 3							// 구간
//1 10
//5 10