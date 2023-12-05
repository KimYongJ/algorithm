// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main{
	static final int INF = Integer.MAX_VALUE;
	static int W, H, G, E;
	static int a,b,c,d,e;
	static int rip[][];
	static int time[][]; // 최종 시간을 담을 배열 
	static int dxy[][] = {{0,1},{0,-1},{1,0},{-1,0}};
	static ArrayList<Edge> edge; // 벨만 포드알고리즘을 사용할 수 있도록 2차원 좌표 끼리 연결을 node-node 형태로 바꾼다.
	static StringBuilder sb = new StringBuilder();
	
	
	public static void bellman_ford() {
		boolean cycle = false;
		
		time[0][0] = 0;
		
		int V = W*H-G;
		
		Loop : 
		for(int i=0; i<V; i++) {

			for(Edge now : edge) {
				Node start 	=  now.start;
				Node end 	=  now.end;
				
				if(time[start.w][start.h] == INF) continue; // 벨만 포드 알고리즘 특징으로 특정 노드에 대해 최단 거리를 구하기 위해 처음 초기화된 값이 아닌 경우 연산하지 않음, 맨 처음 time배열에 inf가 아닌 값은 0,0뿐임 
				
				int newTime = time[start.w][start.h] + now.time;
				
				if(time[end.w][end.h]> newTime) {
					time[end.w][end.h] = newTime;
					if(i==V-1) {
						cycle = true;
						break Loop;
					}
				}
				
			}
		}
		
		
		
		if(cycle) {
			sb.append("Never");
		}else if(time[W-1][H-1]==INF) {
			sb.append("Impossible");
		}else {
			sb.append(time[W-1][H-1]);
		}
		sb.append('\n');
		
	}
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W==0) break; // 종료 조건 
			
			rip = new int[W][H]; // 묘비와 구멍입구 좌표를 담을 배열
			edge = new ArrayList<>(); // 간선 정보를 담을 리스트
			time = new int[W][H]; // 최종 시간을 담을 배열 
			
			G = Integer.parseInt(br.readLine());
			for(int i=0; i<G; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				rip[a][b] = 1; // 묘비는 1로 저장
			}
			
			E = Integer.parseInt(br.readLine()); // 구멍의 갯수
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				edge.add(new Edge(new Node(a,b), new Node(c,d),e)); // 구멍 시작 좌표에서 출구 좌표를 바로 저장한다. 벨만포드 알고리즘은 간선의 순서가 상관 없기 때문. 단, +1을 해주진 않는다.
				rip[a][b] = 2; // 시작 좌표는 2로 저장
			}
			
			// 받은 정보를 바탕으로 하나의 간선을 만들어 줌(구멍의 입구는 연결하지 않아야 하지만, 나오는 곳은 연결해주어야 함)
			for(int w=0; w<W; w++) {
				for(int h=0; h<H; h++) {
					time[w][h] = INF; 
					
					if( (w==W-1 && h==H-1) || rip[w][h] != 0) 
						continue; // 현위치가 최종 도착점이나, 묘비이거나, 워프 시작점일 경우 간선을 추가하지 않음 
					
					for(int xy[] : dxy) {
						int newW = w + xy[0];
						int newH = h + xy[1];
						if(newW<0 || newH<0 || newW>=W || newH>=H || rip[newW][newH] == 1) 
							continue;  // 좌표 유효성 체크 
 
						edge.add(new Edge(new Node(w,h), new Node(newW, newH),1));
					}
				}
			}
			bellman_ford(); // 벪만 포드 알고리즘 시작
		}
		
		System.out.println(sb);
		
	}
}
class Edge{
	Node start, end;
	int time;
	Edge(Node start, Node end, int time){
		this.start 	= start;
		this.end 	= end;
		this.time 	= time;
	}
}
class Node{
	int w, h;
	Node(int w, int h){
		this.w = w;
		this.h = h;
	}
}