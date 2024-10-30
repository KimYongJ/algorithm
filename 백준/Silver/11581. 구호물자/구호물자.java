//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/11581
// 시작 노드는 1번, 도착 노드는 N
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node; Node next;
	Node(int node, Node next){
		this.node=node; this.next=next;
	}
}
class Main{
	
	static Node[] adNode;
	static int N;
	static boolean flag, visit[], recStack[];
	
	public static void DFS(int now) {
		if(flag)
			return;
		
		recStack[now] = true;
		for(Node next=adNode[now]; next!=null; next=next.next) {
			if(!visit[next.node]) {
				visit[next.node] = true;
				DFS(next.node);
			}else if(recStack[next.node]){
				flag = true;
				break;
			}
		}
		recStack[now] = false;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());	// 노드 수(1<=100)
		adNode	= new Node[N+1];
		visit	= new boolean[N+1];
		recStack= new boolean[N+1];
		flag	= false;
		for(int i=1; i<N; i++)
		{
			int T = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			while(T-->0)
				adNode[i] = new Node(Integer.parseInt(st.nextToken()), adNode[i]);
		}
		
		visit[1] = true;
		recStack[1] = true;
		DFS(1);
		
		if(flag)
			System.out.print("CYCLE");
		else
			System.out.print("NO CYCLE");
	}
}