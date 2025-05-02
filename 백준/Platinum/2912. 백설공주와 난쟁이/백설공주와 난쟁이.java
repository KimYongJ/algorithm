//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2912
//1초 256MB

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

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());// 난쟁이 수(3<=300,000)
		int C		= Integer.parseInt(st.nextToken());// 모자 색상 수(1<=10,000)
		int arr[]	= new int[N + 1];
		int color[] = new int[C + 1];
		sqrt		= (int)Math.sqrt(N);
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayList<Query> query = new ArrayList<>();
		int Q = Integer.parseInt(br.readLine());
		int ans[] = new int[Q + 1];
		for(int i=1; i<=Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			query.add(new Query(l,r,i));
		}
		
		Collections.sort(query);
		
		int idxL = 1;
		int idxR = 0;
		
		for(Query q : query)
		{
			int limit = (q.right - q.left + 1) / 2 + 1;
			int maxColor = 0;
			
			while(q.right < idxR)
				--color[arr[idxR--]];
			
			while(idxL < q.left)
				--color[arr[idxL++]];
			
			while(idxR < q.right)
				if(++color[arr[++idxR]] >= limit)
					maxColor = arr[idxR];
			
			while(q.left < idxL)
				if(++color[arr[--idxL]] >= limit)
					maxColor = arr[idxL];

			if(maxColor == 0)
			{
				for(int c=1; c<=C; c++)
				{
					if(color[c] >= limit)
					{
						maxColor = c;
						break;
					}
				}
			}
			
			ans[q.idx] = maxColor;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=Q; i++)
		{
			if(ans[i] == 0)
				sb.append("no").append('\n');
			else
				sb.append("yes ").append(ans[i]).append('\n');
		}
		
		System.out.print(sb);
	}
}
//10 3				// 난쟁이 수(3<=300,000), 모자 색상 수(1<=10,000)
//1 2 1 2 1 2 3 2 3 3	// 모자의 색상이 줄을 서 있는 순서대로 주어짐
//8					// 질의 수 (1<=10,000)
//1 2					// 범위
//1 3
//1 4
//1 5
//2 5
//2 6
//6 9
//7 10
//// 답
//no		// 절반이 넘는 모자의 색상이 같지 않으면 no출력
//yes 1	// 절반이 넘는 모자의 색상이 같으면 해당 모자 수 출력
//no
//yes 1
//no
//yes 2
//no
//yes 3