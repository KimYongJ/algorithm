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
import java.util.Collections;
import java.util.List;

class Main{
	
	static final int MOD = 11092019;
	static int N;
	static int idx;// 세그먼트 트리 인덱스 변수
	static int segIdx[];// 노드번호 : 세그먼트 트리에 대응되는 인덱스
	static int chainLevel[];
	static int chainParent[];
	static int chainHeader[];
	static int heavyChildIdx[];
	static Node dummy;
	static Node tree[];
	static List<Integer> adlist[];
	static List<Input> input;
	
	public static void main(String[] args)throws Exception{
		Reader reader = new Reader();
		N = reader.nextInt();// 노드 개수(1<=1,000,000)
		segIdx = new int[N + 1];
		chainLevel = new int[N + 1];
		chainParent = new int[N + 1];
		chainHeader = new int[N + 1];
		heavyChildIdx = new int[N + 1];
		tree = new Node[N * 4];
		input = new ArrayList<>();
		adlist = new ArrayList[N + 1];
		
		chainHeader[1] = 1;
		dummy = new Node(0,0);
		
		for(int i=0; i<tree.length; i++)
			tree[i] = new Node(0,0);
		
		for(int i=1; i<=N; i++)
		{	
			// 1번 노드부터N번 노드까지 갖는 점수(0<=1,000,000)
			input.add(new Input(reader.nextInt(), i));
			adlist[i] = new ArrayList<>();
		}

		for(int i=2; i<=N; i++)
		{
			int parent = reader.nextInt();
			adlist[parent].add(i);
			adlist[i].add(parent);
		}
		
		Collections.sort(input);
		
		setSize(1, 0, new int[N + 1]);// hld를 위해 무거운 자식노드를 가장 왼쪽으로 옮김
		setHLD(1, 1);// 체인 값 세팅
		
		int maxLen = -1;
		int cnt = 0;
		
		for(int i=0; i<input.size(); i++)
		{
			Input in = input.get(i);
			
			Node result = new Node(0,0);
			int node = in.nodeNum;
			
			while(chainHeader[node] != 1)
			{
				result = merge( result, query(1, 1, N, segIdx[chainHeader[node]], segIdx[node]) );
				node = chainParent[node];
			}
			
			result = merge( result, query(1, 1, N, 1, segIdx[node]) );
			
			int maxLen2 = result.maxLen + 1;
			int cnt2 = result.cnt;
			
			if(cnt2 == 0)
				++cnt2;

            if (maxLen2 > maxLen)
            {
                maxLen = maxLen2;
                cnt = cnt2 % MOD;
            }
            else if (maxLen2 == maxLen)
                cnt = (cnt + cnt2) % MOD;
            
			update(1, 1, N, segIdx[in.nodeNum], maxLen2, cnt2);
		}
		
		System.out.print(maxLen + " " + cnt);
	}
	static void update(int treeNode, int s, int e, int targetIdx, int maxLen, int cnt) {
		if(e < targetIdx || targetIdx < s)
			return;
		
		if(s == e)
		{
			tree[treeNode].cnt = cnt % MOD;
			tree[treeNode].maxLen = maxLen;
			return;
		}
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, targetIdx, maxLen, cnt);
		update(treeNode << 1 | 1 , mid + 1, e, targetIdx, maxLen, cnt);
		
		tree[treeNode] = merge(tree[treeNode << 1], tree[treeNode << 1 | 1]);
	}
	static Node query(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return dummy;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		Node L = query(treeNode << 1, s, mid , left, right);
		Node R = query(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return merge(L, R);
	}
	static void setHLD(int nowNode, int level) {
		segIdx[nowNode] = ++idx;
		chainLevel[nowNode] = level;
		// 가장 무거운 노드 먼저 순회
		int next = adlist[nowNode].get(heavyChildIdx[nowNode]);
		if(chainHeader[next] == 0)
		{
			chainHeader[next] = chainHeader[nowNode];
			chainParent[next] = chainParent[nowNode];
			setHLD(next, level);// 기존 체인 유지시 레벨 그대로
		}
		// 이후 가벼운 노드들 순회
		for(int i=0; i<adlist[nowNode].size(); i++)
		{
			next = adlist[nowNode].get(i);
			
			if(chainHeader[next] != 0 || heavyChildIdx[nowNode] == i)
				continue;
			
			// 새로운 체인 시작시
			chainHeader[next] = next;// header는 자기 자신
			chainParent[next] = nowNode;// 이전 체인으로 바로 점프할 노드 세팅
			setHLD(next, level + 1);// 레벨 추가
		}
	}
	static void setSize(int nowNode, int parentNode, int [] size) {
		int heavyIdx = 0;
		int heavySize = 0;
		size[nowNode] = 1;
		
		for(int i=0; i<adlist[nowNode].size(); i++)
		{
			int next = adlist[nowNode].get(i);
			
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
		// 해당 노드의 가장 무거운 노드 입력
		heavyChildIdx[nowNode] = heavyIdx;
	}
	static Node merge(Node L, Node R) {
		if(L.maxLen > R.maxLen)
			return L;
		if(L.maxLen < R.maxLen)
			return R;
		
		return new Node(L.maxLen, (L.cnt + R.cnt) % MOD);
	}
	static class Input implements Comparable<Input>{
		
		int score;
		int nodeNum;
		
		Input(int s, int n){
			score = s;
			nodeNum = n;
		}
		@Override
		public int compareTo(Input o) {
			return score == o.score ? 
						nodeNum - o.nodeNum : score - o.score;
		}
	}
	static class Node{
		int maxLen;
		int cnt;
		Node(int m, int c){
			maxLen = m;
			cnt = c;
		}
	}
}

class Reader {
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