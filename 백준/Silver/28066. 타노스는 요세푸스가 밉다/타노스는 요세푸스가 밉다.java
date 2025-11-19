//https://www.acmicpc.net/problem/28066
//0.1초/ 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayDeque<Integer> q = new ArrayDeque<>();
		int N = Integer.parseInt(st.nextToken());//(2 ≤ N, K ≤ 10^6) 
		int K = Integer.parseInt(st.nextToken()) - 1;//(2 ≤ N, K ≤ 10^6) 
		
		for(int i=1; i<=N; i++) q.add(i);
		
		while(q.size() != 1)
		{
			int base = q.poll();
			int cnt = K;
			
			while(!q.isEmpty() && cnt-->0)
				q.poll();
			
			q.add(base);
		}
		System.out.print(q.poll());
	}
}