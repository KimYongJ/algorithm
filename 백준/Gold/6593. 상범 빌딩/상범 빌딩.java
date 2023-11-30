// https://github.com/KimYongJ/algorithm
// 문재 개요 : 3차원으로 상하 좌우, 위아래 까지 해서 빌딩의 탈출구에 빨리 도달하는 거리를 찾는 문제
// BFS를 통해 진행

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws Exception{
    	 new Solution().solution();
    }
}
class Solution{
	StringBuilder   sb 	= new StringBuilder();
	ArrayDeque<Node> q;
	int L, R, C, // 층, 세로, 가로
		zyx[][] = {{0,0,1},{0,0,-1},{0,1,0},
				  {0,-1,0},{1,0,0},{-1,0,0}};
	int z,y,x,dist; // BFS시 사용하는 변수들
	char[][][] arr; // 상하좌우 위아래 담을 배열
	
	void BFS() {
		while(!q.isEmpty()) {
			
			Node now = q.poll();
			
			for(int i=0; i<6; i++) {
				z 		= now.z + zyx[i][0];
				y 		= now.y + zyx[i][1];
				x 		= now.x + zyx[i][2];
				dist	= now.dist+1;
				
				if(z<0 || y<0 || x<0 || z>=L || y>=R || x>=C || 
					arr[z][y][x] == '#') {
					continue;
				}
				
				if(arr[z][y][x] == 'E') {
					sb.append("Escaped in ")
					  .append(dist)
					  .append(" minute(s).")
					  .append('\n');
					return;
				}
				q.add(new Node(z,y,x,dist));
				arr[z][y][x] = '#'; // 방문 후 벽으로 막아 못지나가 가게 함
			}
		}
		sb.append("Trapped!").append('\n');
	}
	void solution()throws  Exception {
		BufferedReader  br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			q	= new ArrayDeque<>();
			st 	= new StringTokenizer(br.readLine());
			L 	= Integer.parseInt(st.nextToken()); // 층수
			R 	= Integer.parseInt(st.nextToken()); // 세로
			C 	= Integer.parseInt(st.nextToken()); // 가로
			
			if(L==0 && R==0 && C==0)break; // 종료 조건
			
			arr = new char[L][R][C];
			
			for(int z=0; z<L; z++) { // 층
				for(int y=0; y<R; y++) {  // 세로
					char[] input = br.readLine().toCharArray();
				    for(int x=0; x<C; x++) {
				    	arr[z][y][x] = input[x];
				    	if(input[x]=='S') {
				    		q.add(new Node(z,y,x,0));
				    		arr[z][y][x] = '#'; // 시작점을 #으로 막음.
				    	}
				    }
				}
				br.readLine(); // 공백 입력을 버려준다.
			}
			BFS();			
		}
		System.out.println(sb);		
	}
}
class Node{
	int z,y,x,dist;
	Node(int z,int y, int x, int dist){
		this.z = z;
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}