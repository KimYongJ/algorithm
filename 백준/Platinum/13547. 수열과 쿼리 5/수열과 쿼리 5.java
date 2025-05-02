//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13547
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	static class Query{
		int left, right, idx;
		Query(int l, int r, int i){
			left = l;
			right= r;
			idx = i;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	//수열의 크기 1<=100,000
		int arr[]	= new int[N + 1];
		int log		= (int) Math.log(N);
		int count[] = new int[1_000_001];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 배열 원소 값 1<=1,000,000
		
		ArrayList<Query> query = new ArrayList<>();
		
		int Q = Integer.parseInt(br.readLine());// 쿼리 개수 1<=100,000
		int ans[]	= new int[Q + 1];
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l, r, i));
		}
		
		// left / log 한 값을 기준으로 오름차순, 같으면 right가 작은 값을 기준으로 오름차순
		Collections.sort(query, (a,b)->{
			int l = a.left / log;
			int r = b.left / log;
			return l == r ? a.right - b.right : l - r;
		});
		
		int idxL = 1;	// 올라가기만 한다.
		int idxR = 0;	// 올라가기만 한다.
		int cnt = 0;
		
		for(Query q : query)
		{
			while(idxR < q.right)
			{
				idxR++;
				if(count[arr[idxR]]++ == 0)
						cnt++;
			}
			while(idxL < q.left)
			{
				if(count[arr[idxL]]-- == 1)
					cnt--;
				
				idxL++;
			}
			while(q.left < idxL)
			{
				idxL--;
				if(count[arr[idxL]]++ == 0)
					cnt++;
			}
			while(q.right < idxR)
			{
				if(count[arr[idxR]]-- == 1)
					cnt--;
				
				idxR--;
			}
			
			ans[q.idx] = cnt; 
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=Q; i++)
			sb.append(ans[i])
				.append('\n');
		
		System.out.print(sb);
	}
}
//5				// 수의 길이 1<=100,000
//1 1 2 1 3		// 배열 원소 값 1<=1,000,000
//3				// 쿼리 개수 1<=100,000
//1 5			// 배열의 위치 1<=N
//2 4
//3 5