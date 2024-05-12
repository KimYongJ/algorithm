// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Node{
	int y,x;
	Node(int y, int x){this.y=y;this.x=x;}
}
class AD{
	int node;
	AD next;
	AD(int node, AD next){
		this.node=node;this.next=next;
	}
}
class Main{
	
	static int orderCnt, order[];
	static AD[] adList;
	static List<Node> result;
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static int DFS(int nowNode, int parentNode) {
		int value = order[nowNode] = ++orderCnt;
		int next, 
			low;// 현재 노드와 자기 자식노드들을 통해 도달할 수 있는 가장 작은 orderCnt 값이다.

		for(AD now=adList[nowNode]; now!=null; now=now.next)
		{
			next = now.node;
			if(next != parentNode) 									// 자식 노드가 자기의 부모노드와 같지 않을 때만 연산 진행
			{
				
				if(order[next] == 0) 								// 자식 노드가 첫방문이면 DFS진행
				{
					low = DFS(next, nowNode);
					
					if(low > order[nowNode]) 						// 자식 노드의 가장 낮게 도달할 수 있는 orderCnt값이 현재 노드의 orderCnt값 보다 크다면 현재 노드를 통해서만 위로 갈 수 있기 때문에 둘의 관계는 단절선이 된다. 
					{
						if(next > nowNode) 
							result.add(new Node(nowNode, next));	// 출력 조건이 왼쪽이 작은 값이여야 하기 때문에 분기 처리 
						else
							result.add(new Node(next, nowNode));	// 출력 조건이 왼쪽이 작은 값이여야 하기 때문에 분기 처리 
					}
					value = Math.min(value, low);
				}else 												// 자식 노드가 첫방문이 아니라면 자식노드의 order배열 안에있는값(자식노드가 닿을 수 있는 가장 작은 orderCnt값) 과 value중 작은것을 value에 넣는다. 
				{
					value = Math.min(value, order[next]);           // 자식노드가 방문했다면, 자식노드의 orderCnt와 value중 작은 값을 고른다. 
				}
			}
		}
		return value; // value는 해당 DFS를 통해 가장 낮게 도달할 수 있는 orderCnt 값이다.
	}
	public static void main(String[] args)throws Exception{
		int N 	= read();
		int M 	= read();
		order 	= new int[N+1];
		adList 	= new AD[N+1];
		result	= new ArrayList<>();
		
		int a,b;
		for(int i=0; i<M; i++) 
		{
			a	= read();
			b 	= read();
			adList[a] = new AD(b, adList[a]);
			adList[b] = new AD(a, adList[b]);
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
			sb.append(node.y).append(' ')
				.append(node.x).append('\n');
		}
		System.out.print(sb);
	}
}