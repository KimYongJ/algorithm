//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13038
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static int idx;				// 노드 번호를 트리 인덱스로 변환할 때 사용될 변수
	static int [] tree;			// 세그먼트 트리 배열
	static int [] treeIdx;		// 노드 번호 -> 세그먼트 트리 인덱스로 변환할 배열
	static int [] chainHead;	// 각 체인의 head값
	static int [] chainLevel;	// 각 체인의 level
	static int [] chainParent;	// 각 체인의 이전 체인 값
	static ArrayList<Integer>[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		
		N			= Integer.parseInt(br.readLine());
		tree		= new int[N * 4];
		treeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent	= new int[N + 1];
		adNode		= new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		StringTokenizer	st = new StringTokenizer(br.readLine());
		for(int child=2; child<=N; child++)
		{
			int parent = Integer.parseInt(st.nextToken());
			// 양방향 연결
			adNode[parent].add(child);
			adNode[child].add(parent);
		}
		
		// HLD 세팅을 위해 자식 노드가 큰것을 인접리스트에서 가장 첫번째로 옮긴다.
		setSize(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;// 1번 노드 헤드는 자기자신
		
		// 해당 함수에서 논리적인 체인으로 나누고, chain정보를 입력한다.
		setHLD(1, 0, 1);
		
		// 간선의 정보를 저장해야 하므로, 부모-자식 관계 두노드 중 자식노드에 간선 정보를 저장한다.
		for(int child=2; child<=N; child++)
			update(1, 1, N, treeIdx[child], 1); // 1번 노드를 제외한 자식 노드들의 위치에 1을 더해서 간선 연결을 표현한다.

		Q = Integer.parseInt(br.readLine());// 질문개수 (1<=100,000)
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());

			if(op == 1)// 쿼리 유형이 1이면 a,b가 주어지고, 두 정점 사이 거리 출력
			{
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int ans = 0;
				// 같은 체인이 될 때까지 레벨을 낮춘다.
				while(chainHead[a] != chainHead[b])
				{
					if(chainLevel[a] > chainLevel[b])
					{
						int tmp = a;
						a = b;
						b = tmp;
					}
					// b노드가 속한 체인의 전체 간선 정보를 쿼리해와서 ans에 더함 
					ans += query(1, 1, N, treeIdx[chainHead[b]], treeIdx[b]);
					
					b = chainParent[b];	// 다음체인으로 바로 점프
				}
				// 같은 체인이 되었다면, 같은 체인 내에서 마지막 연산을 진행
				if(treeIdx[a] > treeIdx[b])
				{
					int tmp = a;
					a = b;
					b = tmp;
				}
				
				ans += query(1, 1, N, treeIdx[a] + 1, treeIdx[b]);// 자식 노드에 간선정보를 저장하였으므로 시작은 + 1을 한다.
				
				sb.append(ans).append('\n');
				
				continue;
			}
			
			// 쿼리 유형이 2이면 그 뒤 정수 v가 주어지며, v정점을 제거한다.
			int v = Integer.parseInt(st.nextToken());
			// 해당 노드에 0을 대입해 노드 삭제를 표현
			update(1, 1, N, treeIdx[v], 0);
		}
		System.out.print(sb);
	}
	static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
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
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heavy 노드면 기존 체인 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light면 새로운 체인 시작
			{
				chainHead[next] = next;				// 새체인시 헤드는 자기자신
				chainParent[next] = nowNode;		// 이전 체인으로 점프할 노드 세팅
				setHLD(next, nowNode, level + 1);	// 새 체인시 level 증가
			}
		}
	}
	static void setSize(int nowNode, int parentNode, int[] size) {
		int heavySize = 0;
		int heavyIdx = 0;
		size[nowNode] = 1;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			if(next == parentNode)
				continue;
			
			setSize(next, nowNode, size);
			
			size[nowNode] += size[next];
			
			if(heavySize < size[next])
			{
				heavySize = size[next];
				heavyIdx = i;
			}
		}
		if(adNode[nowNode].size() > 0)
			Collections.swap(adNode[nowNode], 0, heavyIdx);
	}
}
//8				// 정점 개수(1<=100,000)
//1 5 2 1 2 5 5	// 1번은 루트, 2번노드부터 그 부모 노드가 주어짐
//7				// 질문개수 (1<=100,000)
//2 4
//1 7 6			// 쿼리 유형이 1이면 a,b가 주어지고, 두 정점 사이 거리 출력
//2 5			// 쿼리 유형이 2이면 그 뒤 정수 v가 주어지며, v정점을 제거한다.
//1 3 8
//1 8 6
//2 2
//1 8 6
////
//4
//2
//3
//2