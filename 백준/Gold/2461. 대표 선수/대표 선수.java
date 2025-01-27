//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2461
//2초 / 256MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int idx, value;
	Node(int i, int v){
		idx = i;
		value = v;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 학급수1<=1000
		int M = Integer.parseInt(st.nextToken());	// 학생수1<=1000
		int min = Integer.MAX_VALUE;
		int max = 0;
		PriorityQueue<Integer>[] pq = new PriorityQueue[N];
		PriorityQueue<Node> comp = new PriorityQueue<>((a,b)->a.value-b.value);
		for(int i=0; i<N; i++)
		{
			pq[i] = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				pq[i].add(Integer.parseInt(st.nextToken()));
			
			int value = pq[i].poll();
			comp.add(new Node(i, value));
			min = Math.min(min, value);
			max = Math.max(max, value);
		}
		
		int diff = max - min;
		
		while(true)
		{
			Node now = comp.poll();
			if(pq[now.idx].isEmpty())
				break;
			
			int value = pq[now.idx].poll();
			
			comp.add(new Node(now.idx, value));
			
			min = comp.peek().value;
			max = Math.max(max, value);
			
			diff = Math.min(diff, max - min);
		}
		
		System.out.print(diff);
	}
}