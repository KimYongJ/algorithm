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
	
	static int a, b, N, N_cnt, result;
	static String str;
	static boolean flag[], visit[];
	static Node adNode[];
	
	public static int DFS_OUT(int node) {
		visit[node] = true;
		if(flag[node]) return 0;

		int cnt = 0;
		for(Node n=adNode[node]; n!=null; n=n.next)
			if(flag[n.node]) 
				cnt++;
			else if(!visit[n.node]) 
			
				cnt += DFS_OUT(n.node);

		return cnt;
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N 		= Integer.parseInt(br.readLine());
		adNode 	= new Node[N+1];
		flag 	= new boolean[N+1]; 		// true인 경우 실내, false는 실외 이다.
		
		str = br.readLine();
		for(int i=1; i<=N; i++)
			flag[i] = str.charAt(i-1) == '1'; 
		
		
		for(int i=1; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			a 			= Integer.parseInt(st.nextToken());
			b 			= Integer.parseInt(st.nextToken());
			adNode[a] 	= new Node(b, adNode[a]);
			adNode[b] 	= new Node(a, adNode[b]);
		}
		
		// 실외 먼저 탐색
		visit = new boolean[N+1];
		for(int i=1; i<=N; i++)
			if(!flag[i] && !visit[i]) 
			{
				N_cnt = DFS_OUT(i);
				if(N_cnt > 1)
					result += N_cnt * (N_cnt-1);
			}
		
		// 실내 탐색
		visit = new boolean[N+1];
		for(int i=1; i<=N; i++)
			if(flag[i] && !visit[i]) 
			{
				visit[i] = true;
				for(Node n=adNode[i]; n!=null; n=n.next)
					if(flag[n.node]) 
					{
						visit[n.node] = true;
						result += 2;
					}
				
			}
		
		System.out.print(result);
	}
}