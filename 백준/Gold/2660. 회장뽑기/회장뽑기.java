//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2660

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Result{
	int idx, score;
	Result(int idx, int score){this.idx=idx; this.score=score;}
}
class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[] adNode = new Node[N+10];
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == -1 && b== -1)
				break;
			
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		ArrayList<Result> result = new ArrayList<>();

		for(int i=1; i<=N; i++)
		{
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean visit[] = new boolean[N+1];
			visit[i] = true;
			q.add(i);
			
			int score = 0;
			while(!q.isEmpty())
			{
				score++;
				int size = q.size();
				while(size-- > 0)
				{
					int idx = q.poll();
					for(Node now=adNode[idx]; now!=null; now=now.next)
						if(!visit[now.node])
						{
							visit[now.node] = true;
							q.add(now.node);
						}
				}
			}
			if(1 < score)
				result.add(new Result(i, score - 1));
		}
		
		Collections.sort(result, (a,b) -> a.score!=b.score ? a.score-b.score : a.idx - b.idx);
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		int cnt = 0;
		int min = result.get(0).score;
		for(int i=0; i<result.size(); i++)
		{
			Result now = result.get(i);
			if(min < now.score)
				break;
			cnt++;
			sb2.append(now.idx).append(' ');
		}
		sb1.append(min).append(' ').append(cnt).append('\n').append(sb2.toString());
		
		System.out.print(sb1.toString());
	}
}
