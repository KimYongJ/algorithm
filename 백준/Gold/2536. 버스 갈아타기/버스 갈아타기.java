// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

class Main
{
	static int X, Y, BUS;
	static boolean visit[]; 				// 방문한 버스에 대해 체크할 변수
	static HashMap<Integer,int[]> hm; 		// 버스번호에 따른 노선을 담을 배열
	static HashSet<Integer> endBusNum;		// 종료 버스번호를 담는다.
	static ArrayDeque<Node> q;
	
	// 빠른 입력을 위한 함수
	static int read() throws Exception 
	{
	        int c, n = System.in.read() & 15;
	        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
	        return n;
	}
	public static boolean validate(int[] line1, int[] line2)
	{

	    return !(line1[2] < line2[0] || line1[0] > line2[2] || line1[3] < line2[1] || line1[1] > line2[3]);
	}
	public static void BFS() 
	{
		int cnt, cur[];
		while(!q.isEmpty()) 
		{
			Node now = q.poll();
			if(endBusNum.contains(now.busNum)) // 종료 조건 
			{
				System.out.print(now.cnt);
				return;
			}
			
			cur = hm.get(now.busNum);
			cnt = now.cnt + 1;
			for(int i=1; i<=BUS; i++) 
			{
				if(visit[i]) continue; // 기방문은 스킵
				if(validate(cur , hm.get(i)))  // 연결되어 있는지 확인
				{
					q.add(new Node(i, cnt));
					visit[i] = true;
				}
			}
			
		}
		
	}
	
	public static void main(String[] args)throws Exception
	{
		X 			= read();
		Y 			= read();
		BUS 		= read();
		hm 			= new HashMap<>();
		q			= new ArrayDeque<>();
		endBusNum 	= new HashSet<>();
		visit		= new boolean[BUS+1];
		int n, x1, x2, y1, y2, dummy;
		for(int i=0; i<BUS; i++) 
		{
			n 	= read();
			x1 	= read();
			y1 	= read();
			x2 	= read();
			y2 	= read();
			if(x1 > x2) 
			{
				dummy 	= x1;
				x1 		= x2; 
				x2 		= dummy;
			}
			if(y1 > y2) 
			{
				dummy 	= y1;
				y1 		= y2;
				y2 		= dummy;
			}
			hm.put(n, new int[] {x1,y1,x2,y2}); // 버스 번호에 따른 좌표를 왼쪽이 항상 작은 수로 저장 되도록 넣는다.
		}
		
		x1 = read(); 	// 시작 x좌표
		y1 = read();	// 시작 y좌표
		x2 = read();	// 종료 x좌표
		y2 = read();	// 종료 y좌표
		
		boolean isStart , isEnd;
		for(int i=1; i<=BUS; i++) 
		{
			int[] p = hm.get(i);
			// 시작좌표와 끝좌표 체크
			isStart = p[0] <= x1 && x1 <= p[2] && p[1] <= y1 && y1<= p[3];
			isEnd 	= p[0] <= x2 && x2 <= p[2] && p[1] <= y2 && y2<= p[3];
			
			if(isStart && isEnd) // 시작과 끝이 한버스로 가능할 때 종료 
			{
				System.out.println(1);
				return;
			}
			if(isStart) {
				q.add(new Node(i,1));
				visit[i] = true;
			}
			if(isEnd) 	endBusNum.add(i);
		}
		
		BFS();

	}
	public static class Node
	{
		int cnt, busNum;
		Node(int busNum, int cnt)
		{
			this.busNum = busNum;
			this.cnt = cnt;
		}
	}
}

