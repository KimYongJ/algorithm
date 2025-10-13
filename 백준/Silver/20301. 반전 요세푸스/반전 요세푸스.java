//https://www.acmicpc.net/problem/20301
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> q = new ArrayDeque<>();
		
		for(int i=1; i<=N; i++) q.add(i);
		
		StringBuilder sb = new StringBuilder();
		
		int delCnt = 0;
		boolean clockwise = true;
		while(q.size() > 1)
		{
			for(int i=1; i<K; i++)
				if(clockwise)	q.addLast(q.pollFirst());
				else			q.addFirst(q.pollLast());
			
			sb.append(clockwise ? q.pollFirst() : q.pollLast()).append('\n');
			
			if(++delCnt == M)
			{
				clockwise = !clockwise;
				delCnt = 0;
			}
		}
		
		System.out.print(sb.append(q.poll()).toString());
	}
}