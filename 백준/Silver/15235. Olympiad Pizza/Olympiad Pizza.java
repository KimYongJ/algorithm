//https://www.acmicpc.net/problem/15235
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayDeque<Node> q = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		int res[] = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			q.add(new Node(i, Integer.parseInt(st.nextToken())));
		
		int time = 1;
		
		while(!q.isEmpty())
		{
			Node now = q.pollFirst();
			
			if(--now.cnt == 0)
				res[now.idx] = time;
			else
				q.addLast(now);
			
			++time;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int r : res) sb.append(r).append(' ');
		System.out.print(sb);
	}
	static class Node{
		int idx, cnt;
		Node(int i, int c){
			idx = i; cnt = c;
		}
	}
}