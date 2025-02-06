//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1976
//2초 / 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node=node;
		this.next=next;
	}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		input();
		bfs();
		print();
	}
	public static void input() throws Exception{
		N		= Integer.parseInt(br.readLine());	//도시 수1<=200
		M		= Integer.parseInt(br.readLine());	//여행계획에 속한 도시 수 1<=천
		dir		= new int[M];
		visit	= new boolean[N+1];
		adNode	= new Node[N + 1];
		
		for(int y=1; y<=N; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=N; x++)
			{
				int flag = Integer.parseInt(st.nextToken());
				if(flag == 1)
				{
					adNode[y] = new Node(x, adNode[y]);
					adNode[x] = new Node(y, adNode[x]);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++)
			dir[i] = Integer.parseInt(st.nextToken());
	}
	public static void print() {
		for(int d: dir)
			if(!visit[d])
			{
				System.out.print("NO");
				return;
			}
		System.out.print("YES");
	}
	public static void bfs() {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(dir[0]);
		visit[dir[0]] = true;
		while(!q.isEmpty())
		{
			int now = q.poll();
			for(Node next=adNode[now]; next != null; next=next.next)
			{
				if(!visit[next.node])
				{
					visit[next.node] = true;
					q.add(next.node);
				}
			}
		}
	}
	
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, dir[];
	static boolean visit[];
	static Node adNode[];
	
}