//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27924
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}
class Person{
	boolean isCop; int node;
	Person(boolean i, int n){isCop=i;node=n;}
}
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N				= Integer.parseInt(br.readLine());
		Node[] adNode		= new Node[N+1];
		boolean[] isLeaf	= new boolean[N+1];
		boolean[] copVisit	= new boolean[N+1];
		boolean[] runVisit	= new boolean[N+1];
		
		StringTokenizer st;
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
			isLeaf[a] = !isLeaf[a];
			isLeaf[b] = !isLeaf[b];
		}
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Person> q = new ArrayDeque<>();
		q.add(new Person(false, a));
		q.add(new Person(true, b));
		q.add(new Person(true, c));
		runVisit[a] = true;
		copVisit[b] = copVisit[c] = true;
		while(!q.isEmpty())
		{
			Person now = q.poll();
			if(!now.isCop) {
				if(copVisit[now.node])
					continue;
				if(isLeaf[now.node]) {
					System.out.print("YES");
					return;
				}
			}
			for(Node next=adNode[now.node]; next!=null; next=next.next) {
				if(now.isCop)
				{
					if(!copVisit[next.node])
					{
						copVisit[next.node] = true;
						q.add(new Person(true,  next.node));
					}
				}
				if(!now.isCop) {
					if(!copVisit[next.node]&& !runVisit[next.node]) {
						runVisit[next.node] = true;
						q.add(new Person(false, next.node));
					}
				}
			}
		}
		
		
		System.out.print("NO");
	}
}