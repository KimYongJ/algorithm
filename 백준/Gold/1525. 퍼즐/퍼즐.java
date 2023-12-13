// https://github.com/KimYongJ/algorithm
//	[ 인덱스 -> 좌표 변환 법 ]
//		왼쪽좌표 : idx / 3
//		오른좌표 : idx % 3
//	[ 좌표 -> 인덱스 변환 법 ]
//		왼쪽좌표 * 3 + 오른쪽좌표 
//  빠른 연산을 위해 숫자를 1차원으로 변경한 후 연산
import java.util.ArrayDeque;
import java.util.HashSet;

class Main{
	
	static StringBuilder sb = new StringBuilder();
	static HashSet<Integer> set = new HashSet<>();
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	
	// 빠른 입력을 위한 함수
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
    
	// 전달 인자의 0인덱스를 찾아 반환하는 함수
	public static int getZeroIndex(int num) {
		int base = 99_999_999;
		int div = 100_000_000;
		int idx = 0;
		if(base<num) {// 9자리만 연산실행( 맨앞이 0이 아닐 때만)
			while(base<num) {
				num %= div;
				div /= 10;
				base %= div;
				idx ++;
			}
		}
		return idx;
	}
	// 자릿수를 바꿔 저장하는 함수( 수학적 계산 후 코드를 축약한것이라 이해 어려울 수 있음 )
	public static int changeData(int baseNumber, int targetIdx, int baseZeroIdx ) {
		int maxlen 			= 8;
		if(baseZeroIdx== 0) { 
			targetIdx 		-= 1; 		// 맨앞이 0이면 targetIndx는 하나 작아져야 한다.
			maxlen			-= 1; 		// 맨앞이 0이면 maxlen이 하나 작아져야 한다.
		}
		int digit 			= (int)Math.pow(10, maxlen-targetIdx);
		int targetNumber 	= (baseNumber / digit)%10;
		int diff  			= targetNumber * digit;
		baseNumber 			+= targetNumber * (int)Math.pow(10, 8-baseZeroIdx); // 0 자리에 해당 숫자 추가
		baseNumber 			-= diff;
		return baseNumber;
	}
	public static void BFS(int startNumber) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(startNumber,getZeroIndex(startNumber),0));
		set.add(startNumber);
		
		while(!q.isEmpty()) {
			Node now 			= q.poll();
			
			if(123456780 == now.number) {
				System.out.println(now.dist);
				return;
			}
			int nextDist 		= now.dist+1;
			int y 				= now.idx/3; 			// 일차원 배열의 인덱스로 y좌표 값 구하기
			int x				= now.idx%3; 			// 일차원 배열의 인덱스로 x좌표 값 구하기
			for(int i=0; i<4; i++) {
				int newY = y + dy[i];
				int newX = x + dx[i];
				
				if(newY>=0 && newX>=0 && newY<3 && newX<3) {
					int next_zero_idx = newY*3 + newX; 	// 2차원 좌표값을 일차원 배열의 인덱스로 변환
					int nextData = changeData(now.number,next_zero_idx,now.idx);// 0과 해당 좌표의 인자를 바꿈
					if(!set.contains(nextData)) {		// 한번도 방문한 적  없는 숫자일 경우 큐에 담음
						q.add(new Node(nextData,next_zero_idx, nextDist));
						set.add(nextData);
					}
				}
			}
		}
		System.out.println(-1);
	}
	public static void main(String[] args)throws Exception{
		int startNumber = 0;
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				startNumber = startNumber*10 + read();
		BFS(startNumber); 								// 만든 숫자 전달해 해당 문자로 BFS를 진행해 완전탐색
	}
}
class Node{
	int number;
	int idx, dist;
	Node(int number, int idx, int dist){
		this.idx 	= idx;								// 현재 0의 인덱스 
		this.number = number;							// 현재까지 만든 숫자
		this.dist 	= dist;								// 현자 숫자까지 오는데 걸린 거리 
	}
}