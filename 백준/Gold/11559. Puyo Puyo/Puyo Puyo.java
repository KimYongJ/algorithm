// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main{
	static final int Y 		= 12, X = 6;
	static int dy[] 		= {0,0,1,-1};
	static int dx[] 		= {1,-1,0,0};
	static char arr[][];
	static boolean visit[][];
	static ArrayList<Node> pList = new ArrayList<>();
	
	static void resetPosition() { //뿌요를 내리는 로직
		// 세로로 한줄을 전체적으로 확인하여 문자를 밑에서부터 빈칸 없이 채운다.
		for(int j=0; j<X; j++) { 					// 가로 갯수 반복
			boolean usePointer = false;
			int pointer1 = 11; 						// 빈칸체크 포인터
			int pointer2 = 11; 						// 빈칸이 아닌 포인터
			while(pointer1 >= 0 && pointer2 >= 0) { // 하나라도 0보다 작으면 탈출
				if(!usePointer && arr[pointer1][j] != '.') {
					pointer1--;
				}else if(!usePointer && arr[pointer1][j] == '.'){
					usePointer = true;
					pointer2 = pointer1;
				}
				if(usePointer && arr[pointer2][j] == '.') {
					pointer2--;
				}else if(usePointer && arr[pointer2][j] != '.') {
					arr[pointer1][j] = arr[pointer2][j];
					arr[pointer2][j] = '.';
					pointer1--;
					usePointer = false;
				}
			}
		}
	}
	
	static void DFS(char base, int y,int x) { // 같은 뿌요 위치 파악하는 로직
		if(visit[y][x])return;
		visit[y][x] = true;
		pList.add(new Node(y,x));
		for(int i=0; i<4; i++) {
			int newY = y + dy[i];
			int newX = x + dx[i];
			if(newY>=0 && newX>=0 && newY<Y && newX<X && !visit[newY][newX] && arr[newY][newX]==base) {
				DFS(base, newY, newX);
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		int result 			= 0; 							// 결과를 출력할 변수
		arr 				= new char[Y][X]; 				// 문자열을 담을 배열
		for(int i=0; i<Y; i++)
			arr[i] 			= br.readLine().toCharArray(); 	// 문자열 초기화
		
		boolean check 		= true;
		
		while(check) {
			check			= false;
			visit 			= new boolean[Y][X];
			for(int i=0; i<Y; i++) {
				for(int j=0; j<X; j++) {
					if(arr[i][j] != '.' && !visit[i][j]) {	// arr과 visit배열을 돌면서 방문하지 않았고, '.'이 아닌 경우 DFS로 같은 좌표 탐색
						DFS(arr[i][j], i, j);				// 같은 문자가 몇개인지 체크, 문자의 좌표는 pList에 담음
						if(pList.size()>=4){ 				// 같은 범위를 터트리는 로직(색깔을 '.'으로 치환함
							for(Node now : pList)			// 뿌요를 뿌셔서 '.'으로 만듦
								arr[now.y][now.x] = '.';
							check = true;				 	// 4이상인 뿌요가 1개라도 있을 경우 check true처리
						}
						pList.clear(); 						// 좌표 초기화 
					}
				}
			}
			if(check) 										// 4이상인 것이 하나라도 있엇다면 결과에 +1
				result++;

			resetPosition(); 								// 뿌요를 내리는 로직
		}
		System.out.println(result);

	}
}
class Node{
	int y, x;
	Node(int y, int x) {
		this.y = y;
		this.x = x;
	}
}