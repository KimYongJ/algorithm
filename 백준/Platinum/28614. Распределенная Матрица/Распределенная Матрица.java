//https://www.acmicpc.net/problem/28614
//1초 1024MB
//3 7// 노드 수(2<=2*10^6), 이벤트 수(2<=2*10^6)
//! 1 2// 첫번째 유형 쿼리
//! 1 3
//? 2 3
//- 3//두번째 유형 쿼리
//? 2 3
//+ 3// 세번째 유형 쿼리
//? 2 3// 네번째 유형 쿼리
//네번째 유형 쿼리마다 안정성 출력
//6
//-1
//14

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	
	static int idx;// 순차증가 인덱스
	static int segIdx[];// 트리노드 번호 -> 세그먼트 트리 인덱스 번호를 담을 배열
	static int chainLevel[];// 노드번호당 체인의 레벨을 담을 배열
	static int chainParent[];// 노드번호당 이전 체인으로 바로 점프하기 위해 이전 체인의 노드번호를 담을 배열
	static int chainHeader[];// 노드번호당 자기 체인의 head를 담을 배열
	
	static Node tree[];// 세그먼트 트리를 담을 배열
	static Node result[];// 최종 결과를 담을 배열
	static Node dummy = new Node(-1,-1,false);
	
	static List<Query> query;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 노드 수(2<=2*10^6)
		Q = Integer.parseInt(st.nextToken());// 이벤트 수(2<=2*10^6)
		segIdx = new int[N + 1];
		chainLevel = new int[N + 1];
		chainParent = new int[N + 1];
		chainHeader = new int[N + 1];
		chainParent[1] = 1;
		chainHeader[1] = 1;// 1번노드 헤드는 자기자신
		tree = new Node[N * 4];
		result = new Node[N * 4];
		query = new ArrayList<>();
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		for(int i=0; i<tree.length; i++)
		{
			tree[i] = new Node(0,0,false);
			result[i] = new Node(0,0,false);
		}
		
		for(int i=0; i<Q; i++)
		{
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = 0;
			
			if(cmd == '!' || cmd == '?')
			{
				y = Integer.parseInt(st.nextToken());
				if(cmd == '!')
				{
					adList[x].add(y);
					adList[y].add(x);
				}
			}
			query.add(new Query(cmd, x, y));
		}
		// HLD를 위해 인접리스트 중 무거운 자식노드를 맨 앞으로 옮깁니다.
		setChild(1, 0, new int[N + 1]);
		// HLD를 위한 필요 정보들 저장
		setHLD(1, 0);
		//tree에 해당 노드가 몇개 씩있는지 cnt를 초기 세팅한다. 추후에는 조회만하도록 하기 위한 조치
		init(1, 1, N);
		// 1번 노드 연결표시
		update(1, 1, N, segIdx[1], 0, true);
		
		StringBuilder sb = new StringBuilder();
		for(int time=1; time<=Q; time++)
		{
			Query q = query.get(time - 1);
			if(q.cmd == '!')
				update(1, 1, N, segIdx[q.y], time, true);
			else if(q.cmd == '-')
				update(1, 1, N, segIdx[q.x], time, false);
			else if(q.cmd == '+')
				update(1, 1, N, segIdx[q.x], time, true);
			else
			{
				// 1번 노드 -> x노드까지 확인
				Node res1 = find(q.x);
				// 2번 노드 -> y노드까지 확인
				Node res2 = find(q.y);
				// lca노드 -> 1번노드까지 확인
				Node res3 = find(getLca(q.x, q.y));
				
				if(res1.isConnected && res2.isConnected)
				{
					long num1 = cal(res1, time);
					long num2 = cal(res2, time);
					long num3 = cal(res3, time);
					sb.append(num1 + num2 - num3);
				}
				else
					sb.append(-1);
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
	static long cal(Node res, int time) {
		return res.cnt * time - res.lastModified;
	}
	static Node find(int node) {
		Node res = new Node(0,0,true);
		
		while(chainHeader[1] != chainHeader[node])
		{
			res.add( query(1, 1, N, segIdx[chainHeader[node]], segIdx[node] ));
			node = chainParent[node];
		}
		
		res.add( query(1, 1, N, 1, segIdx[node]) );
		
		return res;
	}
	static int getLca(int node1, int node2)
	{
		while(chainHeader[node1] != chainHeader[node2])
		{
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			node2 = chainParent[node2];
		}
		return segIdx[node1] > segIdx[node2] ? node2 : node1;
	}
	static Node query(int treeNode, int s, int e, int left, int right)
	{
		if(e < left || right < s)
			return dummy;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		
		int mid = (s + e) >> 1;
		
		Node l = query(treeNode << 1, s, mid, left, right);
		Node r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		if(l.cnt >= 0 && r.cnt >=0)
		{
			result[treeNode].cnt = l.cnt + r.cnt;
			result[treeNode].lastModified = l.lastModified + r.lastModified;
			result[treeNode].isConnected = l.isConnected && r.isConnected;
			return result[treeNode];
		}
		
		if(l.cnt >= 0)
			return l;
		
		return r;
	}
	static void update(int treeNode, int s, int e, int targetIdx, long lastModified, boolean isConnected)
	{
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			tree[treeNode].lastModified = lastModified;
			tree[treeNode].isConnected = isConnected;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, lastModified, isConnected);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, lastModified, isConnected);
		
		tree[treeNode].lastModified = tree[treeNode<<1].lastModified + tree[treeNode<<1|1].lastModified;
		tree[treeNode].isConnected = tree[treeNode<<1].isConnected && tree[treeNode<<1|1].isConnected;
	}
	static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode].cnt = 1;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode].cnt = tree[treeNode<<1].cnt + tree[treeNode<<1|1].cnt;
	}
	static void setHLD(int nowNode, int level) {
		
		segIdx[nowNode] = ++idx;
		
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int next = adList[nowNode].get(i);
			if(chainHeader[next] != 0)
				continue;
			
			if(i == 0)// 무거운 자식 노드일 경우 체인 정보 유지
			{
				chainHeader[next] = chainHeader[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, level);
				continue;
			}
			// 가벼운 자식노드는 새 체인 생성
			chainHeader[next] = next;// 헤드는 자기자신
			chainParent[next] = nowNode;// 이전체인 점프할 노드 저장
			setHLD(next, level + 1);// 레벨 증가
		}
	}
	
	static void setChild(int nowNode, int parentNode, int size[]) {
		int heavyIdx = 0;
		int heavySize = 0;
		size[nowNode] = 1;
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int next = adList[nowNode].get(i);
			if(next == parentNode)
				continue;
			
			setChild(next, nowNode, size);
			size[nowNode] += size[next];
			if(heavySize < size[next])
			{
				heavySize = size[next];
				heavyIdx = i;
			}
		}
		if(adList[nowNode].size() > 0)
			Collections.swap(adList[nowNode], 0, heavyIdx);
	}
	static class Node{
		long lastModified;
		long cnt;
		boolean isConnected;
		Node(int l, int c, boolean i){
			this.lastModified = l;
			this.cnt = c;
			this.isConnected = i;
		}
		void add(Node o) {
			this.lastModified += o.lastModified;
			this.cnt += o.cnt;
			this.isConnected = isConnected && o.isConnected;
		}
	}
	static class Query{
		char cmd;
		int x, y;
		Query(char cmd, int x, int y){
			this.cmd=cmd;
			this.x=x;
			this.y=y;
		}
	}
}