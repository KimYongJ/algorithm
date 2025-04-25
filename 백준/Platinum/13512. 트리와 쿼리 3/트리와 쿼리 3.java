//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13512
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{
	
	static final int MAX = 1<<29;
	static int N, Q;
	static int idx;
	static int[] tree;
	static int[] nodeToTreeIndex;	// 노드 번호를 세그먼트 트리 인덱스로 변환하는 배열
	static int[] treeIndexToNode;	// 세그먼트 트리 인덱스를 노드 번호로 변환하는 배열
	static int[] chainHead;
	static int[] chainLevel;
	static int[] chainParent;
	static ArrayList<Integer> adNode[];
	
	static void update(int treeNode, int s, int e, int targetIdx) {
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			tree[treeNode] = tree[treeNode] == MAX ? s : MAX;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	
	static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return MAX;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode << 1, s, mid, left, right);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return Math.min(l, r);
	}
	
	static void setSize(int nowNode, int parentNode, int size[]) {
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
	
	static void setHLD(int nowNode, int parentNode, int level) {
		chainLevel[nowNode] = level;
		nodeToTreeIndex[nowNode] = ++idx;	// 노드인덱스를 세그먼트 트리에서 사용할 인덱스로 변경
		treeIndexToNode[idx] = nowNode;		// 세그먼트 트리에서 사용할 인덱스를 노드번호로 변경
		
		for(int i=0; i<adNode[nowNode].size(); i++) {
			int next = adNode[nowNode].get(i);
			if(next == parentNode)
				continue;
			if(i == 0)	// heavy로, 현재 체인 유지시
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// 새로운 체인 시작
			{
				chainHead[next] = next;			// 헤드는 자기자신
				chainParent[next] = nowNode;	// 점프할 노드 저장
				setHLD(next, nowNode, level + 1);// 레벨은 + 1
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder	sb = new StringBuilder();
		StringTokenizer	st;
		
		N				= Integer.parseInt(br.readLine());// 노드개수 2<=100,000
		tree			= new int[N * 4];
		chainHead		= new int[N + 1];
		chainLevel		= new int[N + 1];
		chainParent		= new int[N + 1];
		adNode			= new ArrayList[N + 1];
		nodeToTreeIndex = new int[N + 1];
		treeIndexToNode	= new int[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adNode[n1].add(n2);
			adNode[n2].add(n1);
		}
		
		Arrays.fill(tree, MAX);
		// 해당 노드에서 heavy노드를 맨 앞으로 옮긴다.
		setSize(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;	// 1번 노드의 헤드는 자기자신
		
		setHLD(1, 0, 1);	// HLD에 필요한 chain정보 및 인덱스들 세팅
		
		Q = Integer.parseInt(br.readLine());
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			
			if(op == 1)	// 1인 경우 i번 정점 교체
			{
				update(1, 1, N, nodeToTreeIndex[i]);
				continue;
			}
			
			// op가 2인 경우 i번 정점부터 위로 올라오면서 최소 검정을 찾음, 검정은 세그먼트트리에서 MAX보다 작은 값 
			int ans = MAX;
			// 같은 체인까지 만듬
			while(chainHead[1] != chainHead[i])
			{
				ans = Math.min(ans,  query(1, 1, N, nodeToTreeIndex[chainHead[i]], nodeToTreeIndex[i]));
				
				i = chainParent[i];
			}
			// 같은 체인 후 체인안에서 마지막으로 비교하여 정답을 구함
			ans = Math.min(ans, query(1, 1, N, nodeToTreeIndex[1], nodeToTreeIndex[i]));
			// 출력은 노드번호이므로, 세그먼트 트리의 인덱스를 노드번호로 바꾸기 위해 treeIndexToNode 배열을 사용
			sb.append(ans == MAX ? -1 : treeIndexToNode[ans]).append('\n');
		}
		System.out.print(sb);
	}
}

//9			// 노드개수 2<=100,000
//1 2		// 간선을 연결하는 두 정점
//1 3
//2 4
//2 9
//5 9
//7 9
//8 9
//6 8
//8		// 쿼리 개수 1<=100,000
//2 3
//1 8		// 1일때, 입력된 노드의 색을 반전시킨다. 초기값은 흰색
//2 6		// 2일때, 1번에서 v번 정점으로 가는 경로에 존재하는 첫 번째 검정 정점의 번호를 출력
//2 7
//1 2
//2 9
//1 2
//2 9
//// 답
//-1
//8
//-1
//2
//-1