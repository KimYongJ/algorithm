// https://github.com/KimYongJ/algorithm


class Main{
	public static void main(String[] args)throws Exception{new Main().solution();}
	
	int N, X, p, l, r, parentNode;
	int rlevel, width, minX[], maxX[];
	StringBuilder sb;
    boolean parent[];
	Node nlist[];
	
	void solution()throws Exception {
		sb	                = new StringBuilder();
		X					= 1;
		N 					= read();
		minX 				= new int[N+1];				// 레벨 별 가장 작은 X값
		maxX 				= new int[N+1];				// 레벨 별 가장 큰 X값
		nlist 				= new Node[N+1]; 			// 노드 정보를 담을 리스트 생성
		parent 				= new boolean[N+1];			// false인 것이 부모 노드이다. 
		
		for(int i=0; i<N; i++) 
		{
			p = read();									// 부모노드
			l = read();									// 왼쪽노드	
			r = read();									// 오른쪽노드
			nlist[p] = new Node(l,r);					// 부모노드의 자식정보 셋팅
			if(l >0 ) parent[l] = true;					// 루트노드가 아닌 것들 체크
			if(r >0 ) parent[r] = true;					// 루트노드가 아닌 것들 체크 
		}
		
		for(int i=1; i<=N; i++) 
		{
			minX[i] = 10001;							// 레벨 별 최소 값을 담을 배열을 높은 값으로 초기화 
			if(!parent[i]) 								// 루트 노드를 찾는다.
				parentNode = i;
		}
		
		DFS(parentNode, 1);								// 순서 : 부모노드 , level
		// 결과 출력
		for(int i=1; i<=N; i++) 
		{
			if(minX[i] == 10001) break;
			if(width < maxX[i]-minX[i] +1) 
			{
				width = maxX[i]-minX[i] +1;
				rlevel = i;
			}
		}
		
		sb.append(rlevel)
			.append(' ')
			.append(width);
		
		System.out.println(sb);
	}
	// 중위 순회 진행 하며 x값을 저장 : 왼쪽 -> 루트 -> 오른쪽
	public void DFS(int parent, int level) 
	{
		int lNode = nlist[parent].left;
		int rNode = nlist[parent].right;
		if(lNode > 0) 					// 왼쪽 노드가 있다면
			DFS(lNode, level + 1);		// 왼쪽 탐색 시작
		
		// 중위 순회
		minX[level] = Math.min(minX[level], X);
		maxX[level] = Math.max(maxX[level], X);
		X++;
		
		if(rNode > 0)					// 오른쪽 노드가 있다면
			DFS(rNode, level + 1);		// 오른쪽 노드 탐색 시작
	}
    // 빠른 입력을 위해 만든 함수
    public static int read() throws Exception {
		int c, n = System.in.read() & 15;
		boolean isNegative = n == 13;
		if (isNegative) n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
		if (c == 13) System.in.read();
		return isNegative ? ~n + 1 : n;
	}
}

class Node{
	int left, right;
	Node(int left, int right)
	{
		this.left = left;
		this.right = right;
	}
}