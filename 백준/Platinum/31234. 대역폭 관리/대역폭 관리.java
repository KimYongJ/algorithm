//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/31234
//2초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static boolean isEnd = false;
	static int N, Q;
	static int idx;
	static int[] arr;
	static int[] tree;
	static int[] lazy;
	static int[] treeIdx;
	static int[] nodeIdx;
	static int[] chainHead;
	static int[] chainLevel;
	static int[] chainParent;
	static ArrayList<Integer>[] adNode;
	// 최초 입력된 정점의 최대 대역폭을 세팅하고 최솟값 세그먼트 트리로 만든다.
	static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[nodeIdx[s]];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	// 구간 업데이트를 위한 propagate함수, 업데이트 숫자를 tree 값에 빼주고, 값이 0 이하로 내려가면,
	// 즉, 대역폭 초과를 처리하려고 하면 isEnd를 true로 바꿔 끝낸다.
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] -= lazy[treeNode];
			
			if(tree[treeNode] < 0)
				isEnd = true;
			
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			
			lazy[treeNode] = 0;
		}
	}
	static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.min(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	public static void setSize(int nowNode, int parentNode, int [] size) {
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
		treeIdx[nowNode] = ++idx;
		nodeIdx[idx] = nowNode;
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heay일 경우, 기존 체인 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light일 경우 새로운 체인 시작
			{
				chainHead[next] = next;			// 새 체인의 head는 자기자신
				chainParent[next] = nowNode;	// 이전 체인 점프할 정보는 이전 노드
				setHLD(next, nowNode, level + 1);	// 새 체인에서는 level이 증가한다
			}
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N			= Integer.parseInt(st.nextToken());// 정점개수(2<=100,000)
		Q			= Integer.parseInt(st.nextToken());// 예약 개수(2<=100,000)
		arr			= new int[N + 1];
		tree		= new int[N * 4];
		lazy		= new int[N * 4];
		treeIdx		= new int[N + 1];
		nodeIdx		= new int[N + 1];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent = new int[N + 1];
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
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 각 노드의 한계 대역폭(0<=1,000,000,000)

		// 해당 함수에서 체인의 크기를 확인하여 무거운 노드를 인접리스트의 가장 첫번째로 옮김
		setSize(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;	// 1번 노드의 헤드는 자기자신
		// 해당 노드에서 chain정보를 입력
		setHLD(1, 0, 1);
		init(1, 1, N);	// 최솟값 세그먼트 트리 초기화
		
		int ans = 0;
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			// x, y, w 로 x,y노드에 w 대역폭이 증가함(w 1<=1,000,000,000)
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			while(!isEnd && chainHead[x] != chainHead[y])
			{
				if(chainLevel[x] > chainLevel[y]) {
					int tmp = x;
					x = y;
					y = tmp;
				}
				// 해당 체인의 범위에 w만큼 대역폭 감소 처리, 최솟값 세그먼트 트리이므로, 0미만이 되면 불가
				update(1, 1, N, treeIdx[chainHead[y]], treeIdx[y], w);
				y = chainParent[y];	// 다음 체인으로 바로 점프
			}
			// 같은 체인이 되었고, 대역폭 감소가 유효하면
			if(!isEnd)
			{
				// 트리 인덱스가 낮은게 왼쪽, 큰게 오른쪽 오도록 값 변경 후 update 연산
				if(treeIdx[x] > treeIdx[y])
				{
					int tmp = x;
					x = y;
					y = tmp;
				}
				update(1, 1, N, treeIdx[x], treeIdx[y], w);
			}
			// 모든 것이 유효하면 ans에  + 1
			if(!isEnd)
				++ans;
			else
				break;
		}
		System.out.print(ans);
	}
}
//3 2		// 정점개수(2<=100,000), 예약 개수(2<=100,000)
//1 2		// 연결된 두 정점 번호(v!=u)
//2 3
//5 5 5	// 각 노드의 한계 대역폭(0<=1,000,000,000)
//2 1 5	// x, y, w 로 x,y노드에 w 대역폭이 증가함(w 1<=1,000,000,000)
//3 2 5
//답 : 1