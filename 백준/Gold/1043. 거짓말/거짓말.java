// https://github.com/KimYongJ/algorithm
class Main{

	static int[] parent;
	
	public static void main(String[] args)throws Exception{
		int N, M, r, max, lie=0, aParent, bParent, partyList[][];
		N = read(); // 사람수
		M = read(); // 파티 수
		
		partyList = new int[M][51]; // 파티수만큼 총 51명 들어갈 수 있는 배열 선언
		
		parent = new int[N+1]; // 부모노드를 담을 배열 선언
		for(int i=1; i<N+1; i++)
			parent[i] = i; // 부모노드를 자기 자신으로 초기화 

		r = read();
		while(r-->0) 
			parent[read()] = 0; // 진실을 알고 있다면 부모가 0값임 
		
		
		for(int i=0; i<M; i++) {// 파티수 반복

			max = read(); // 최대 파티원
			for(int j=0; j<max; j++) {
				partyList[i][j] = read(); // 파티원 입력
			}

			for(int j=0; j<max-1; j++) {
				aParent = getParent(partyList[i][j]);
				bParent = getParent(partyList[i][j+1]);
				
				if(aParent<bParent) { // a부모노드가 더 작으면 b부모노드를 갖고있는 모든 값을 a로 바꾼다.
					AtoB(bParent,aParent);
				}else {
					AtoB(aParent,bParent);
				}
			}
		}
		
		Loop:
		for(int i=0; i<M; i++) {
			for(int people : partyList[i]) { // 파티를 하나씩 순회한다.
				if(people==0) break;
				if(getParent(people) == 0)// 부모노드가 하나라도 -1이면 진실을 알고 있으므로 다음 파티로 넘어간다.
					continue Loop;
			}
			lie++;
		}
		System.out.println(lie);
	}	
	// 부모 노드를 찾아 주는 함수 
	public static int getParent(int x) {
		if(parent[x]==x) return x;
		return getParent(parent[x]);
	}
	// 부모노드를 서로 바꾸는 함수
	public static void AtoB(int A,int B) {
		for(int p=0; p<parent.length; p++)
			if(parent[p] == A)parent[p] = B;
	}
    private static int read() throws Exception{
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32)
	    	n = (n << 3) + (n << 1) + (c & 15);
	    return n;
	}
}