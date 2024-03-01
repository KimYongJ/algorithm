// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;

class Main{
	
	static int N, K, M, map[][];
	static boolean visit[];
	static ArrayDeque<Node> q;
	static ArrayList<Integer>[] list;
	
	public static void BFS() 
	{
		q.add(new Node(1,1));
		visit[1] = true;
		
		int nextDist , nextNode;
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			
			if(now.node == N) 
			{
				System.out.println(now.dist);
				System.exit(0);
			}
			
			nextDist = now.dist+1;
			for(int index : list[now.node]) 
			{
				for(int i=0; i<K; i++) 
				{
					nextNode = map[index][i];
					
					if(!visit[nextNode]) 
					{
						visit[nextNode] = true;
						q.add(new Node(nextNode, nextDist));
					}
				}
			}
		}
		
	}
	public static void main(String[] args)throws Exception{
		InputReader read = new InputReader();
		N 		= read.in(); 			// 역의 수 
		K 		= read.in(); 			// 하나의 튜브가 연결하는 최대 역의 수 
		M 		= read.in(); 			// 하이퍼튜브의 개수
		map 	= new int[M][K];		// 연결을 담을 좌표
		visit 	= new boolean[N+1];		// 방문한 노드를 체크할 배열
		list 	= new ArrayList[N+1];	// 역의 번호마다 연결된 하이퍼튜브의 idx
		q 		= new ArrayDeque<>();	// 현재 역의 정보와 현재 역까지 오는데 걸린 갯수
		
		for(int i=0; i<=N; i++)
			list[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) 
			for(int j=0; j<K; j++) 
			{
				map[i][j] = read.in();
				list[map[i][j]].add(i);
			}

		BFS();
		System.out.println(-1);
	}
	

	// INPUT 속도 증가
	private static class InputReader {
		private InputStream stream = System.in;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;
		
		public int read() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0) {
					return -1;
				}
			}
			return buf[curChar++];
		}

		public int in() {
			int c = read();
			while (isSpaceChar(c)) {
				c = read();
			}
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

	}
}
class Node{
	int node, dist;
	Node(int node, int dist){
		this.node = node;
		this.dist = dist;
	}
}