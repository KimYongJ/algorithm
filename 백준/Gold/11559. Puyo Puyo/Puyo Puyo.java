// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

class Main{
	public static void main(String[] args)throws Exception{
		new Solution().solution();
	}
}
class Solution{

	char arr[][];
	int startI = 0, Y = 12, X = 6;
	int dy[] = {0,0,1,-1};
	int dx[] = {1,-1,0,0};
	boolean visit[][];
	int BFS(int startI, int startJ) {
		int cnt = 0;
		char base = arr[startI][startJ];
		
		ArrayList<Node> pList = new ArrayList<>();// 터트릴 좌표를 담는 리스트
		ArrayDeque<Node> q = new ArrayDeque<>(); 
		q.add(new Node(startI, startJ));
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			if(!visit[now.y][now.x]) {
				visit[now.y][now.x] = true;
				cnt++;
				pList.add(new Node(now.y, now.x));
				for(int i=0; i<4; i++) {
					int y = now.y + dy[i];
					int x = now.x + dx[i];
					if(y>=0 && x>=0 && y<Y && x<X && !visit[y][x] && arr[y][x]==base) {
						q.add(new Node(y,x));
					}
				}
			}
		}
		
		if(cnt >=4) {
			for(int i=0; i<pList.size(); i++) {
				Node now = pList.get(i);
				arr[now.y][now.x] = '.';
			}
		}else cnt = 0;
	
		
		
		return cnt;
	}
	
	void resetPosition() {
		// 왼쪽 한줄을 전체적으로 확인하여 빈칸 없이 아래로 쭉 내린다.
		for(int j=0; j<X; j++) { // 가로 갯수 반복
			boolean usePointer = false;
			int pointer1 = 11; // 빈칸체크 포인터
			int pointer2 = 11; // 빈칸이 아닌 포인터
			while(pointer1 >= 0 && pointer2 >= 0) {
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

	void solution()throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = 0;
		arr = new char[Y][X];
		for(int i=0; i<Y; i++)
			arr[i] = br.readLine().toCharArray();
		
		while(true) {
			int plus = 0;
			visit = new boolean[Y][X];
			for(int i=startI; i<Y; i++) {
				for(int j=0; j<X; j++) {
					if(arr[i][j] != '.' && !visit[i][j]) {
						plus += BFS(i,j); // 같은 범위를 터트리는 로직(색깔을 '.'으로 치환함
					}
				}
			}
			if(plus != 0) {
				result++;
			}else break;
			
			resetPosition(); // 뿌요를 내리는 로직
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

