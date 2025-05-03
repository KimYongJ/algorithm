//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/8462
//3초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int sqrt;
	
	static class Query implements Comparable<Query>{
		int left, right, idx;
		Query(int l, int r, int i){
			left = l;
			right = r;
			idx = i;
		}
		@Override
		public int compareTo(Query o) {
			int l = left / sqrt;
			int r = o.left / sqrt;
			return l == r ? right - o.right : l - r;
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 배열 크기(1<=100,000)
		int Q		= Integer.parseInt(st.nextToken());// 부분 배열 개수(1<=100,000)
		int arr[]	= new int[N + 1];
		long cnt[]	= new long[1_000_001];
		long ans[]	= new long[Q + 1];
		sqrt		= (int)Math.sqrt(N);
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 1<=1,000,000
		
		ArrayList<Query> query = new ArrayList<>();
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i));
		}
		
		Collections.sort(query);
		
		long now = 0;
		int idxL = 1;
		int idxR = 0;
		for(Query q : query)
		{			
			while(idxR < q.right)
			{
				++idxR;
				
				int num = arr[idxR];
				
				now += 2*cnt[num] * num + num;
				
				++cnt[num];
			}
			while(q.right < idxR)
			{
				int num = arr[idxR];
				
				now -= 2*cnt[num] * num - num;
				
				--cnt[num];
				
				--idxR;
			}
			while(idxL < q.left)
			{
				int num = arr[idxL];
				
				now -= 2*cnt[num] * num - num;
				
				--cnt[num];
				
				++idxL;
			}
			while(q.left < idxL)
			{
				--idxL;
				
				int num = arr[idxL];

				now += 2*cnt[num] * num + num;
				
				++cnt[num];
			}
			
			ans[q.idx] = now; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i]).append('\n');
		
		System.out.print(sb);
	}
}
//8 3					// 배열 크기(1<=100,000), 부분 배열 개수(1<=100,000)
//4 3 1 1 1 3 1 2		// 1<=1,000,000
//2 7					// 범위
//1 6
//3 8
//답 / 범위 안에있는 특정수 S가 몇개있는지를 세고, 그 숫자를 K라하면 K * K * S를 출력
//28
//25
//21