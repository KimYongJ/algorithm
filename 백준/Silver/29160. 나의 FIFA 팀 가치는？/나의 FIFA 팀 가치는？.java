//https://www.acmicpc.net/problem/29160
//1초 1024MB
//11 1 // 선수 수(0≤N≤1000000), 목표 년수(1≤K≤50000)
//1 5 // 포지션 번호(1<=11), 선수의 가치(1≤100000)
//2 4
//3 2
//4 3
//5 10
//6 9
//7 6
//8 8
//9 11
//10 5
//11 6
//K년 12월에 선수 가치의 합 출력 : 58

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//선수 수(0≤N≤1,000,000)
		int K = Integer.parseInt(st.nextToken());//목표 년수(1≤K≤50,000)
		PriorityQueue<Integer>[] q = new PriorityQueue[11];
		
		for(int i=0; i<11; i++)
			q[i] = new PriorityQueue<>((a,b) -> b - a);
		
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			q[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()));
		}
		
		while(K-->0)
		{
			for(int i=0; i<11; i++)
			{
				if(q[i].isEmpty())
					continue;

				q[i].add(Math.max(0,q[i].poll() - 1));
			}
		}
		
		int sum = 0;
		
		for(int i=0; i<11; i++)
			if(!q[i].isEmpty())
				sum += q[i].peek();
		
		System.out.print(sum);
	}
}