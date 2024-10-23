//https://github.com/KimYongJ
//https://www.acmicpc.net/problem/14907
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int n, Node nxt){node=n; next=nxt;}
}
class Main{
	
	static Node[] adNode;
	static int result;
	static int[] time, indg, maxTime;
	static boolean visit[];
	
	public static void DFS(int node, int t)
	{
		result = Math.max(result, t);
		
		for(Node next=adNode[node]; next!=null; next=next.next)
			if(!visit[next.node])
			{
				maxTime[next.node] = Math.max(maxTime[next.node],t);
				if(0<indg[next.node])
					--indg[next.node];
				if(indg[next.node] == 0)
				{
					visit[next.node]= true; 
					DFS(next.node, maxTime[next.node] + time[next.node]);
				}
			}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		adNode	= new Node[26];
		time	= new int[26];		// 현재 노드에 작업시간
		indg	= new int[26];		// 진입차수를 담을 배열
		maxTime	= new int[26];		// 각 노드까지 도착할 때 까지 최대 시간
		visit	= new boolean[26];	// 진입차수가 0이 된것을 마킹하여 이미 0이된 것은 방문하지 않도록 한다.
		while(true)
		{
			String str = br.readLine();
			if(str == null || str.length() == 0)
				break;
			StringTokenizer st = new StringTokenizer(str);
			int node1	= st.nextToken().charAt(0) - 'A';
			time[node1]	= Integer.parseInt(st.nextToken());
			
			if(st.hasMoreTokens())
			{
				for(char c : st.nextToken().toCharArray())
				{
					int node2 = c-'A';
					// node2에서 node1으로 갈 수 있게 만든다.
					adNode[node2] = new Node(node1, adNode[node2]);
					indg[node1]++;
				}
			}
			
		}
		
		for(int i=0; i<26; i++)
			if(indg[i] == 0 && !visit[i])		// 진입차수가 0인 것들을 반복
			{
				visit[i] = true;
				DFS(i, time[i]);	// 해당 노드로 시작해서 걸리는 시간 전달
			}
		
		System.out.print(result);
	}
}