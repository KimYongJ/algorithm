//https://www.acmicpc.net/problem/11412
//4초 256MB
//4// 트리 정점 수 (1<=오십만)
//0 1// N-1개 줄에 연결된 노드 2개가 주어집니다.
//0 3
//1 2
//1 2 3 4// 노드의 화폐 초기값이 주어집니다.
//3// 질의 Q가 주어집니다.
//1000 1 1 1 0 1 0 0 2// 각질의 마다 9개의 공백으로 구분된 정수가 주어짐, k, 초기x값, 초기화폐단위y, A,B,C,D, u,v
//2 0 5 1 1 2 2 2 3
//1 3 7 999 999 999 999 1 3
//쿼리에 대한 답을 출력합니다.
//1006
//1027
//1031

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	
	static int N, Q;
	static int idx; // 노드번호 -> 세그먼트 트리 인덱스 번호 변수
	static long prevX;
	static long prevY;
	static int query[];
	static int segIdx[]; // 노드번호 -> 세그먼트 트리 인덱스 변환 
	static int chainLevel[];
	static int chainHeader[];
	static int chainParent[];
	static long fenwickTree[]; 
	static List<Integer> adList[];
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		N = in.nextInt();
		query = new int[9];
		segIdx = new int[N + 1];
		chainLevel = new int[N + 1];
		chainHeader = new int[N + 1];
		chainParent = new int[N + 1];
		fenwickTree = new long[N + 2];
		adList = new ArrayList[N + 1];
		chainHeader[1] = 1;
		
		for(int i=0; i<=N; i++)
			adList[i] = new ArrayList<>();
			
		for(int i=1; i<N; i++)
		{
			int a = in.nextInt() + 1;
			int b = in.nextInt() + 1;
			adList[a].add(b);
			adList[b].add(a);
		}

		// HLD를 위해 자식노드가 무거운것을 인접리스트의 가장 앞으로 옮김
		setChild(1, 0, new int[N + 1]);
		// HLD를 위해 실질 적인 체인 정보등을 세팅
		setHLD(1, 1);
		
		for(int i=1; i<=N; i++)
		{
			long num = in.nextInt();
			update(segIdx[i], num);
		}
		
		Q = in.nextInt();
		
		while(Q-->0)
		{
			for(int i=0; i<9; i++)
				query[i] = in.nextInt();
			
			for(int i=0; i<query[0]; i++)
				update(segIdx[(int)getX(i) + 1], getY(i));
			
			long res = find( query[7] + 1, query[8] + 1 );
			
			sb.append(res).append('\n');
		}
		System.out.print(sb);
	}
	static long find(int node1, int node2){
		long res = 0;
		
		while(chainHeader[node1] != chainHeader[node2])
		{
			if(chainLevel[node1] > chainLevel[node2])// 레벨이 더 깊은 것을 올리면서 연산
			{
				int tmp = node1;
				node1 = node2;
				node2 = tmp;
			}
			res += query(segIdx[node2]) - query(segIdx[chainHeader[node2]] - 1);
			node2 = chainParent[node2];
		}
		if(segIdx[node1] > segIdx[node2])
		{
			int tmp = node1;
			node1 = node2;
			node2 = tmp;
		}
		
		res += query(segIdx[node2]) - query(segIdx[node1] - 1);
		
		return res;
	}
	static void setHLD(int nowNode, int level){
		
		chainLevel[nowNode] = level;
		segIdx[nowNode] = ++idx;
		
		for(int i=0; i<adList[nowNode].size(); i++)
		{
			int next = adList[nowNode].get(i);
			
			if(chainHeader[next] != 0)
				continue;
				
			if(i == 0)// 무거운 자식 노드일 경우 체인정보 유지
			{
				chainParent[next] = chainParent[nowNode];
				chainHeader[next] = chainHeader[nowNode];
				setHLD(next, level);// 체인 유지시 레벨 유지
				continue;
			}
			
			chainParent[next] = nowNode;// 새로운 체인 시작시 이전 체인으로 바로 점프할 수 있는 노드 저장
			chainHeader[next] = next;	// 새로운 체인 시작시 헤더는 자기자신
			setHLD(next, level + 1);// 새로운 체인 시작시 레벨 + 1처리
		}
	}
	static void setChild(int nowNode, int parentNode, int size[]){
		size[nowNode] = 1;
		int heavyIdx = 0;
		int heavySize = 0;
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
	static long getX(int i){
		// x(i) = (A * x(i-1) + B) mod N
		return prevX = (i < 1 ? query[1] : ( query[3] * prevX + query[4] )) % N;
	}
	static long getY(int i){
		// y(i) = (C * y(i-1) + D) mod 1,000,000,007
		return prevY = i < 1 ? query[2] : ( query[5] * prevY + query[6] ) % 1_000_000_007L;
	}
	static void update(int idx, long value){
		while(idx <= N)
		{
			fenwickTree[idx] += value;
			idx += idx & -idx;
		}
	}
	static long query(int idx){
		long res = 0;
		while(idx > 0){
			res += fenwickTree[idx];
			idx -= idx & -idx;
		}
		return res;
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