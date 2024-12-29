//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/29767

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Long> pq = new PriorityQueue<>((a,b)->Long.compare(b, a));
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 교실개수(1<=삼십만)
		int K = Integer.parseInt(st.nextToken());	// 학생수
		long sum[] = new long[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			pq.add(sum[i] += sum[i-1] + Integer.parseInt(st.nextToken()));
		
		long res = 0;
		while(K-->0)
			res += pq.poll();

		System.out.print(res);
	}
}