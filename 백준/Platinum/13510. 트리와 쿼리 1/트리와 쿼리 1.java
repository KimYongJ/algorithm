//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13510
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static class Node{
		int node, value;
		Node(int n, int v){
			node = n;
			value = v;
		}
	}
	
	static int N, Q;
	static int idx;						// 입력된 노드 번호를 세그먼트 트리의 인덱스로 전환할 때 증가될 인덱스 값
	static int [] tree;					// 세그먼트 트리
	static int [] treeIdx;				// HLD용 : 입력된 노드 번호 -> 세그먼트 트리 인덱스로 변환할 값
	static int [] chainHead;			// HLD용 : 각 체인 마다의 head 노드, 체인 당 첫번째 노드의 값은 자기 자신이됨
	static int [] chainLevel;			// HLD용 : 노드가 포함된 체인의 깊이(체인 레벨)
	static int [] chainParent;			// HLD용 : 내가 속한 체인의 head 노드의 바로 윗노드, 즉, 다음 체인으로 첨프할 때 도착할 노드
	static int [][] edge;				// 간선 정보 저장
	static ArrayList<Node>[] adNode;	// 인접 리스트

	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer	st;
		
		N			= Integer.parseInt(br.readLine());// 정점 수(2<=100,000)
		tree		= new int[N * 4];
		treeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent = new int[N + 1];
		edge		= new int[N + 1][];
		adNode		= new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());//비용 w(1<=1,000,000)
			adNode[node1].add(new Node(node2, value));
			adNode[node2].add(new Node(node1, value));
			edge[i] = new int[] {node1, node2};
		}
		
		// 이 함수에서는 단순히 HLD를 구현하기 위해 adNode에서 가장 heavy한 자식만 앞으로 옮기는 역할을 합니다.
		moveHeavyChildToFront(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;	// 1번노드의 헤드는 자기 자신
		
		// 해당 함수에서 HLD를 위한 chain 정보들을 마킹하고, 세그먼트 트리에 값을 업데이트 한다.
		setHLD(1, 0, 1);
		
		Q = Integer.parseInt(br.readLine());// 쿼리 개수 Q(1<=100,000)
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if(op == 1)// 1 i c : i번 간선의 비용을 j로 바꾼다.
			{
				int i = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				// 간선 정보는 자식 노드에 저장되어 있으므로, i번째 간선의 2개의 노드를 가져와서
				// 세그먼트 트리 노드의 번호로 변경했을 때 큰 값을 찾아 그 값으로 업데이트한다.
				// setHLD 함수에서 treeIdx배열 세팅 하는 것을 보면, 자식 노드가 무조건 값이 더 크다.
				int segIdx = Math.max(treeIdx[ edge[i][0] ], treeIdx[ edge[i][1] ]);
				
				update(1, 1, N, segIdx, c);
				
				continue;
			}
			// op가 2인 경우 u, v사이의 간선 중 가장 큰 값을 찾는다.
			int ans = 0;
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 레벨이 같아질 때까지 레벨이 높은 것을 낮게 만들면서 높이를 맞춘다.
			if(chainLevel[u] > chainLevel[v])	// v노드가 레벨이 더크게 조정 즉, v노드가 레벨이 더 높아야된다.
			{
				int tmp = v;
				v = u;
				u = tmp;
			}
			while(chainLevel[u] != chainLevel[v])
			{
				int head = chainHead[v];
				// 해당 체인의 head부터, 현재 v노드까지 가장 큰 값을 구해와 ans 갱신
				ans = Math.max(ans, query(1, 1, N, treeIdx[head], treeIdx[v]));
				v = chainParent[v];	// 레벨이 높은 v를 다음 체인까지 바로 점프 하도록 만든다.
			}
			
			// 레벨이 같아졌다면 같은 체인에 속하도록 두 노드의 레벨을 지속적으로 낮춘다.
			while(chainHead[v] != chainHead[u])
			{
				ans = Math.max(ans, query(1, 1, N, treeIdx[chainHead[u]], treeIdx[u]));
				ans = Math.max(ans, query(1, 1, N, treeIdx[chainHead[v]], treeIdx[v]));
				u = chainParent[u];	// 다음 체인으로 바로 점프
				v = chainParent[v];	// 다음 체인으로 바로 점프
			}
			
			// 같은 체인에 속했다면, 마지막으로 해당 두 노드간의 부모 자식 노드를 파악한 후, 그 사이의 가장 큰 값을 다시 비교한다.
			if(treeIdx[u] > treeIdx[v])	// v를 자식노드로 만든다.
			{
				int tmp = v;
				v = u;
				u = tmp;
			}
			
			// treeIdx[u] + 1을 하는 이유 : 
			// 세그먼트 트리에 값을 저장할 때, A가 부모노드이고, B가 자식노드일 때 간선의 가중치를 B위치에 저장했다. 
			// 부모 자신은 빼주어야 정확한 값을 구해올 수 있다.
			ans = Math.max(ans, query(1, 1, N, treeIdx[u] + 1, treeIdx[v]));
			
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
	
	static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			Node now = adNode[nowNode].get(i);
			
			if(now.node == parentNode)
				continue;
			
			if(i == 0)	// heavy일 경우, 기존 데이터들 그대로 이어짐
			{
				chainHead[now.node] = chainHead[nowNode];
				chainParent[now.node] = chainParent[nowNode];
				setHLD(now.node, nowNode, level);	// heavy는 level이 그대로 이어진다.
			}
			else		// 새로운 체인 시작
			{
				chainHead[now.node] = now.node;			// 해당 체인의 시작은 자기자신
				chainParent[now.node] = nowNode;		// 새 체인 시작시 now.node는 nowNode로 바로 점프할 수 있게 저장
				setHLD(now.node, nowNode, level + 1);	// 새 체인 시작시 레벨 증가
			}
			// now객체의 모든 연산이 끝난 후, 해당 노드 번호를 이용해 세그먼트 트리에 간선 값을 업데이트한다.
			// 부모노드가 A, 자식노드 B라 할 때, 노드는 2개지만 간선은 하나기 때문에 간선 정보는 자식노드에 저장한다.
			// 그래서 현재 함수에서 nowNode에 저장하는것이 아닌, 자식 노드인 now.node에 간선 정보를 저장한다.
			update(1, 1, N, treeIdx[now.node], now.value);
		}
		
	}
	static void moveHeavyChildToFront(int nowNode, int parentNode, int[] size) {
		int heavySize = 0;
		int heavyIdx = 0;
		size[nowNode] = 1;
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			Node now = adNode[nowNode].get(i);
			
			if(now.node == parentNode)	// 부모노드면 스킵
				continue;
			
			moveHeavyChildToFront(now.node, nowNode, size);
			
			size[nowNode] += size[now.node];
			
			if(heavySize < size[now.node])
			{
				heavySize = size[now.node];
				heavyIdx = i;
			}
			
		}
		// 0번째 인덱스에 가장 무거은 노드를 놓아 추후 있을 연산을 단순하게 한다.
		Collections.swap(adNode[nowNode], 0, heavyIdx);
	}
	static void update(int treeNode, int s, int e, int targetIdx, int value) {
		if(e < targetIdx || targetIdx < s)
			return;
		if(s == e)
		{
			tree[treeNode] = value;
			return;
		}
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, value);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int l = query(treeNode << 1, s, mid, left, right);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return Math.max(l,r);
	}
}
//3		// 정점 수(2<=100,000)
//1 2 1	// N-1개 줄에 i번 간선이 연결하는 두 정점 번호 u, v와 비용 w(1<=1,000,000)가 주어짐
//2 3 2
//3		// 쿼리 개수 Q(1<=100,000)
//2 1 2
//1 1 3	// 1 i c : i번 간선의 비용을 c로 바꾼다.
//2 1 2	// 2 u v : u에서 v로 가는 단순 경로에 존재하는 비용 중 가장 큰 것을 출력한다.
// 답
//1
//3
