//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/16453
//2초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static int idx;
	static int [] treeIdx;
	static int [] tree;
	static int [] lazy;
	static int [] chainHead;
	static int [] chainLevel;
	static int [] chainParent;
	static ArrayList<Integer>[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder	sb = new StringBuilder();
		N			= Integer.parseInt(st.nextToken());// 노드 개수(5<=100,000)
		Q			= Integer.parseInt(st.nextToken());// 질의 수(1<=20,000)
		treeIdx		= new int[N + 1];
		tree		= new int[N * 4];
		lazy		= new int[N * 4];
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
		// 해당 함수에서 자식 노드가 큰것을 인접리스트에 가장 앞으로 옮김
		setSize(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;// 1번 노드의 헤드는 자기자신
		// chain정보 입력
		setHLD(1, 0, 1);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			// (A,B), (C,D) 노드가 주어짐
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			rangeUpdate(a, b, 1);	// a-b구간을 탐색하며 해당 구간에 1을 업데이트
			
			int ans = getAns(c, d);	// c-d구간을 탐색하며 구간 sum을 구해서 1인 노드들의 합을 구해옴
			
			rangeUpdate(a, b, -1);	// a-b구간을 탐색하며 해당 구간에 -1로 업데이트
			
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
	static int getAns(int n1, int n2) {
		int ans = 0;
		// chainHead가 같아질 때 까지 레벨을 낮춤
		while(chainHead[n1] != chainHead[n2])
		{
			if(chainLevel[n1] > chainLevel[n2])
			{
				int tmp = n1;
				n1 = n2;
				n2 = tmp;
			}
			ans += query(1, 1, N, treeIdx[chainHead[n2]], treeIdx[n2]);
			n2 = chainParent[n2];
		}
		// 같은 헤드일 때 마지막 연산
		if(treeIdx[n1] > treeIdx[n2])
		{
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		ans += query(1, 1, N, treeIdx[n1], treeIdx[n2]);
		
		return ans;
	}
	static void rangeUpdate(int n1, int n2, int value) {
		// chainHead가 같아질 때 까지 레벨을 낮춤
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
		// 같은 헤드일 때 마지막 연산
		if(treeIdx[n1] > treeIdx[n2]) {
			int tmp = n1;
			n1 = n2;
			n2 = tmp;
		}
		update(1, 1, N, treeIdx[n1], treeIdx[n2], value);
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
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	static int query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode << 1, s, mid, left, right);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return l + r;
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
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
	static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heavy 노드일 경우 체인 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light일 경우 새 체인 생성
			{
				chainHead[next] = next;		// 체인의 헤드는 자기자신
				chainParent[next] = nowNode;// 이전 체인으로 바로 점프를 위한 이전 노드 값 삽입
				setHLD(next, nowNode, level + 1);// 새 체인시 레벨을 올린다.
			}
		}
	}
}
//10 4		// 노드 개수(5<=100,000), 질의 수(1<=20,000)
//1 4		// 이어진 두 노드 번호
//4 5
//3 4
//3 2
//7 3
//6 7
//7 8
//10 8
//8 9
//6 10 2 5	// (A,B), (C,D) 노드가 주어짐
//1 9 5 10
//9 10 2 1
//5 10 2 9
////각 질의마다 두 노드가 서로 공유하는 간선을 출력
//0
//4
//0
//3