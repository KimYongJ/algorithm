//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2927
//5초 128MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int idx;					// 주어지는 노드 인덱스를 세그먼트 트리 인덱스로 변환할 때 값
	static int chainLevelValue;		// 각 체인당 레벨
	static int[] size;				// 각 체인의 사이즈
	static int[] arr;				// 초기 입력되는 노드의 기본값
	static int[] tree;				// 구간합 세그먼트 트리
	static int[] treeIdx;			// 노드 번호 -> 세그먼트 트리 인덱스 전환 값을 담을 배열
	static int[] chainHead;			// 각 체인의 head 값
	static int[] chainLevel;		// 각 체인의 level
	static int[] chainParent;		// 각 체인의 이전 체인으로 점프할 도착노드번호
	static Query[] query;			// 주어지는 질의 정보를 담을 배열
	static UnionFind uf;			// 유니온 파인드 알고리즘을 위한 배열
	static ArrayList<Integer>[] adNode;// 인접노드
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N			= Integer.parseInt(br.readLine());// 섬의 수 1<=30,000
		size		= new int[N + 1];
		arr			= new int[N + 1];
		tree		= new int[N * 4];
		treeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent = new int[N + 1];
		uf			= new UnionFind(N);
		adNode		= new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());// 각 섬의 펭귄수 0<=1,000
			adNode[i] = new ArrayList<>();
		}

		Q = Integer.parseInt(br.readLine());// 쿼리 수 1<=300,000
		
		query = new Query[Q + 1];
		
		for(int idx=1; idx<=Q; idx++)
		{
			st = new StringTokenizer(br.readLine());
			
			int cmd = 0, n1 = 0, n2 = 0;
			boolean ok = false;
			
			String o = st.nextToken();				// 명령어
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			if("excursion".equals(o))// A-B로 갈수 있는 경우 모든 펭귄의 수를 출력, 이동 불가시 impossible출력
			{
				cmd = 1;
				int p1 = uf.find(n1);	// 부모노드 가져옴
				int p2 = uf.find(n2);	// 부모노드 가져옴
				if(p1 == p2)			// 부모노드가 같으면 연결되어 있는 것
					ok = true;
			}
			else if("bridge".equals(o))// 두 노드를 연결 명령, 이전까지 지어진 다리를 이용해 이동할 수 없을 경우에만 다리를 지어야함, 지어야하면 yes, 아니면no출력
			{
				cmd = 2;
				// 연결 되어있지 않다면 연결 후 true반환, 연결 되어 있었다면 false
				boolean isConnect = uf.union(n1, n2);
				if(isConnect)
				{
					ok = true;			// brigde일 때, 1은 yes를 의미
					adNode[n1].add(n2);	// 인접 노드연결
					adNode[n2].add(n1);	// 인접 노드연결
				}
			}
			else// penguins A X - 섬 A에 살고있는 펭귄 수를 X로 교체 출력필요없음
			{
				cmd = 3;
			}
			
			query[idx] = new Query(cmd, n1, n2, ok);
		}
		// setSize함수에서 체인 분할을 위해 가장 무거운 노드를 인접노드 중 가장 앞으로 옮긴다.
		for(int i=1; i<=N; i++)	// 트리가 여러개일 수 있어서 각 노드마다 모두 탐색
			if(size[i] == 0)
				setSize(i, 0);
		
		// 트리가 모두 연결되어있지 않을 수 있다. 그래서 모든 노드에 대해 체인 세팅을 해주어야 함, 체인 미지정 노드는 chainHead 값이 0
		// setHLD함수에서 세그먼트트리에 대응하는 노드 인덱스를 생성 및 체인 정보들 입력
		for(int i=1; i<=N; i++)
		{
			if(chainHead[i] == 0)
			{
				chainParent[i] = i;
				chainHead[i] = i;
				setHLD(i, 0, ++chainLevelValue);
			}
		}

		StringBuilder sb = new StringBuilder();
	
		for(int idx=1; idx<=Q; idx++)// 결과 연산
		{
			Query q = query[idx];
			
			if(q.cmd == 1 && !q.ok)	// 연결 불가시 바로 impossible출력 후 다음 연산
			{
				sb.append("impossible").append('\n');
				continue;
			}
			
			if(q.cmd == 2)	// bridge 명령일 때는 이전에 이미 구했으니 스킵
			{
				sb.append(q.ok ? "yes" : "no").append('\n');
				continue;
			}
			
			if(q.cmd == 3)// penguins A X - 섬 A에 살고있는 펭귄 수를 X로 교체 출력필요없음
			{
				update(1, 1, N, treeIdx[q.n1], q.n2);
				continue;
			}

			// 이하 excursion 명령으로 A-B로 갈수 있는 경우 모든 펭귄의 수를 출력
			
			// 먼저 chain을 올린다.
			int n1 = q.n1;
			int n2 = q.n2;
			if(n1 == n2)	// 한 정점에 대해 탐색시 바로 결과 출력
			{
				sb.append(query(1, 1, N, treeIdx[n1], treeIdx[n1])).append('\n');
				continue;
			}
			
			int ans = 0;
			
			// 체인이 같아 질 때 까지 더 깊은 레벨인 n2를 올린다. 
			while(chainHead[n1] != chainHead[n2])
			{
				if(chainLevel[n1] > chainLevel[n2])	// 체인 레벨은 n2가 더 높아야 한다.
				{
					int tmp = n1;
					n1 = n2;
					n2 = tmp;
				}

				ans += query(1, 1, N, treeIdx[chainHead[n2]], treeIdx[n2]);

				n2 = chainParent[n2];
			}

			// 여기까지 온것이면 같은 체인에 속한것
			if(treeIdx[n1] > treeIdx[n2])	// n2가 트리상 인덱스가 더 커야한다.
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			
			ans += query(1, 1, N, treeIdx[n1], treeIdx[n2]);
			
			sb.append(ans).append('\n');
		}
		
		System.out.print(sb);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left <=s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
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
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heavy인 경우 체인 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light 는 새로운 체인 시작
			{
				chainHead[next] = next;				// head는 자기자신
				chainParent[next] = nowNode;		// 다음 체인으로 점프를 위한 이전노드 삽입
				setHLD(next, nowNode, level + 1);	// 새 체인은 level증가
			}
		}
		
		update(1, 1, N, treeIdx[nowNode], arr[nowNode]);
	}
	public static void setSize(int nowNode, int parentNode) {
		int heavySize = 0;
		int heavyIdx = 0;
		size[nowNode] = 1;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			setSize(next, nowNode);
			
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
class UnionFind{
	int N;
	int parent[];
	UnionFind(int N){
		this.N = N;
		this.parent = new int[N + 1];
		
		for(int i=1; i<=N; i++)
			parent[i] = i;
	}
	public boolean union(int node1, int node2) {
		int parent1 = find(node1);
		int parent2 = find(node2);
		
		if(parent1 != parent2)
		{
			if(parent1 < parent2)
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;

			return true;// 다리를 지어야하면 yes반환
		}
		
		return false; // 다리를 지을 필요가 없으면 no반환
	}
	public int find(int node) {
		if(parent[node] == node)
			return node;
		
		return parent[node] = find(parent[node]);
	}
}
class Query{
	// cmd = (1)excursion, (2)bridge, (3)penguins
	int cmd, n1, n2;
	boolean ok;
	Query(int cmd, int n1, int n2, boolean ok){
		this.cmd = cmd;
		this.n1 = n1;
		this.n2 = n2;
		this.ok = ok;
	}
}

//5				// 섬의 수 1<=30,000
//4 2 4 5 6		// 각 섬의 펭귄수 0<=1,000
//10				// 쿼리 수 1<=300,000
//excursion 1 1	// bridge , excursion 일 때 출력(penguins A X - 섬 A에 살고있는 펭귄 수를 X로 교체 출력필요없음)
//excursion 1 2	// A-B로 갈수 있는 경우 모든 펭귄의 수를 출력, 이동 불가시 impossible출력
//bridge 1 2		// 두 노드를 연결 명령, 이전까지 지어진 다리를 이용해 이동할 수 없을 경우에만 다리를 지어야함, 지어야하면 yes, 아니면no출력
//excursion 1 2
//bridge 3 4
//bridge 3 5
//excursion 4 5
//bridge 1 3
//excursion 2 4
//excursion 2 5