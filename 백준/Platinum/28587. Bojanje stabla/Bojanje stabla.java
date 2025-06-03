//https://www.acmicpc.net/problem/28587
//2초 1024MB
//5// 노드수(1<=100,000)
//1 2// N-1개 줄에 간선 연결 정보가 주어짐
//1 3
//2 4
//4 5
//8// 쿼리 수(1<=100,000)
//1 3 4// 1 A B : 이 변경이 T번째 변경이라면, A~B까지 경로에 있는 모든 노드 값을 T로 변경
//1 5 1
//1 3 5
//2 3// 2 X : 현재 X 노드  값 출력
//2 2
//1 1 3
//1 3 2
//2 3
//2번 질문에 대한 답 출력
//3
//3
//5

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N, Q;
	static HLD hld;
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 노드수(1<=100,000)
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
		
		hld = new HLD(N, adList);
		
		Q = Integer.parseInt(br.readLine());// 쿼리 수(1<=100,000)
		for(int q=0, cnt = 0; q<Q; q++)
		{
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			if(cmd == 2)// 2번 명령어일 때, 출력 후 다음 연산
			{
				sb.append( hld.query(x) ).append('\n');
				continue;
			}
			// 1번 명령어일 때, 1번 명령어 개수 카운팅 증가 및 x,y값 업데이트
			int y = Integer.parseInt(st.nextToken());
			hld.update( x, y, ++cnt );
			
		}
		System.out.print(sb);
	}
	static class HLD{
		int N;
		int idx;
		int segIdx[];
		int chainLevel[];
		int chainHeader[];
		int chainParent[];
		int tree[];
		int lazy[];
		List<Integer> adList[];
		HLD(int N, List<Integer> adList[]){
			this.N = N;
			this.adList = adList;
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.tree = new int[N * 4];
			this.lazy = new int[N * 4];
			this.chainHeader[1] = 1;
			this.chainParent[1] = 1;
			// HLD를 위한 무거운 자식노드, 가벼운 자식노드 구분 및 무거운 자식노드를 인접리스트의 앞으로 옮기는 작업
			setChild(1, 0, new int[N + 1]);
			// HLD를 위한 체인 정보 저장
			setHLD(1, 0);
		}
		void update(int node1, int node2, int cnt) {
			while(chainHeader[node1] != chainHeader[node2])
			{
				if(chainLevel[node1] > chainLevel[node2])
				{
					int tmp = node1;
					node1 = node2;
					node2 = tmp;
				}
				update(1, 1, N, segIdx[chainHeader[node2]], segIdx[node2], cnt);
				node2 = chainParent[node2];
			}
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			update(1, 1, N, segIdx[node1], segIdx[node2], cnt);
		}
		void update(int treeNode, int s, int e, int left, int right, int value) {
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
		}
		int query(int node) {
			return query(1, 1, N, segIdx[node]);
		}
		int query(int treeNode, int s, int e, int targetIdx) {
			propagate(treeNode, s, e);
			
			if(s == e)
				return tree[treeNode];
			
			int mid = (s + e) >> 1;
		
			if(targetIdx <= mid)
				return query(treeNode << 1, s, mid, targetIdx);
			
			return query(treeNode << 1 | 1, mid+1, e, targetIdx);
		}
		void propagate(int treeNode, int s, int e) {
			if(lazy[treeNode] == 0)
				return;
			
			tree[treeNode] = lazy[treeNode];
			
			if(s != e)
			{
				lazy[treeNode << 1] = lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
		void setChild(int nowNode, int parentNode, int size[]) {
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
				Collections.swap(adList[nowNode],0 , heavyIdx);
		}
		void setHLD(int nowNode, int level) {
			segIdx[nowNode] = ++idx;
			chainLevel[nowNode] = level;
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				int next = adList[nowNode].get(i);
				if(chainHeader[next] != 0)
					continue;
				if(i == 0)// 무거운 자식노드인 경우
				{
					chainHeader[next] = chainHeader[nowNode];
					chainParent[next] = chainParent[nowNode];
					setHLD(next, level);
					continue;
				}
				chainHeader[next] = next;// 새로운 체인 시작시 헤드는 자기자신
				chainParent[next] = nowNode;// 새로운 체인 시작시 이전 체인으로 점프할 노드 저장
				setHLD(next, level + 1);// 새로운 체인 시작시 level + 1처리
			}
		}
	}
}