// https://github.com/kimyongj/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	int y1, x1, y2, x2; 
	Node(int y1, int x1,int y2, int x2){
		this.y1=y1; this.x1=x1;
		this.y2=y2; this.x2=x2;
	}
}

class Main{
	
	static int		result = Integer.MAX_VALUE;
	static int		Y, X, K;
	static int		map[][];
	static Node 	pos[];
	static boolean 	visit[];

	public static void left(int i) {
		int y1	= pos[i].y1;
		int x1	= pos[i].x1;
		int y2	= pos[i].y2;
		int x2	= pos[i].x2;
		while(y1<y2) 
		{
			int before = map[y1][x1];
			int dummy = 0;
			for(int y=y1+1; y<=y2; y++) {
				dummy = map[y][x1];
				map[y][x1] = before;
				before = dummy;
			}
			for(int x=x1+1; x<=x2; x++) {
				dummy = map[y2][x]; 
				map[y2][x] = before;
				before = dummy;
			}
			for(int y=y2-1; y>=y1; y--) {
				dummy = map[y][x2];
				map[y][x2] = before;
				before = dummy;
			}
			for(int x=x2-1; x>=x1; x--) {
				dummy = map[y1][x];
				map[y1][x] = before;
				before = dummy;
			}
			y1++;
			x1++;
			y2--;
			x2--;
		}
	}
	public static void right(int i) {
		int y1	= pos[i].y1;
		int x1	= pos[i].x1;
		int y2	= pos[i].y2;
		int x2	= pos[i].x2;
		while(y1<y2) 
		{
			int before = map[y1][x1];
			int dummy = 0;
			for(int x=x1+1; x<=x2; x++) {
				dummy = map[y1][x];
				map[y1][x] = before;
				before = dummy;
			}
			for(int y=y1+1; y<=y2; y++) {
				dummy = map[y][x2];
				map[y][x2] = before;
				before = dummy;
			}
			for(int x=x2-1; x>=x1; x--) {
				dummy = map[y2][x];
				map[y2][x] = before;
				before = dummy;
			}
			for(int y=y2-1; y>=y1; y--) {
				dummy = map[y][x1];
				map[y][x1] = before;
				before = dummy;
			}
			y1++;
			x1++;
			y2--;
			x2--;
		}
	}
	public static void sum() {
		int sum;
		for(int y=1; y<=Y; y++) 
		{
			sum = 0;
			for(int x=1; x<=X; x++)
				sum += map[y][x];
			if(result > sum)
				result = sum;
		}
	}
	public static void backtracking(int depth) {
		if(depth == 0) 
		{
			sum();
			return;
		}
		for(int i=0; i<K; i++)
			if(!visit[i]) 
			{
				visit[i] = true;
				right(i);
				backtracking(depth - 1);
				left(i);
				visit[i] = false;
			}
		
	}
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Y	= Integer.parseInt(st.nextToken());
		X	= Integer.parseInt(st.nextToken());
		K	= Integer.parseInt(st.nextToken());
		map = new int[Y+1][X+1];
		pos = new Node[K];
		visit = new boolean[K];
		for(int y=1; y<=Y; y++) 
		{
			st = new StringTokenizer(br.readLine());
			for(int x=1; x<=X; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		int a,b,c;
		for(int k=0; k<K; k++) 
		{
			st	= new StringTokenizer(br.readLine());
			a	= Integer.parseInt(st.nextToken());
			b	= Integer.parseInt(st.nextToken());
			c	= Integer.parseInt(st.nextToken());
			pos[k] = new Node(a-c, b-c, a+c, b+c);
		}
		
		backtracking(K);

		System.out.print(result);
	}
}