// https://github.com/KimYongJ/algorithm
// 제한 조건  : 노드 총 100개, 간선 2,000개
// 문제 요약  : 대소 비교로 입력되는 값에 대해서 서로 연결되어 있는지 체크
// [ 알고리즘 설명 ]
// 플로이드 와샬알고리즘을 사용해 모든 정점에 대해 모든 정점에 도달 가능한지 확인한다.
// a > b 이고 b > c 일때 a > c임을 알 수 있다. 간단하게 생각하면 아래와 같이 생각이 가능하다.
// a와 b가 연결되어있고, b와 c가 연결되어 있으면 a와 c는 연결되어 있는 것으로 알 수 있다. 그러나 단순 연결로 생각하면 안된다
// 추가로 생각할 것이 있다. 다음과 같이 2개의 대소관계 (1>2>4 , 6>5>4)가 주어졌을 때
// 1은 2,4와만 연결되어있고 2는 1,4와 연결되어있고, 6은 5,4와 연결되어있고 4는 1,2,5,6모두 연결된것으로
// 제출해야 정답이다. 이를 해결하기 위해 배열에 1과 -1로 연결을 표현해 크고 작음으로 연결되어있음을 나타내면
// 연결되어있지 않은 값을 알 수 있다. 연결 되지 않은 것은 0으로 표현될 것이다. 

class Main{
    public static void main(String[] args)throws Exception{
    	 new Solution().solution();
    }
}
class Solution{
	void solution() throws Exception{
		StringBuilder sb 	= new StringBuilder();
		Reader r 			= new Reader();
		int N 				= r.read()+1; 		// 노드 갯수
		int M 				= r.read(); 		// 간선의 갯수
		int cnt 			= -1;				// 자기를 제외하고 연결 불가능 부분을 카운팅하는 변수 / 자기자신은 빼야함으로 -1로 셋팅
		char arr[][] 		= new char[N][N];	// 초기값 모두 0
		for(int i=0; i<M; i++) {
			int a 		= r.read();
			int b 		= r.read();
			arr[a][b] 	= 1; 					// 연결표시
			arr[b][a] 	= 2;					// 반대 연결표시
		}
		 
		for(int k=1; k<N; k++) {
			for(int i=1; i<N; i++) {
				if(i==k) continue; 				// 빠른 연산을 위해 불필요 연산 스킵
				for(int j=1; j<N; j++) {
					if(i==j || k==j) continue;	// 빠른 연산을 위해 불필요 연산 스킵
					if(arr[i][k] == 1 && arr[k][j]==1) { 		// i->k로 가는게 연결되있고, k->j로 가는게 연결되어 있다면 i->j연결되어있음.
						arr[i][j] = 1;
					}else if(arr[i][k] == 2 && arr[k][j]==2) {	// i->k로 가는게 연결되있고, k->j로 가는게 연결되어 있다면 i->j연결되어있음.
						arr[i][j] = 2;
					}
				}
			}
		}
		
		for(int i=1; i<N; i++) {
			for(int j=1; j<N; j++)
				if(arr[i][j] == 0) 		// 값이 0인것은 연결되지 않은 것으로 판단 
					cnt++; 
			sb.append(cnt)
			  .append('\n');
			cnt = -1;
		}
		System.out.println(sb);
	}
}

// 빠른 입력을 위해 만듦
class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;
    int read() throws Exception {
        int n = 0;
        byte c;
        while ((c = get()) <= 32);
        do n = (n << 3) + (n << 1) + (c & 15);
        while (isNumber(c = get()));
        return n;
    }
    boolean isNumber(byte c) {
        return 47 < c && c < 58;
    }
    byte get() throws Exception {
        if (index == size) {
            size = System.in.read(buffer, index = 0, SIZE);
            if (size < 0) buffer[0] = -1;
        }
        return buffer[index++];
    }
}