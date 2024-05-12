// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Node{
	int y,x;
	Node(int y, int x){this.y=y;this.x=x;}
}
class Main{
	
	static int orderCnt, order[];
	static List<Integer>[] adList;
	static List<Node> result;

	public static int DFS(int nowNode, int parentNode) {
		int value = order[nowNode] = ++orderCnt;
		int low;
		for(int next : adList[nowNode]) {
			if(next != parentNode) {
				
				if(order[next] == 0) {
					low = DFS(next, nowNode);
					
					if(low > order[nowNode]) {
						if(next > nowNode) {
							result.add(new Node(nowNode, next));
						}else
							result.add(new Node(next, nowNode));
					}
					value = Math.min(value, low);
				}else {
					value = Math.min(value, order[next]);
				}
			}
		}
		return value;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		int N 	= Integer.parseInt(st.nextToken());
		int M 	= Integer.parseInt(st.nextToken());
		order 	= new int[N+1];
		adList 	= new ArrayList[N+1];
		result	= new ArrayList<>();
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		int a,b;
		for(int i=0; i<M; i++) 
		{
			st 	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b 	= Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		for(int i=1; i<=N; i++)
			if(order[i] == 0)
				DFS(i, i);

		Collections.sort(result, (a1,b1) -> {
			if(a1.y==b1.y)
				return a1.x-b1.x;
			return a1.y-b1.y;
		});
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append('\n');
		for(int i=0; i<result.size(); i++) {
			Node node = result.get(i);
			sb.append(node.y).append(' ').append(node.x).append('\n');
		}
		System.out.print(sb);
	}
}