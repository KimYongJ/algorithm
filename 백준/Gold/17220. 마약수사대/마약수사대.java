// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Main{
	
	static int		N;
	static int		cnt;
	static Node		adNode[];
	static boolean	visit[];
	static boolean	isRoot[]; // 루트인 것은 false 값을 갖는 배열
	
	public static void DFS(int node) {
		for(Node now=adNode[node]; now!=null; now=now.next) 
		{
			if(!visit[now.node]) 
			{
				cnt++;
				visit[now.node] = true;
				DFS(now.node);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		adNode	= new Node[N];
		visit	= new boolean[N];
		isRoot	= new boolean[N];
		int T	= Integer.parseInt(st.nextToken());
		int a,b;
		while(T-->0) 
		{
			st	= new StringTokenizer(br.readLine());
			a	= st.nextToken().charAt(0) - 'A';
			b	= st.nextToken().charAt(0) - 'A';
			adNode[a] = new Node(b, adNode[a]);
			isRoot[b] = true;// 루트가 아닌 것은 true, 루트인 것은 false
		}
		st	= new StringTokenizer(br.readLine());
		T	= Integer.parseInt(st.nextToken());
		while(T-->0) 
		{
			visit[ st.nextToken().charAt(0) - 'A'] = true;
		}
		for(int i=0; i<N; i++) 
		{
			if(!isRoot[i] && !visit[i]) 
			{
				visit[i] = true;
				DFS(i);
			}
		}
		System.out.print(cnt);
	}
}