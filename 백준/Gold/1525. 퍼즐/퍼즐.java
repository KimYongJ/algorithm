// https://github.com/KimYongJ/algorithm
//	[ 인덱스 -> 좌표 변환 법 ]
//		왼쪽좌표 : idx / 3
//		오른좌표 : idx % 3
//	[ 좌표 -> 인덱스 변환 법 ]
//		왼쪽좌표 * 3 + 오른쪽좌표
//  위 방법 보다 1차원 배열의 상하좌우로 하는게 더 빠름 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	
	static StringBuilder sb = new StringBuilder();
	static HashMap<String, Integer> map = new HashMap<>();
	static int dx[] = {0,0,-1,1};
	static int dy[] = {1,-1,0,0};
	public static void BFS(String str) {
		ArrayDeque<Node> q = new ArrayDeque<>();
		q.add(new Node(str,str.indexOf('0'),0));
		map.put(str, 0);
		
		while(!q.isEmpty()) {
			Node now 			= q.poll();
			int nextDist 		= now.dist+1;
			
			if("123456780".equals(now.str)) {
				System.out.println(map.get(now.str));
				return;
			}
			
			int y 				= now.idx/3; 			// 일차원 배열의 인덱스로 y좌표 값 구하기
			int x				= now.idx%3; 			// 일차원 배열의 인덱스로 x좌표 값 구하기
			for(int i=0; i<4; i++) {
				int newY = y + dy[i];
				int newX = x + dx[i];
				
				if(newY>=0 && newX>=0 && newY<3 && newX<3) {
					sb = new StringBuilder(now.str);
					int next_zero_idx = newY*3 + newX; 	// 2차원 좌표값을 일차원 배열의 인덱스로 변환
					// next_zero_idx에 있는 값을 now.idx에 대입
					sb.setCharAt(now.idx, sb.charAt(next_zero_idx));
					// next_zero_idx에 있는 값은 0으로 치환
					sb.setCharAt(next_zero_idx, '0');
					
					String nextString = sb.toString();
					if(!map.containsKey(nextString)) {
						q.add(new Node(nextString,next_zero_idx, nextDist));
						map.put(nextString, nextDist);
					}
				}
			}
		}
		System.out.println(-1);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for(int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				sb.append(st.nextToken()); 	// 하나의 문자열로 생성
			}
		}	
		BFS(sb.toString()); 						// 만든 문자를 전달해 해당 문자로 BFS를 진행해 완전탐색
	}
}
class Node{
	String str;
	int idx, dist;
	Node(String str, int idx, int dist){
		this.idx 	= idx;
		this.str 	= str;
		this.dist 	= dist;
	}
}