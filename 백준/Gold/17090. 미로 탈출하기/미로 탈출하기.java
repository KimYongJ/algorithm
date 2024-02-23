// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	
	static int Y, X, flag, total, visit[][];
	static char map[][];
	static HashSet<Integer> success, fail;
	public static void DFS(int y, int x, int depth)
	{
		// 밖으로 벗어 났을 때 + 탈출 가능한 flag를 만났을 때
		if( y<0 || x<0 || y>=Y || x>=X || success.contains(visit[y][x]))
		{
			total += depth; // 지금까지 지나온 노드들을 모두 더한다.
			success.add(flag); // flag 값을 success에 더해 다음 탐색시 탈출 가능유무를 알 수 있게 한다.
		}
		// 방문한 노드가 0이 아닌경우 사이클이거나, fail에 저장된 값이다. 이유는 위에서 success를 한번 체크했기 때문
		else if(visit[y][x] != 0)
		{
			fail.add(flag);
		}
		else
		{
			visit[y][x] = flag;
			switch(map[y][x]) {
			case 'U' : DFS(y-1,x,depth + 1);break;
			case 'R' : DFS(y,x+1,depth + 1);break;
			case 'D' : DFS(y+1,x,depth + 1);break;
			case 'L' :DFS(y,x-1,depth + 1); break;
			}
		}
	}
	public static void main(String[] args)throws Exception
	{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		Y 					= Integer.parseInt(st.nextToken());
		X 					= Integer.parseInt(st.nextToken());
		visit 				= new int[Y][X];
		map 				= new char[Y][X];
		success 			= new HashSet<>();
		fail 				= new HashSet<>();
		// 값을 입력 받는다.
		for(int y=0; y<Y; y++)
			map[y] = br.readLine().toCharArray();
		
		for(int y=0; y<Y; y++)
			for(int x=0; x<X; x++) 
			{
				// 방문한적 없을 때 만 실행
				if(visit[y][x] == 0)
				{
					flag++;
					DFS(y,x,0);
				}
			}
		
		System.out.println(total);
	}
	
}