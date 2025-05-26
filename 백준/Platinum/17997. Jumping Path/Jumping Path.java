//https://www.acmicpc.net/problem/17997
//10초 512MB
//4 // 노드 개수(1<=1,000,000)
//1// 1번 노드부터N번 노드까지 갖는 점수(0<=1,000,000)
//5
//3
//6
//1// 2번노드부터 해당 노드의 부모 노드가 주어짐
//2
//3
//출력 : 점수가 비 감소하는 가장 긴 점프 경로 길이, 그 길이를 가지면서 정점 레이블이 비감소하는 점프 경로 개수 출력
//답 : 3 2

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Main{
	
	static final int MOD = 11_092_019;
	static int N;
	static int idx;// 세그먼트 트리 인덱스 변수
	static int segIdx[];// 노드번호에 따른 세그먼트 트리에 대응되는 인덱스
	static int heavyIdx[];// HLD를 위해 인접 리스트 중 가장 무거운 자식노드의 인덱스
	static int chainLevel[];// HLD를 위한 체인 레벨 저장
	static int chainParent[];// HLD를 위한 바로 이전 체인으로 점프하기 위한 정보를 저장
	static int chainHeader[];// HLD를 위한 같은 체인의 head값 저장
	static Node tree[];
	static Node dummy;
	static List<Integer> adList[];
	static PriorityQueue<Input> input;
	
	public static void main(String[] args)throws Exception{
		Reader reader = new Reader();
		N = reader.nextInt();// 노드 개수(1<=1,000,000)
		segIdx = new int[N + 1];
		heavyIdx = new int[N + 1];
		chainLevel = new int[N + 1];
		chainParent = new int[N + 1];
		chainHeader = new int[N + 1];
		adList = new ArrayList[N + 1];
		input = new PriorityQueue<>();
		tree = new Node[N * 4];
		dummy = new Node(0,0);
		chainHeader[1] = 1;
		
		for(int i=0; i<tree.length; i++)
			tree[i] = new Node(0,0);
		
		for(int node=1; node<=N; node++)
		{
			// 1번 노드부터N번 노드까지 갖는 점수(0<=1,000,000)
			int score = reader.nextInt();
			input.add(new Input(score, node));
			adList[node] = new ArrayList<>();
		}
		
		for(int child = 2; child<=N; child++)
		{
			int parent = reader.nextInt();
			adList[parent].add(child);
			adList[child].add(parent);
		}
		
		setChild(1, 0, new int[N + 1]);// HLD를 위해 자식노드 크기 책정 및 가장 무거운 자식노드 마킹
		setHLD(1, 1);// HLD를 위한 체인 정보 마킹
		
		int maxlen = 0;
		int cnt = 0;
		
		while(!input.isEmpty())
		{
			Input now = input.poll();
			int node = now.nodeNum;
			Node result = new Node(0,0);
			
			// 루트 체인까지 올라오면서 가장 길면서, 길이가 같다면 개수를 더해나감
			while(chainHeader[node] != 1)
			{
				result = result.add( query(1, 1, N, segIdx[chainHeader[node]], segIdx[node]) );
				node = chainParent[node];
			}
			// 루트 체인까지 마지막 연산
			result = result.add( query(1, 1, N, 1, segIdx[node]) );
			
			int maxlen2 = result.len + 1;// 쿼리로 구한 최대 길이
			int cnt2 = result.cnt;// 쿼리로 구한 경로 개수
			// 경로개수가 0이면 + 1
			if(cnt2 == 0)
				++cnt2;
			// 최대 길이 갱신시 그대로 대입
			if(maxlen < maxlen2)
			{
				maxlen = maxlen2;
				cnt = cnt2;
			}
			else if(maxlen == maxlen2)// 최대길이가 같다면 경로 개수 플러스
				cnt = (cnt + cnt2) % MOD;
			
			// 구한 값을 update하여 다음 연산에 사용하도록 함
			update(1, 1, N, segIdx[now.nodeNum], maxlen2, cnt2);
		}
		
		System.out.print(maxlen + " " + cnt);
	}
	static void update(int treeNode, int s, int e, int targetIdx, int len, int cnt) {
		if(e < targetIdx || targetIdx < s)
			return;
		if(s == e)
		{
			tree[treeNode].len = len;
			tree[treeNode].cnt = cnt;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, len, cnt);
		update(treeNode << 1 | 1, mid + 1, e, targetIdx, len, cnt);
		
		tree[treeNode] = tree[treeNode << 1].add( tree[treeNode << 1 | 1] );
	}
	static Node query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return dummy;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		Node L = query(treeNode << 1, s, mid, left, right);
		Node R = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return L.add(R);
	}
	static void setHLD(int nowNode, int level) {
		segIdx[nowNode] = ++idx;// 트리 노드를 세그먼트 트리 인덱스로 치환
		chainLevel[nowNode] = level;
		
		// 무거운 노드 부터 탐색한다.
		int heavyChildIdx = heavyIdx[nowNode];
		int next = adList[nowNode].get(heavyChildIdx);
		// 탐색한 적이 없다면 탐색
		if(chainHeader[next] == 0)
		{
			chainHeader[next] = chainHeader[nowNode];
			chainParent[next] = chainParent[nowNode];
			setHLD(next, level);
		}
		
		// 가벼운 노드들 탐색
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			next = adList[nowNode].get(i);
			// 무거운 노드이거나, 탐색한 킵
			if(i == heavyChildIdx || chainHeader[next] != 0)
				continue;
			
			chainHeader[next] = next;// 새로운 체인 시작시 체인의 헤더는 자기자신
			chainParent[next] = nowNode;// 새로운 체인 시작시 이전 체인 바로 점프할 노드 세팅
			setHLD(next, level + 1);// 새로운 체인 시작시 레벨 증가
		}
	}
	static void setChild(int nowNode, int parentNode, int size[]) {
		int heavyChildIdx = 0;
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
				heavyChildIdx = i;
			}
		}
		// 해당 노드의 자식 노드 중 가장 무거운 것 마킹
		heavyIdx[nowNode] = heavyChildIdx;
	}
	static class Input implements Comparable<Input>{
		int score, nodeNum;
		Input(int s, int n){
			score = s;
			nodeNum = n;
		}
		@Override
		public int compareTo(Input o) {
			if(score != o.score)
				return score - o.score;
			
			return nodeNum - o.nodeNum;
		}
	}
	static class Node{
		int len, cnt;
		Node(int l, int c){
			len = l;
			cnt = c;
		}
		public Node add(Node o) {
			if(len < o.len)
				return o;
			if(len > o.len)
				return this;
			
			return new Node(
						len,
						(cnt + o.cnt) % MOD
					);
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;

	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }

	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}
