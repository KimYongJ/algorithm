//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/30092
//4초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static int idx;					// 노드번호 -> 세그먼트 트리 인덱스 변환할 변수
	static int[] tree;				// 세그먼트 트리
	static int[] lazy;				// lazy propagation을 위한 배열
	static int[] treeIdx;			// 노드번호 -> 세그먼트 트리 인덱스 변환 정보를 담은 배열
	static int[] chainHead;			// 체인의 head값
	static int[] chainLevel;		// 체인의 level
	static int[] chainParent;		// 이전 체인으로 점프를 위한 이전 체인의 도착노드 값
	
	static ArrayList<Integer>[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		N			= Integer.parseInt(st.nextToken());
		Q			= Integer.parseInt(st.nextToken());
		treeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent	= new int[N + 1];
		tree		= new int[N * 4];
		lazy		= new int[N * 4];
		adNode		= new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adNode[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adNode[a].add(b);
			adNode[b].add(a);
		}
		
		// 이 함수에서 자식 노드의 크기를 확인하여 인접노드에 첫번째에 가장 무거운 노드를 오게 한다.
		setSize(1, 0, new int[N + 1]);
		// 1번 노드의 헤드는 자기자신
		chainHead[1] = 1;
		chainParent[1] = 1;
		// HLD에 필요한 값 세팅
		setHLD(1, 0, 1);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			rangeUpdate(a, b, 1);	// a-b 노드 구간을 1로 업데이트하여 노드가 끊어짐을 표현
			
			sb.append(isConnect(c,d) ? "YES" : "NO").append('\n');
			
			rangeUpdate(a, b, -1);	// a-b 노드 구간을 다시 -1을 더해 노드 연결을 복구
		}
		System.out.print(sb);
	}
	static boolean isConnect(int n1, int n2) {
		int ans = 0;
		// 체인 헤드가 같아질 때 까지 레벨을 올리면서 query결과가 true면 false를 반환해서 연결이 끊어짐을 알린다.
		while(chainHead[n1] != chainHead[n2])
		{
			if(chainLevel[n1] > chainLevel[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			
			ans = Math.max(ans ,query(1, 1, N, treeIdx[chainHead[n2]], treeIdx[n2]));

			n2 = chainParent[n2];
		}
		if(treeIdx[n1] > treeIdx[n2])
		{
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		
		ans = Math.max(ans, query(1, 1, N, treeIdx[n1] + 1, treeIdx[n2]));
		
		return ans == 0;
	}
	static void rangeUpdate(int n1, int n2, int value) {
		// 체인 헤드가 같아질 때 까지 레벨을 올린다. 
		while(chainHead[n1] != chainHead[n2])
		{
			if(chainLevel[n1] > chainLevel[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			
			update(1, 1, N, treeIdx[chainHead[n2]], treeIdx[n2], value);
			
			n2 = chainParent[n2];
		}
		// 같은 체인이 되었으면 마지막으로 같은 체인 내에서 연산
		if(treeIdx[n1] > treeIdx[n2])
		{
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		update(1, 1, N, treeIdx[n1] + 1, treeIdx[n2], value);
	}
	static int query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return Math.max(query(treeNode << 1, s, mid, left, right),
						query(treeNode << 1 | 1, mid + 1, e, left, right));
	}
	static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.max(tree[treeNode << 1] , tree[treeNode << 1 | 1]);
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1] += lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heavy 노드는 체인 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light는 새로운 채인 생성
			{
				chainHead[next] = next;			// 새체인의 헤드는 자기자신
				chainParent[next] = nowNode;	// 이전 체인으로 점프를 위한 노드 세팅
				setHLD(next, nowNode, level + 1);// 새 체인은 level이 1증가한다.
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
//8 4		// 노드 수(2<=100,000), 쿼리수(1<=300,000)
//1 2		// 연결된 두 노드번호로 양방향 연결
//1 3
//3 4
//3 5
//3 6
//4 7
//4 8
//6 7 2 3	// A,B,C,D가 주어지며 A->B로 가는 최단 경로에 모든 간선제거 후, C->D로 가는 경로가 있다면 YES, 없으면 NO출력
//6 7 7 8
//4 6 1 5
//4 7 4 8
////
//YES
//NO
//YES
//YES