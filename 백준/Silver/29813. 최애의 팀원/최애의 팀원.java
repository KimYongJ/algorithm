//https://www.acmicpc.net/problem/29813
//1초 1024MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Node> q =new ArrayDeque<>();
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			q.addLast(new Node(st.nextToken(), Integer.parseInt(st.nextToken())));
		}
		while(q.size() > 1)
		{
			Node now = q.pollFirst();
			
			for(int i=1; i<now.pass; i++)
				q.addLast(q.pollFirst());
			
			q.pollFirst();
		}
		System.out.print(q.poll().name);
	}
	static class Node{
		String name;
		int pass;
		Node(String n, int p){
			this.name = n;
			this.pass = p;
		}
	}
}