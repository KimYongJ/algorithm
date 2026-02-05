//https://www.acmicpc.net/problem/23350
//1초 512MB
//3 1 // 컨테이너 개수 N, 우선순위 종류 M(1≤M≤N≤100)
//1 2 // 우선순위, 무게
//1 1
//1 3
//무게합 출력 : 12

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long sum = 0;
		
		Stack stack = new Stack();
		Stack dummy = new Stack();
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b-a);
		ArrayDeque<Node> q = new ArrayDeque<>();
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int o = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(o);
			q.add(new Node(o, w));
		}
		
		while(!q.isEmpty())
		{
			if(q.peek().order != pq.peek())
			{
				sum += q.peek().weight;
				q.addLast(q.pollFirst());
				continue;
			}
			
			pq.poll();// 우선순위 하나 버림
			Node now = q.pollFirst();
			
			while(!stack.isEmpty() && stack.peek().order == now.order && stack.peek().weight < now.weight)
			{
				Node n = stack.pop();
				sum += n.weight;
				dummy.push(n);
			}
			
			sum += now.weight;
			stack.push(now);
			
			while(!dummy.isEmpty())
			{
				Node n = dummy.pop();
				sum += n.weight;
				stack.push(n);
			}
		}
		
		System.out.print(sum);
	}
	static class Node{
		int order, weight;
		Node(int o, int w){
			order = o;
			weight = w;
		}
	}
	static class Stack{
		int idx;
		Node arr[];
		Stack(){
			idx = -1;
			arr = new Node[1000];
		}
		boolean isEmpty() {return idx < 0;}
		Node peek() { return arr[idx];}
		Node pop() {return arr[idx--];}
		void push(Node n) { arr[++idx] = n;}
	}
}