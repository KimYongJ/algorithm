//https://www.acmicpc.net/problem/17722
//2초 256MB
//3 7 // 노드 수(2<=100,000), 질의 수(1<=300,000)
//1 1 2// Q줄에 T,A,B가 주어지며 T는 건국 i년 후에 수립된 개선 계획의 실행 상태를 나타내며 T가 1일 때는 그 개선 계획이 실행되었음을, 2이면 포기되었음으로 의미, A,B는 두 노드를 의미함
//2 2 1
//2 2 3
//1 2 1
//2 1 2
//1 2 3
//2 1 3
//답 : 실행되지 않고 포기된 개선 계획 각각에 대해, 그 개선계획이 실행되었다면 그 개선 계획에서 포장하게 될 도로의 수를 출력하며, 그 개선 계획 실행시 새로운 도로가 건설되는 경우 -1을 출력합니다.
//1
//-1
//0
//1
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
class Main{
	
	static int N, Q;
	static List<Query> query;
	static List<Integer> adList[];
	static UnionFind uf;// 유니온 파인드에 사용
	static HLD hld;// HLD + lazy seg
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 노드 수(2<=100,000)
		Q = Integer.parseInt(st.nextToken());// 질의 수(1<=300,000)
		uf = new UnionFind(N);
		query = new ArrayList<>();
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			// 명령어가 1이면서 + 두 노드가 새롭게 연결이 된것이면 간선으로 추가, 두 노드가 기존 연결되어 있었다면, 중복으로 간선 추가 할 필요 없기 때문
			if(T == 1 && uf.union(A, B))
			{
				adList[A].add(B);
				adList[B].add(A);
			}
			// 모든 쿼리는 추후 다시 연산을 위해 저장
			query.add(new Query(T, A, B));
		}
		// HLD를 위한 HLD클래스 세팅
		hld = new HLD(N, adList);
		// 분리집합을 다시 써야 하기 때문에 클래스 초기화
		uf.clear();
		
		StringBuilder sb = new StringBuilder();
		for(Query q : query)
		{
			int parent1 = uf.find(q.n1);// 분리집합을 통해 두 노드가 연결되어있는지를 확인하기 위해 가져욤
			int parent2 = uf.find(q.n2);// 분리집합을 통해 두 노드가 연결되어있는지를 확인하기 위해 가져욤
			
			if(q.op == 1)// 1번 명령이라면,
			{
				if(parent1 == parent2)// 이미 연결되어 있다면 해당 경로의 모든 도로를 포장시키므로 0으로 업데이트, 0이 포장됬다는 의미
					hld.updateOrQuery(q.op, q.n1, q.n2);
				else// 연결되어있지 않다면 연결 처리
					uf.union(parent1, parent2);
				continue;
			}
			
			// 명령어 2인경우, 해당 구간의 값을 출력한다.
			
			if(parent1 != parent2)// 연결되어 있지 않다면 -1 출력
				sb.append(-1).append('\n');
			else// 연결되어 있다면, 두 노드 사이에 비포장 도로 개수 출력
				sb.append(hld.updateOrQuery(q.op, q.n1, q.n2)).append('\n');
		}
		System.out.print(sb);
	}
	static class HLD{
		int N;
		int idx;
		int tree[];// 세그먼트 트리
		int lazy[];// 레이지 
		int size[];// HLD를 위해 자식노드의 크기를 저장할 배열
		int segIdx[];// 노드번호 -> 세그먼트 인덱스 번호로 치환할 정보를 담을 배열
		int chainLevel[];// 각 노드당 체인의 레벨을 담을 배열
		int chainParent[];// 각 노드당 자기가 속한 체인의 이전 체인으로 바로 점프할 수 있도록 이전 체인의 노드를 담을 배열
		int chainHeader[];// 각 노드당 자기가 속한 체인의 header를 담을 배열 
		boolean visit[];// dfs 함수 사용시 중복방문을 제거하기 위한 방문 배열
		List<Integer> adList[];// 인접 리스트
		
		HLD(int N, List<Integer> adList[]){
			this.N = N;
			this.adList = adList;
			this.tree = new int[N * 4];
			this.lazy = new int[N * 4];
			this.size = new int[N + 1];
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.visit = new boolean[N + 1];
			// 느리게 갱신되는 세그먼트 트리를 위해 -1로 초기화
			Arrays.fill(lazy, -1);
			// HLD를 위해 인접리스트 중 무거운 자식노드를 앞쪽으로 옮김, 주어지는 간선이 여러개의 트리이기 때문에 반복문으로 실행
			for(int i=1; i<=N; i++)
			{
				if(!visit[i])
				{
					visit[i] = true;
					setChild(i);
				}
			}
			// HLD를 위한 실질적인 값 세팅, 주어지는 간선 정보가 여러개의 트리일 수 있어 노드마다 전부 순회
			for(int i=1; i<=N; i++)
			{
				if(chainHeader[i] == 0)// header값이 없는 것만 탐색, 있으면 이미 탐색한 것을 의미
				{
					chainHeader[i] = i;// 헤더는 자기자신
					chainParent[i] = i;// 자기가 1번이라 이전 체인 점프도 자기자신
					setHLD(i, 0);// 해당 함수에서 비포장 도로로 업데이트도 같이함(비포장 도로는 1로 저장)
				}
			}
		}
		int updateOrQuery(int op, int node1, int node2) {
			int res = 0;
			// node1 부터 node2까지 도로 값을 업데이트
			while(chainHeader[node1] != chainHeader[node2])
			{
				if(chainLevel[node1] > chainLevel[node2])
				{
					int tmp = node1;
					node1 = node2;
					node2 = tmp;
				}
				// 명령어가 1이냐 2냐에 따라 업데이트인지, 쿼리인지 달라짐
				if(op == 2)
					res += query(1, 1, N, segIdx[chainHeader[node2]], segIdx[node2]);
				else
					update(1, 1, N, segIdx[chainHeader[node2]], segIdx[node2], 0);

				node2 = chainParent[node2];
			}
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			// 명령어가 1이냐 2냐에 따라 업데이트인지, 쿼리인지 달라짐
			if(op == 2)
				res += query(1, 1, N, segIdx[node1]+1, segIdx[node2]);//간선 정보는 자식노드에 저장되어 있기 때문에 LCA에 + 1을 하여 저장한다.
			else
				update(1, 1, N, segIdx[node1]+1, segIdx[node2], 0);//간선 정보는 자식노드에 저장되어 있기 때문에 LCA에 + 1을 하여 쿼리한다.
			
			return res;
		}
		int query(int treeNode, int s, int e, int left, int right) {
			
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return 0;
			
			if(left <= s && e <= right)
				return tree[treeNode];
			
			int mid = (s + e) >> 1;
			
			return query(treeNode << 1, s, mid, left, right)
					+ query(treeNode << 1 | 1, mid + 1, e, left, right);
		}
		void update(int treeNode, int s, int e, int left, int right, int value) {
			
			propagate(treeNode, s, e);
			
			if(e < left || right < s)
				return;
			
			if(left <= s && e <= right)
			{
				lazy[treeNode] = value;
				propagate(treeNode, s, e);
				return;
			}
			
			int mid = (s + e) >> 1;
			
			update(treeNode << 1, s, mid, left, right, value);
			update(treeNode << 1 | 1, mid + 1, e, left, right, value);
			// 구간합 갱신
			tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
		}
		void propagate(int treeNode, int s, int e) {
			if(lazy[treeNode] == -1)
				return;
			// 0을 전파함
			tree[treeNode] = lazy[treeNode] * (e - s + 1);
			
			if(s != e)// 리프가 아니면 해당 값 지속 전파
			{
				lazy[treeNode << 1] = lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			
			lazy[treeNode] = -1;
		}
		void setHLD(int nowNode, int level) {
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				int next = adList[nowNode].get(i);
				
				if(chainHeader[next] != 0)// 이미 연산하였으면 스킵
					continue;
				
				if(i == 0)// 무거운 자식 노드이면, 체인 정보 유지
				{
					chainHeader[next] = chainHeader[nowNode];
					chainParent[next] = chainParent[nowNode];
					setHLD(next, level);
				}
				else
				{
					// 가벼운 자식노드이면 새로운 체인 시작
					chainHeader[next] = next;
					chainParent[next] = nowNode;
					setHLD(next, level + 1);
				}
				//간선 정보는 자식노드에 저장되어 있기 때문에 자식노드에 1을 업데이트, 1은 비포장 도로를 의미
				update(1, 1, N, segIdx[next], segIdx[next], 1);
			}
		}
		void setChild(int nowNode) {
			int heavySize = 0;
			int heavyIdx = 0;
			size[nowNode] = 1;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				int next = adList[nowNode].get(i);
				
				if(visit[next])// 이미 방문한 노드면 스킵
					continue;
				
				visit[next] = true;
				
				setChild(next);
				
				size[nowNode] += size[next];
				
				if(heavySize < size[next])
				{
					heavySize = size[next];
					heavyIdx = i;
				}
			}
			// 무거운 자식노드를 가장 앞으로 옮긴다.
			if(adList[nowNode].size() > 0)
				Collections.swap(adList[nowNode], 0, heavyIdx);
		}
	}
	static class UnionFind{
		int N;
		int parent[];
		UnionFind(int N){
			this.N = N;
			this.parent = new int[N + 1];
			clear();
		}
		boolean union(int n1, int n2) {
			int parent1 = find(n1);
			int parent2 = find(n2);
			
			if(parent1 == parent2)// 기 연결이면 false 리턴
				return false;
			
			if(parent[parent1] < parent[parent2])
				parent[parent2] = parent1;
			else
				parent[parent1] = parent2;
			
			return true;
		}
		int find(int node) {
			if(parent[node] == node)
				return node;
			return parent[node] = find(parent[node]);
		}
		void clear() {
			for(int i=1; i<=N; i++)
				parent[i] = i;
		}
	}
	static class Query{
		int op, n1, n2;
		Query(int op, int n1, int n2){
			this.op = op;
			this.n1 = n1;
			this.n2 = n2;
		}
	}
}
