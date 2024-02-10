// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{
	static final int MAX = 100_001;
	static int start, end, time=MAX, searchNum;
	static int[] timeDP;
	public static void BFS() {
		ArrayDeque<Point> q = new ArrayDeque<>();
		q.add(new Point(start, 0)); // 현재 위치와 걸린 시간을 넣는다.
		
		while(!q.isEmpty()) {
			Point now = q.poll();
			
			if(now.now == end) {
				if(time >= now.time)time = now.time;
				searchNum++;
				continue;
			}
			
			int nextTime = now.time+1;
			for(int i=0; i<3; i++) {
				int next = now.now;
				
				if(i==0) next +=1;
				else if(i==1) next -=1;
				else next *=2;
				
				if(next<0 || MAX<=next) continue;
				
				if(timeDP[next] >= nextTime) {
					timeDP[next] = nextTime;
					q.add(new Point(next, nextTime));
				}
				
				
				
			}

		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		timeDP = new int[MAX];
		Arrays.fill(timeDP, MAX);
		BFS();
		
		System.out.println(time);
		System.out.println(searchNum);
	}
	
	public static class Point{
		int now, time;
		Point(int now, int time){
			this.now = now;
			this.time = time;
		}
	}
}