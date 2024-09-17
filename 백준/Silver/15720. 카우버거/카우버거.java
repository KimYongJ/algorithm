//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15720

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	static BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static int poll(PriorityQueue<Integer> pq) {
		int sum = 0;
		while(!pq.isEmpty()) sum += pq.poll();
		return sum;
	}
	public static int input(PriorityQueue<Integer> pq, int len)throws Exception{
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		for(int i=0; i<len; i++) {
			int n = Integer.parseInt(st.nextToken());
			sum += n;
			pq.add(n);
		}
		return sum;
	}
	public static void main(String[] args)throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());	// 버거 개수(1<=1000)
		int C = Integer.parseInt(st.nextToken());	// 사이드 개수(1<=1000)
		int D = Integer.parseInt(st.nextToken());	// 음료 개수(1<=1000)
		int sum1 = 0;
		int sum2 = 0;
		PriorityQueue<Integer> bq = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> cq = new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Integer> dq = new PriorityQueue<Integer>((a,b)->b-a);
		
		sum1 += input(bq, B);
		sum1 += input(cq, C);
		sum1 += input(dq, D);

		while(!bq.isEmpty() && !cq.isEmpty() && !dq.isEmpty())
		{
			int total = bq.poll() + cq.poll() + dq.poll();
			sum2 += total - (total / 10);
		}
		
		sum2 += poll(bq) + poll(cq) + poll(dq);
		
		System.out.print( new StringBuilder().append(sum1).append('\n').append(sum2) );
	}
}