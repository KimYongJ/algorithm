//https://www.acmicpc.net/problem/23835
//1초 512MB
//5 // 방의 개수 N(1<=1,000)
//1 2 // 연결된 두방 번호
//2 3
//3 4
//2 5
//5 // 질의 수(1<=1,000)
//1 3 5 // 1로 시작하면 두 방 사이에 우유를 놓는다.
//1 4 5
//1 1 2
//2 2// 2로 시작하면 해당 노드에 지금까지 배달한 우유의 총 개수를 계산한다.
//2 5
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static int s, e;
	static int milk[];
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 방의 개수 N(1<=1,000)
		milk = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		Q = Integer.parseInt(br.readLine());// 질의 수(1<=1,000)
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			if(cmd == 2)
			{
				sb.append(milk[Integer.parseInt(st.nextToken())])
					.append('\n');
				continue;
			}
			
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			dfs(s, 0, 0);
		}
		
		System.out.print(sb);
	}
	static boolean dfs(int now, int parent, int depth) {
		if(now == e)
			return true;
		
		for(int next : adList[now])
		{
			if(next != parent)
			{
				milk[next] += depth + 1;
				
				if(dfs(next, now, depth + 1))
					return true;
				
				milk[next] -= depth + 1;
			}
		}
		return false;
	}
}
// 이하 HLD 및 세그먼트 트리로 푼 답

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.StringTokenizer;
//
//class Main{
//	
//	static int N, Q;
//	static int idx;// 세그먼트 트리 인덱스를 지정할 순차 증가 변수
//	static int segIdx[];// 노드 번호 -> 세그먼트 트리 노드번호로 변경시 사용
//	static int chainLevel[];// 노드당 체인의 레벨을 담음
//	static int chainParent[];// 노드당, 이전 체인으로 바로 점프할 수 있도록 이전 체인 노드번호를 담음 
//	static int chainHeader[];// 노드당, 자기 체인의 head 정보를 담음
//	static long tree[];// 세그먼트 트리
//	static long lazy[];// 레이지 전파
//	static ArrayDeque<Node> forward;
//	static ArrayDeque<Node> reverse;
//	static List<Integer> adList[];// 인접 노드
//	
//	public static void main(String[] args)throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		StringTokenizer st;
//		N = Integer.parseInt(br.readLine());// 방의 개수(1≤100,000)
//		tree = new long[N * 4];
//		lazy = new long[N * 4];
//		segIdx = new int[N + 1];
//		chainLevel = new int[N + 1];
//		chainParent = new int[N + 1];
//		chainHeader = new int[N + 1];
//		chainHeader[1] = 1;// 1번 노드의 헤더는 자기자신
//		adList = new ArrayList[N + 1];
//		forward = new ArrayDeque<>();
//		reverse = new ArrayDeque<>();
//		
//		for(int i=0; i<=N; i++)
//			adList[i] = new ArrayList<>();
//		
//		for(int i=1; i<N; i++)
//		{
//			st = new StringTokenizer(br.readLine());
//			int n1 = Integer.parseInt(st.nextToken());
//			int n2 = Integer.parseInt(st.nextToken());
//			adList[n1].add(n2);
//			adList[n2].add(n1);
//		}
//		// HLD를 위해 자식 노드를 인접리스트에서 맨 앞으로 옮긴다.
//		setChild(1, 0, new int[N + 1]);
//		// HLD 체인 정보 세팅 및 노드번호 -> 세그먼트 트리 인덱스번호로 바인딩할 변수 세팅
//		setHLD(1, 1);
//		
//		Q = Integer.parseInt(br.readLine());// 방의 개수(1≤100,000)
//		
//		while(Q-->0)
//		{
//			st = new StringTokenizer(br.readLine());
//			int flag = Integer.parseInt(st.nextToken());
//			
//			if(flag == 2)// 특정 노드 값 출력
//			{
//				sb.append( 
//							query(1, 1, N, 1, segIdx[Integer.parseInt(st.nextToken())])
//						).append('\n');
//				continue;
//			}
//			// 특정 값 업데이트시
//			int startNode = Integer.parseInt(st.nextToken());
//			int endNode = Integer.parseInt(st.nextToken());
//			
//			input(startNode, endNode);
//		}
//		System.out.print(sb);
//	}
//	static void input(int startNode, int endNode)
//	{
//		forward.clear();
//		reverse.clear();
//		
//		int s,e;
//		while(chainHeader[startNode] != chainHeader[endNode])
//		{
//			// startNode가 레벨이 더 깊을 때, 올리면서 정방향에 담는다.
//			if(chainLevel[startNode] > chainLevel[endNode])
//			{
//				s = segIdx[chainHeader[startNode]];
//				e = segIdx[startNode];
//				forward.addLast(new Node(s,e));
//				startNode = chainParent[startNode];
//				continue;
//			}
//			// endNode가 더 레벨이 깊을 때 올리면서 역방향에 담는다.
//			s = segIdx[chainHeader[endNode]];
//			e = segIdx[endNode];
//			reverse.addFirst(new Node(s,e));
//			endNode = chainParent[endNode];
//		}
//		
//		if(segIdx[startNode] > segIdx[endNode])// endNode가 LCA로 segIdx가 더작다면 정방향에 넣음
//			forward.addLast(new Node(segIdx[endNode], segIdx[startNode]));
//		else// startNode가 LCA면 역방향에 넣는다.
//			reverse.addFirst(new Node(segIdx[startNode], segIdx[endNode]));
//		
//		long value = 0;
//		// 시작에서 lca까지 올라갈 때,
//		while(!forward.isEmpty())
//		{
//			// 정방향은, 시작에 큰 값을 넣고, end로 갈 수록 값이 감소한다. 
//			Node now = forward.poll();
//
//			int nodeCnt = now.e - now.s + 1;
//			// s값이 더커야하고, e가 작아져야 하므로, s에 큰 값을 넣어놓고, 감소 순열을 입력하게 해야함
//			value += nodeCnt;
//			
//			update(1, 1, N, now.s, now.e, -1);// 감소순열 세팅
//			update(1, 1, N, now.s, now.s, value);// s에다 큰값을 넣어놓는다.
//			update(1, 1, N, now.e + 1, now.e + 1,  nodeCnt - value);// 마지막 인덱스 + 1 장소에 종료 마킹
//		}
//		// lac에서 end까지 갈 때,
//		while(!reverse.isEmpty())
//		{
//			// 역방향은, 증가 순열이 되면된다.
//			Node now = reverse.poll();
//			
//			int nodeCnt = now.e - now.s + 1;
//			// lca에서 end까지 갈 때는 s가 작고 e가 커지는 방향으로 간다. 증가 순열을 입력하게 해야함
//			update(1, 1, N, now.s, now.e, 1);// 증가순열 세팅
//			update(1, 1, N, now.s, now.s, value - 1);// 초기값 세팅
//			update(1, 1, N, now.e + 1, now.e + 1, -value - nodeCnt + 1);// 마지막 인덱스 + 1 장소에 종료 마킹
//			
//			value += nodeCnt;
//		}
//	}
//	static void update(int treeNode, int s, int e, int left, int right, long value) {
//		propagate(treeNode, s, e);
//		
//		if(e < left || right < s)
//			return;
//		
//		if(left <= s && e <= right)
//		{
//			lazy[treeNode] = value;
//			propagate(treeNode, s, e);
//			return;
//		}
//		
//		int mid = (s + e) >> 1;
//		
//		update(treeNode << 1, s, mid, left, right, value);
//		update(treeNode << 1 | 1, mid + 1 , e, left, right, value);
//		
//		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
//	}
//	static long query(int treeNode, int s, int e, int left, int right) {
//		propagate(treeNode, s, e);
//		
//		if(e < left || right < s)
//			return 0;
//		
//		if(left <= s && e <= right)
//			return tree[treeNode];
//		
//		int mid = (s + e) >> 1;
//		return query(treeNode << 1, s, mid, left, right)
//				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
//	}
//	static void propagate(int treeNode, int s, int e) {
//		if(lazy[treeNode] == 0)
//			return;
//		// 단순 누적합이기 때문에 lazy에 있는 값만큼 트리에 누적합 저장
//		tree[treeNode] += lazy[treeNode] * (e - s + 1);
//		if(s != e)
//		{
//			lazy[treeNode << 1] += lazy[treeNode];
//			lazy[treeNode << 1 | 1] += lazy[treeNode];
//		}
//		
//		lazy[treeNode] = 0;
//	}
//	static void setHLD(int nowNode, int level) {
//		segIdx[nowNode] = ++idx;
//		chainLevel[nowNode] = level;
//		
//		for(int i=0; i<adList[nowNode].size(); i++)
//		{
//			int next = adList[nowNode].get(i);
//			// 이미 방문한 노드면 스킵
//			if(chainHeader[next] != 0)
//				continue;
//			if(i == 0)// 무거운 자식 노드일 경우 체인 정보 유지
//			{
//				chainHeader[next] = chainHeader[nowNode];
//				chainParent[next] = chainParent[nowNode];
//				setHLD(next, level);
//				continue;
//			}
//			// 가벼운 노드는 새로운 체인 시작
//			chainHeader[next] = next;// 새로운 체인의 헤더는 자기 자신
//			chainParent[next] = nowNode;// 이전 체인으로 점프하기 위해 이전 체인 노드번호 저장
//			setHLD(next, level + 1);// 새로운 체인 시작시 레벨 + 1
//		}
//	}
//	static void setChild(int nowNode, int parentNode, int size[]) {
//		int heavyIdx = 0;
//		int heavySize = 0;
//		size[nowNode] += 1;
//		
//		for(int i=0; i<adList[nowNode].size(); i++)
//		{
//			int next = adList[nowNode].get(i);
//			
//			if(next == parentNode)
//				continue;
//			
//			setChild(next, nowNode, size);
//			
//			size[nowNode] += size[next];
//			if(heavySize < size[next])
//			{
//				heavySize = size[next];
//				heavyIdx = i;				
//			}
//		}
//		if(adList[nowNode].size() > 0)
//			Collections.swap(adList[nowNode], 0, heavyIdx);
//	}
//	static class Node{
//		int s, e;
//		Node(int s, int e){
//			this.s=s;
//			this.e=e;
//		}
//	}
//}