// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.ArrayList;

class Main{ 
	static int start, end, time, MAX, route[], value[] = {-1,1,2};
	static boolean visit[];
	static StringBuilder sb;
	static ArrayList<Integer> list;
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    
	public static void DFS(int node) 
	{
		list.add(node);
		if(node == start)
			return;
		DFS(route[node]);
	}
	public static void BFS() 
	{
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(start, 0));			// start노드까지 오는데 걸린시간 0초 
		visit[start] = true; 				// 시작점 방문 처리 
		
		int nextNode, nextTime;
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(now.node == end) 
			{
				time = now.time;
				return;
			}
			
			nextTime = now.time + 1;
			
			for(int v : value) 
			{
				nextNode = (v != 2 ? now.node + v : now.node << 1); // 다음 노드 경로

				if(0<= nextNode && nextNode< MAX && !visit[nextNode])
				{
					visit[nextNode] = true; // 해당 노드 방문처리
					route[nextNode] = now.node; // nextNode까지 가기 바로전에 값은 now.node이기 때문에 바로 전값 저장(추후 경로찾을 때 사용)
					q.add(new Node(nextNode, nextTime));
				}
			}
			
		}
		
	}
	public static void main(String[] args)throws Exception
	{
		start 				= read();
		end 				= read();
		MAX					= end+2;
		route 				= new int[MAX]; 		// 최종적으로 경로를 출력할 것
		visit 				= new boolean[MAX]; 	// 방문 체크
		sb 					= new StringBuilder();
		list 				= new ArrayList<>(); 	// 경로를 담을 리스트
		if(start == end) 
			sb.append(0).append('\n').append(start);
		else if(start > end) 
		{
			sb.append(start - end).append('\n');
			for(int i=start; i>=end; i--)
				sb.append(i).append(' ');
		}
		else 
		{
			BFS(); 										// BFS 탐색
			DFS(end); 									// 경로 탐색
			
			sb.append(time).append('\n');
			
			for(int i=list.size()-1; i>=0; i--)
				sb.append(list.get(i)).append(' ');
		}
		System.out.println(sb);
	}
}
class Node
{
	int node, time;
	Node(int node, int time)
	{
		this.node = node;
		this.time = time;
	}
}