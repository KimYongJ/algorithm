//https://www.acmicpc.net/problem/11978
//2초 512MB
//6 // 명령수(1<=100)
//N 10 // 북쪽
//E 2 // 동쪽
//S 3 // 남쪽
//W 4 // 서쪽
//S 5
//E 8
//답 : 10
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	static HashMap<Long, Integer> map;
	static int N;
	static int res;
	static long y,x;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		y = 1001;
		x = 1001;
		res = Integer.MAX_VALUE;
		map = new HashMap<>();
		
		map.put(y * 10_000_000 + x, 0);
		
		int time = 0;
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int idx = getIdx(st.nextToken().charAt(0));
			int t = Integer.parseInt(st.nextToken());
			
			while(t-->0)
			{
				time++;
				y += dxy[idx][0];
				x += dxy[idx][1];
				
				long key = y * 10_000_000 + x;
				
				if(map.containsKey(key))
					res = Math.min(res, time - map.get(key));
				
				map.put(key, time);
			}
		}
		
		if(res == Integer.MAX_VALUE)
			res = -1;
		
		System.out.print(res);
	}
	static int getIdx(char c) {
		if(c == 'N') return 0;
		if(c == 'E') return 1;
		if(c == 'S') return 2;
		return 3;
	}
}