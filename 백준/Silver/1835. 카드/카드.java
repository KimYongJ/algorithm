//https://www.acmicpc.net/problem/1835
//2초 128MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());

		q.add(N);
		
		while(--N > 0)
		{
			q.addFirst(N);
			
			for(int i=0; i<N; i++)
				q.addFirst(q.pollLast());
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty())
			sb.append(q.pollFirst()).append(' ');
		
		System.out.print(sb);
	}
}