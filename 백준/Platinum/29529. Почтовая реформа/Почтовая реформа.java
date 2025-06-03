//https://www.acmicpc.net/problem/29529
//2초 1024MB
//3// 도시 수(1<=50,000)
//1 2 3// 도시의 초기 탑의 높이(1<=100,000,000)
//1 3// N-1 줄에 연결된 두노드가 주어짐
//2 3
//5// 질의 수(1<=100,000)
//? 1 2
//! 1 5// ! 명령어는 1번 노드의 높이가 5로 변경되 었다는 것을 의미
//? 2 3// ? 명령어는 2~3번 노드 사이에 밧줄 길이중 최대 길이
//! 3 2
//? 1 2
////? 명령어마다 답을 출력합니다.
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
	static int height[];
	static List<Integer> adList[];
	static HLD hld;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());// 도시 수(1<=50,000)
		height = new int[N + 1];
		adList = new ArrayList[N + 1];
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
			height[i] = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adList[a].add(b);
			adList[b].add(a);
		}
		
		hld = new HLD(N, height, adList);
		
		Q = Integer.parseInt(br.readLine());// 질의 수(1<=100,000)
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(c == '!')// ! 명령어는 x번 노드의 높이가 y로 변경되 었다는 것을 의미
			{
				hld.update(x, y);
				continue;
			}
			// ? 명령어는 x,y번 노드 사이에 각 밧줄 길이중 최대 길이
			sb.append(hld.query(x,y)).append('\n');
		}
		System.out.print(sb);
	}
	static class HLD{
		int N;
		int idx;
		int tree[];
		int reverse[];// 세그먼트 트리 인덱스 -> 노드 번호
		int segIdx[];// 노드번호 -> 세그먼트 트리 인덱스
		int height[];
		int chainLevel[];
		int chainParent[];
		int chainHeader[];
		List<Integer> adList[];
		HLD(int N, int height[], List<Integer> adList[]){
			this.N = N;
			this.height = height;
			this.tree = new int[N * 4];
			this.reverse = new int[N + 1];
			this.segIdx = new int[N + 1];
			this.chainLevel = new int[N + 1];
			this.chainParent = new int[N + 1];
			this.chainHeader = new int[N + 1];
			this.adList = adList;
			this.chainHeader[1] = 1;
			this.chainParent[1] = 1;
			// 무거운 자식노드, 가벼운 자식 노드를 구분하고 무거운 자식노드를 인접리스트에 가장 앞으로 옮김
			setChild(1, 0, new int[N + 1]);
			// HLD 알고리즘을 위한 체인 정보 및 세그 인덱스 정보 세팅
			setHLD(1, 0);
			// 최댓값 세그먼트 트리 초기화
			init(1, 1, N);
		}
		void update(int targetIdx, int value) {
			update(1, 1, N, segIdx[targetIdx], value);
		}
		void update(int treeNode, int s, int e, int targetIdx, int value) {
			if(s == e)
			{
				tree[treeNode] = value;
				return;
			}
			
			int mid = (s + e) >> 1;
			
			if(targetIdx <= mid)
				update(treeNode << 1, s, mid, targetIdx, value);
			else
				update(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
			
			tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode << 1 | 1]);
		}
		int query(int node1, int node2)
		{
			int max = 0;
			
			while(chainHeader[node1] != chainHeader[node2])
			{
				if(chainLevel[node1] > chainLevel[node2])
				{
					int tmp = node1;
					node1 = node2;
					node2 = tmp;
				}
				max = Math.max(max, query(1, 1, N, segIdx[chainHeader[node2]], segIdx[node2] ));
				node2 = chainParent[node2];
			}
			
			if(segIdx[node1] > segIdx[node2])
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			
			return Math.max(max, query(1, 1, N, segIdx[node1], segIdx[node2] ));
		}
		int query(int treeNode, int s, int e, int left, int right) {
			if(e < left || right < s)
				return 0;
			
			if(left <= s && e <= right)
				return tree[treeNode];
			
			int mid = (s + e) >> 1;
			
			return Math.max(
								query(treeNode << 1, s, mid, left, right),
								query(treeNode << 1 | 1, mid + 1, e, left, right)
							);
		}
		void init(int treeNode, int s, int e) {
			if(s == e)
			{
				tree[treeNode] = height[reverse[s]];
				return;
			}
			int mid = (s + e) >> 1;
			
			init(treeNode << 1, s, mid);
			init(treeNode << 1 | 1, mid + 1, e);
			
			tree[treeNode] = Math.max(tree[treeNode << 1], tree[treeNode << 1 | 1]);
		}
		void setChild(int nowNode, int parentNode, int size[])
		{
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
		void setHLD(int nowNode, int level)
		{
			segIdx[nowNode] = ++idx;
			reverse[idx] = nowNode;
			chainLevel[nowNode] = level;
			
			for(int i=0; i<adList[nowNode].size(); i++)
			{
				int next = adList[nowNode].get(i);
				
				if(chainHeader[next] != 0)
					continue;
				
				if(i == 0)
				{
					chainHeader[next] = chainHeader[nowNode];
					chainParent[next] = chainParent[nowNode];
					setHLD(next, level);
					continue;
				}
				chainHeader[next] = next;// 새로운 체인 시작시 헤더는 자기자신
				chainParent[next] = nowNode;// 새로운 체인 시작시 이전 체인 점프할 노드 저장
				setHLD(next, level + 1);// 새로운 체인 시작시 레벨 추가
			}
		}
	}
}