// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	
	static int T, V, E, node1, node2, flag[];
	static ArrayList<Integer>[] list;

	public static boolean DFS(int node, int flagValue) 
	{
		boolean f = false;
		flag[node] = flagValue;
		for(int nextNode : list[node]) 
		{
			if(flag[nextNode] == 0) {
				f =  DFS(nextNode, flag[node] * -1);
				if(!f)
					return f;
			}
			if(flag[nextNode] == flag[node]) 
				return false;
		}
		return true;
	}

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			flag = new int[V+1];
			list = new ArrayList[V+1];
			
			for(int i=1; i<=V; i++)
				list[i] = new ArrayList<>();
			
			for(int i=0; i<E; i++) 
			{
				st = new StringTokenizer(br.readLine());
				node1 = Integer.parseInt(st.nextToken());
				node2 = Integer.parseInt(st.nextToken());
				list[node1].add(node2);
				list[node2].add(node1);
			}
			
			boolean result = false;
			
			for(int i=1; i<=V; i++) 
			{
				if(flag[i] == 0)
					result = DFS(i,1);
				if(!result) 
					break;
			}
			
			sb.append(result  ? "YES" : "NO")
				.append('\n');
		}
		System.out.println(sb);
	}
}


///////////////////////// 이하 BFS 방법
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//class Main{
//	
//	static int T, V, E, node1, node2, flag[];
//	static ArrayList<Integer>[] list;
//	static ArrayDeque<Integer> q;
//	public static boolean BFS(int i) {
//		int nextFlag;
//		flag[i] = 1;
//		q.add(i);
//		
//		
//		while(!q.isEmpty()) 
//		{
//			int now = q.poll();
//			nextFlag = flag[now] * -1;
//			for(int next : list[now]) 
//			{
//				if(flag[next] == flag[now])
//					return false;
//				else if(flag[next] == 0) {
//					flag[next] = nextFlag;
//					q.add(next);
//				}
//			}
//			
//		}
//		
//		
//		return true;
//	}
//
//	public static void main(String[] args)throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		
//		
//		T = Integer.parseInt(br.readLine());
//		while(T-->0) {
//			st = new StringTokenizer(br.readLine());
//			V = Integer.parseInt(st.nextToken());
//			E = Integer.parseInt(st.nextToken());
//			flag = new int[V+1];
//			list = new ArrayList[V+1];
//			q = new ArrayDeque<>();
//			
//			for(int i=1; i<=V; i++)
//				list[i] = new ArrayList<>();
//			
//			for(int i=0; i<E; i++) 
//			{
//				st = new StringTokenizer(br.readLine());
//				node1 = Integer.parseInt(st.nextToken());
//				node2 = Integer.parseInt(st.nextToken());
//				list[node1].add(node2);
//				list[node2].add(node1);
//			}
//			
//			boolean result = false;
//			
//			for(int i=1; i<=V; i++) 
//			{
//				if(flag[i] == 0)
//					result = BFS(i);
//				if(!result) break;
//			}
//			
//			sb.append(result  ? "YES" : "NO")
//				.append('\n');
//		}
//		System.out.println(sb);
//	}
//}