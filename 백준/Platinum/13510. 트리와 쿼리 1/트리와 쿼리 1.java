//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13510
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
	static ArrayList<Node>[] adNode;	// 인접리스트
	static int hldCnt;
	static int[][] edge;				// 간선 정보 저장
	static int[] tree;					// 세그먼트 트리
	static int[] treeNum;				// HLD용 : 입력된 노드 번호 -> 세그먼트 트리 인덱스로 변환할 값
	static int[] chainLevel;			// HLD용 : 노드 깊이(체인 레벨)
	static int[] chainHead;				// HLD용 : 각 체인 마다의 head 노드
	static int[] chainParent;			// HLD용 : 내가 속한 체인의 head 노드의 바로 윗노드, 즉, 다음 체인으로 첨프할 때 도착할 노드
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N				= Integer.parseInt(br.readLine());
		adNode			= new ArrayList[N + 1];
		edge			= new int[N + 1][];
		tree			= new int[N * 4];
		treeNum			= new int[N + 1];
		chainLevel		= new int[N + 1];
		chainHead		= new int[N + 1];
		chainParent		= new int[N + 1];
		hldCnt			= 0;
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			adNode[node1].add(new Node(node2, value));
			adNode[node2].add(new Node(node1, value));
			
			edge[i] = new int[]{node1, node2};
		}
		
		// 서브트리 크기를 파악해서 가장 큰 서브트리를 갖는 객체를 adNode의 가장 앞으로 옮김
		computeSizeAndSwapHeavyChild(1, 0, new int[N + 1]);
		
		// HLD 초기 설정
		chainHead[1] = 1;	// 1번 노드의 체인헤드는 1번
		chainParent[1] = 1;	// 1번 노드의 체인은 1번이며, 가장 처음도 1번
		
		setHldDfs(1, 0, 1);
		
		Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());

			if(op == 1)// 1 i c : i번 간선의 비용을 j로 바꾼다.
			{
				int i		= Integer.parseInt(st.nextToken());
				int c		= Integer.parseInt(st.nextToken());
				int node1	= edge[i][0];
				int node2	= edge[i][1];
				
				if(treeNum[node1] > treeNum[node2]) 
				{
					int tmp = node1;
					node1 = node2;
					node2 = tmp;
				}
				// 세그먼트 트리의 인덱스로 변경했을 때 큰수, 즉, 자식 노드가 node2가 되도록 한다.
				// A가 부모, B가 자식 노드일 때 A-B사이 간선을 B노드에 저장해 놓았기 때문에 이렇게 한다.
				update(1, 1, N, treeNum[node2], c);
				
				continue;
			}
			
			// 이하 u에서 v로가는 단순 경로중 가장 큰 가중치를 출력한다. 
			
			int low = Integer.parseInt(st.nextToken());
			int high = Integer.parseInt(st.nextToken());
			
			if(chainLevel[low] > chainLevel[high]) 
			{
				int tmp = low;
				low = high;
				high = tmp;
			}
			
			int ans = 0;
			
			// chainLevel[low]과 chainLevel[high] 값이 같아질 때 까지 chainLevel[high]을 내리며
			// 내리면서 해당 구간의 가장 큰 가중치를 쿼리로 구해나간다.
			while(chainLevel[low] < chainLevel[high])
			{
				ans = Math.max(ans, query(1, 1, N, treeNum[chainHead[high]], treeNum[high]));
				high = chainParent[high];// 다음 체인으로 바로 점프
			}
			// 위 while문에서 레벨은 같아졌지만, 같은 체인에 포함되지 않을 수 있어 같은 체인이 될 때 까지 같이 점프하며 값을 구해나간다.
			while(chainHead[high] != chainHead[low])
			{
				ans = Math.max(ans, query(1, 1, N, treeNum[chainHead[low]], treeNum[low]));
				ans = Math.max(ans, query(1, 1, N, treeNum[chainHead[high]], treeNum[high]));
				low = chainParent[low];		// 다음 체인으로 점프
				high = chainParent[high];	// 당므 체인으로 점프
			}
			
			// 여기까지 오면 같은 체인안에 있는 것이다.
			// 주어진 노드번호가, 세그먼트 트리 노드의 인덱스로 변경했을 때, low가 작은 값이 되도록 스왑한다.
			if(treeNum[low] > treeNum[high])
			{
				int tmp = low;
				low = high;
				high = tmp;
			}
			// treeNum[low] + 1을 하는 이유 : 
			// 세그먼트 트리에 값을 저장할 때, A가 부모노드이고, B가 자식노드일 때 간선의 가중치를 B위치에 저장했다.
			// 그렇기 때문에 해당 범위까지 검색으로 제한해야 정확한 값을 구할 수 있다.
			ans = Math.max(ans, query(1, 1, N, treeNum[low] + 1, treeNum[high]));
			
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode << 1, s, mid, left, right);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return Math.max(l, r);
	}
	public static void update(int treeNode, int s, int e, int targetIdx, int value) {
		
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
	public static void setHldDfs(int nowNode, int parentNode, int level) {
		treeNum[nowNode] = ++hldCnt;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			Node now = adNode[nowNode].get(i);
			
			if(now.node == parentNode)
				continue;
			
			if(i == 0)	// heavy child / 처음인 경우, 즉, 서브트리의 크기가 가장 큰경우 
			{
				chainHead[now.node]		= chainHead[nowNode];	// 기존 체인 유지시 해당 체인의 head 저장
				chainParent[now.node]	= chainParent[nowNode];	// chainParent[v] : v 체인의 head 바로 위 노드 (다음 체인으로 점프용)  
				setHldDfs(now.node, nowNode, level);
			}
			else		// light child : 새 체인 시작
			{
				chainHead[now.node] = now.node;					// 새 체인 시작시 해당 체인의 head는 자기 자신
				chainParent[now.node] = nowNode;				// 새 체인 시작시 now.node는 nowNode로 바로 점프할 수 있게 저장
				setHldDfs(now.node, nowNode, level + 1);
			}
			
			// 연결된 간선 가중치로 세그먼트트리 업데이트 
			update( 1, 1, N, treeNum[now.node], now.value);
		}
	}
	public static void computeSizeAndSwapHeavyChild(int nowNode, int parentNode, int size[]) {
		int heavySize = 0;
		int heavyIdx = 0;
		
		size[nowNode] = 1;
		
		for(int i=0; i< adNode[nowNode].size(); i++)
		{
			Node now = adNode[nowNode].get(i);
			
			if(now.node == parentNode)
				continue;
			
			computeSizeAndSwapHeavyChild(now.node, nowNode, size);	// DFS순회
			// 현재 노드의 크기에 자식 노드의 크기를 더함
			size[nowNode] += size[now.node];
			
			if(heavySize < size[now.node])
			{
				heavySize = size[now.node];
				heavyIdx = i;
			}
		}
		// 가장 무거운 인덱스를 제일 앞에 놓는다.
		Collections.swap(adNode[nowNode], 0, heavyIdx);
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