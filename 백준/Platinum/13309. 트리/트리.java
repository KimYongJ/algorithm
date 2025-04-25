//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13309
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{

	static int N, Q;
	static int idx;
	static int[] treeIdx;
	static int[] chainHead;
	static int[] chainLevel;
	static int[] chainParent;
	static boolean[] tree;
	static ArrayList<Integer>[] adNode;

	public static void main(String[] ags)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		
		N			= Integer.parseInt(st.nextToken());// 트리의 정점개수(1 ≤ 200,000)
		Q			= Integer.parseInt(st.nextToken());// 질의 개수(1 ≤ 200,000)
		tree		= new boolean[N * 4]; // 초기값 다 false로, 연결시 false
		treeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent	= new int[N + 1];
		adNode		= new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int child=2; child<=N; child++)
		{
			int parent = Integer.parseInt(br.readLine());
			adNode[parent].add(child);
		}
		
		// 해당 함수에서 HLD를 위한 무거운 자식 노드를 맨앞으로 옮긴다.
		moveHeavyChildToFront(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;	// 1번노드의 헤드는 자기 자신
		
		setHLD(1, 0, 1);	// HLD 세팅
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			boolean isPossible = true;
			
			int node1 = b;
			int node2 = c;
			// 레벨을 맞춘다
			if(chainLevel[node1] > chainLevel[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			while(isPossible && chainLevel[node1] != chainLevel[node2])
			{
				boolean notConnected = query(1, 1, N, treeIdx[chainHead[node2]], treeIdx[node2]);
				if(!notConnected)	// 노드가 연결되어있는게 맞다면
					node2 = chainParent[node2];// 다음 체인으로 점프
				else
					isPossible = false;
			}
			// 같은 헤더까지 반복해서 둘다 레벨을 낮춘다.
			while(isPossible && chainHead[node1] != chainHead[node2])
			{
				boolean notConnected1 = query(1, 1, N, treeIdx[chainHead[node1]], treeIdx[node1]);
				boolean notConnected2 = query(1, 1, N, treeIdx[chainHead[node2]], treeIdx[node2]);
				
				if(!notConnected1 && !notConnected2)	// 노드가 연결되어있는게 맞다면, 다음 체인으로 점프
				{
					node1 = chainParent[node1];
					node2 = chainParent[node2];
				}
				else
					isPossible = false;
			}
			// 위 코드들을 통해 같은 체인에 속하게 되었을 것이고, 연결이 가능한경우 최종 적으로 두 노드 사이 간선 개수를 구한다.
			if(isPossible)
			{
				if(treeIdx[node1] > treeIdx[node2])
				{
					int tmp = node1;
					node1 = node2;
					node2 = tmp;
				}
				
				boolean notConnected = query(1, 1, N, treeIdx[node1] + 1, treeIdx[node2]);
				if(notConnected)
					isPossible = false;
			}
			
			if(d == 1)// d가 1이면
			{
				int deleteNode = c;// b와 c를 연결하는 통로가 없으면 c의 부모정점과 c를 연결하는 엣지 제거
				
				if(isPossible)	// b와 c를 연결하는 통로가 있으면 b의 부모 정점과 b연결 엣지 제거
				{
					deleteNode = b;
				}
				
				update(1, 1, N, treeIdx[deleteNode], true);
			}
			
			sb.append(isPossible ? "YES\n" : "NO\n");
		}
		System.out.print(sb);
	}

	static boolean query(int treeNode, int s, int e, int left, int right) {
		
		if(e < left || right < s)
			return false;

		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				| query(treeNode << 1 | 1,mid + 1, e, left, right);
	}
	static void update(int treeNode, int s, int e, int targetIdx, boolean value) {
		
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
		
		tree[treeNode] = tree[treeNode << 1] | tree[treeNode << 1 | 1];
	}
	static void setHLD(int nowNode, int parentNode, int level) {
		
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int child = adNode[nowNode].get(i);
			
			if(child == parentNode)
				continue;
			
			if(i == 0)	// heavy인 경우, 체인 그대로 유지
			{
				chainHead[child]	= chainHead[nowNode];	// heavy는 부모의 값을 그대로 저장
				chainParent[child]	= chainParent[nowNode];	// heavy는 부모의 값을 그대로 저장
				setHLD(child, nowNode, level);
			}
			else		// light인 경우, 새로운 체인 시작
			{
				chainHead[child]	= child;				// head는 자기자신
				chainParent[child]	= nowNode;				// 다음 점프할 노드는 자기의 부모노드로 세팅
				setHLD(child, nowNode, level + 1);			// 레벨을 늘려 새로운 체인 시작
			}
		}
	}
	static void moveHeavyChildToFront(int nowNode, int parentNode, int[] size) {
		
		int heavySize = 0;
		int heavyIdx = 0;
		size[nowNode] = 1;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int child = adNode[nowNode].get(i);
			
			if(child == parentNode)
				continue;
			
			moveHeavyChildToFront(child, nowNode, size);
			
			size[nowNode] += size[child];
			
			if(heavySize < size[child])
			{
				heavySize = size[child];
				heavyIdx = i;
			}
		}
		
		if(adNode[nowNode].size() > 0)
			Collections.swap(adNode[nowNode], 0, heavyIdx);
	}
}
//3 4	// 트리의 정점개수(1 ≤ 200,000), 질의 개수(1 ≤ 200,000)
//1		// 부모 정점 번호(1<=N)
//1		// 부모 정점 번호(1<=N)
//2 3 1	// b,c,d / d가1이면 b와 c를 연결하는 경로가 있는지 파악후 있으면 b의 부모 정점과 b연결 엣지 제거, 없으면, c의 부모정점과 c를 연결하는 에지 제거
//1 3 0	// b,c,d / d가0이면 b와 c를 연결하는 경로가 있는지만 파악
//2 3 1
//1 3 1
////답
//YES
//YES
//NO
//NO
