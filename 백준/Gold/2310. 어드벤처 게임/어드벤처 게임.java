//https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{
	
	static int N;
	static int arr[];	// 각 방의 에너지 추가, 차감 정도를 나타냄
	static boolean visit[];
	static Node adNode[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer st;
		
		while((N = Integer.parseInt(br.readLine())) != 0) 
		{
			arr		= new int[N+1];
			adNode	= new Node[N+1];
			visit	= new boolean[N+1];
			
			for(int i=1; i<=N; i++) 
			{
				st = new StringTokenizer(br.readLine());
				
				char c = st.nextToken().charAt(0);
				int cost = Integer.parseInt(st.nextToken());
				arr[i] = c=='T' ? -cost : cost;

				int next;
				while((next = Integer.parseInt(st.nextToken()))!= 0) 
				{
					adNode[i] = new Node(next, adNode[i]);
				}
			}
			visit[1] = true;
			sb.append(DFS(1, 0) ? "Yes" : "No")
				.append('\n');
		}
		System.out.print(sb);
	}
	public static boolean DFS(int nowNode, int cost) {
		if(arr[nowNode] < 0)
		{
			cost += arr[nowNode];
        }
		else if(arr[nowNode] > cost)
		{
			cost = arr[nowNode];
        }
		
		if(cost < 0)
		{		
			return false;
        }
		if(nowNode == N)
		{	
			return true;
        }
		for(Node now=adNode[nowNode]; now!=null; now=now.next) 
		{
			if(!visit[now.node])
			{
				visit[now.node] = true;
				if(DFS(now.node, cost)){
					return true;
                }
				visit[now.node] = false;
			}
		}
		
		return false;
	}
}