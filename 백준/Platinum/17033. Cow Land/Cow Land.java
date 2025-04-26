//https://github.com/KimyongJ/algorithm
//https://www.acmicpc.net/problem/17033
//2초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main{

	static int N, Q;
	static int idx;				// 일반 노드 번호를 세그먼트 트리의 인덱스로 변경하기 위한 변수
	static int[] value;			// 초기에 입력되는 값을 입력받을 배열
	static int[] treeIdx;		// 노드번호 -> 세그먼트 트리 인덱스 치환
	static int[] tree;			// 세그먼트 트리
	static int[] chainHead;		// 해당 체인의 Head값을 담을 배열
	static int[] chainLevel;	// 해당 체인의 Level을 담을 배열
	static int[] chainParent;	// 해당 체인에서 다음 체인으로 점프할 노드번호
	static ArrayList<Integer>[] adNode;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N			= Integer.parseInt(st.nextToken());// 노드개수N(2<=100,000)
		Q			= Integer.parseInt(st.nextToken());// 질의 수Q(1<=100,000)
		value		= new int[N + 1];
		treeIdx		= new int[N + 1];
		tree		= new int[N * 4];
		chainHead	= new int[N + 1];
		chainLevel	= new int[N + 1];
		chainParent = new int[N + 1];
		adNode		= new ArrayList[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++)
		{
			adNode[i] = new ArrayList<>();
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adNode[n1].add(n2);
			adNode[n2].add(n1);
		}
		// 해당 함수에서 자식 서브트리 크기가 가장 큰 노드를 맨 앞으로 옮김
		setSize(1, 0, new int[N + 1]);
		
		chainHead[1] = 1;// 1번 노드의 헤드는 자기자신
		
		// HLD정보 세팅 및 세그먼트 트리 값 업데이트
		setHLD(1, 0, 1);
		
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			if(op == 1)// 1인 경우, i번노드를 j값으로 업데이트
			{
				update(1, 1, N, treeIdx[i], j);
				continue;
			}
			// 2인 경우, i, j 노드 경로사이의 XOR값을 출력
			
			int ans = 0;
			// 같은 체인이 될 때 까지 level을 올림
			while(chainHead[i] != chainHead[j])
			{
				if(chainLevel[i] > chainLevel[j])
				{
					int tmp = i;
					i = j;
					j = tmp;
				}
				
				ans ^= query(1, 1, N, treeIdx[chainHead[j]], treeIdx[j]);
				
				j = chainParent[j];
			}
			
			// 같은 체인에 속하면 그 안에서 다시 한번 해준다.
			if(treeIdx[i] > treeIdx[j])
			{
				int tmp = i;
				i = j;
				j = tmp;
			}
			
			ans ^= query(1, 1, N, treeIdx[i], treeIdx[j]);
			
			sb.append(ans).append('\n');
		}
		System.out.print(sb);
	}
	static int query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		int l = query(treeNode << 1, s, mid, left, right);
		int r = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return l ^ r;
	}
	static void update(int treeNode, int s, int e, int targetIdx, int value) {
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
		
		tree[treeNode] = tree[treeNode << 1] ^ tree[treeNode << 1 | 1];
	}
	static void setHLD(int nowNode, int parentNode, int level) {
		treeIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		
		for(int i=0; i<adNode[nowNode].size(); i++)
		{
			int next = adNode[nowNode].get(i);
			
			if(next == parentNode)
				continue;
			
			if(i == 0)	// heavy 노드일 경우 체인 정보 그대로 유지
			{
				chainHead[next] = chainHead[nowNode];
				chainParent[next] = chainParent[nowNode];
				setHLD(next, nowNode, level);
			}
			else		// light 노드일 경우 새 체인 생성
			{
				chainHead[next] = next;
				chainParent[next] = nowNode;
				setHLD(next, nowNode, level + 1);	// 새 체인일 경우 레벨 증가
			}
		}
		update(1, 1, N, treeIdx[nowNode], value[nowNode]);
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

//5 5				// 노드개수N(2<=100,000), 질의 수Q(1<=100,000)
//1 2 4 8 16		// 각 노드의 초기값 0<=1,000,000,000
//1 2				// 노드의 연결 관계
//1 3
//3 4
//3 5
//2 1 5				// 2인 경우, 1과 5노드 경로사이의 XOR값을 출력하라
//1 1 16			// 1인 경우, 1번노드를 16값으로 업데이트하라는 뜻
//2 3 5
//2 1 5
//2 1 3
//답
//21
//20
//4
//20