// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Node{
	int y,x;
	Node(int y, int x){this.y=y; this.x=x;}
}

class Main{
	static final int		dxy[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static int 				N, K, nextY, nextX, tmp, map[][];
	static boolean 			flag;
	static boolean			visit[][];
	static ArrayList<Node> 	position;
	
	public static void DFS(int base, int y,int x) {
		if(!visit[y][x]) 
		{
			visit[y][x] = true;
			position.add(new Node(y,x));
			for(int xy[] : dxy) 
			{
				nextY = y + xy[0];
				nextX = x + xy[1];
				if(map[nextY][nextX] == base && !visit[nextY][nextX])
					DFS(base, nextY, nextX);
			}
		}
	}
	
	public static void change(int y, int x) {
		if(y+1 <= N && map[y+1][x] == 0) 
		{
			tmp 		= map[y][x];
			map[y][x] 	= map[y+1][x];
			map[y+1][x] = tmp;
			change(y+1,x);	// 재귀 호출
		}
	}
	public static void DROP() {
		for(int x=1; x<=10; x++)  // 왼쪽부터 탐색
			for(int y=N; y>0; y--)// 밑에서부터 올라옴
				if(map[y][x] != 0)
					change(y,x);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N 			= Integer.parseInt(st.nextToken());
		K 			= Integer.parseInt(st.nextToken());
		map 		= new int[N+2][12];
		position 	= new ArrayList<>();
		String str;
		for(int y=1; y<=N; y++)
		{
			str = br.readLine();
			for(int x=1; x<=10; x++)
				map[y][x] = str.charAt(x-1)-'0';
		}
		
		flag = true;
		while(flag) 
		{
			DROP();	// 밑으로 내리기
			flag = false;
			visit = new boolean[N+2][12];
			for(int y=1; y<=N; y++)
				for(int x=1; x<=10; x++)
					if(map[y][x] != 0 && !visit[y][x]) 
					{
						
						position.clear(); // 좌표를 담을 리스트 클리어

						DFS(map[y][x],y,x);
						
						if(position.size() >= K) 
						{ // 연결된 개수가 K개 이상일 경우 0으로 치환
							for(Node n : position)
								map[n.y][n.x] = 0; 
							flag = true;
						}
					}
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for(int y=1; y<=N; y++) 
		{
			for(int x=1; x<=10; x++)
				sb.append(map[y][x]);
			sb.append('\n');
		}
		System.out.print(sb);
	}
}