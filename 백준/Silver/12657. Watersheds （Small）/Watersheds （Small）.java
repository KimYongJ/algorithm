//https://www.acmicpc.net/problem/12657
//5초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main{
	
	static final int dxy[][] = {{-1,0},{0,-1},{0,1},{1,0}};
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int Y = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int min = Integer.MAX_VALUE;
			int len = Y * X;
			int map[][] = new int[Y][X];
			int info[] = new int[len];
			
			for(int j=0; j<len; j++)
				info[j] = j;
			
			for(int y=0; y<Y; y++)
			{
				st = new StringTokenizer(br.readLine());
				for(int x=0; x<X; x++)
				{
					map[y][x] = Integer.parseInt(st.nextToken());
					min = Math.min(min, map[y][x]);
				}
			}
			
			for(int y=0; y<Y; y++)
			{
				for(int x=0; x<X; x++)
				{
					int ny = 0;
					int nx = 0;
					int m = map[y][x];
					
					for(int xy[] : dxy)
					{
						ny = y + xy[0];
						nx = x + xy[1];
						
						if(ny < 0 || nx < 0 || Y <= ny || X <= nx)
							continue;
						
						m = Math.min(m, map[ny][nx]);
					}
					
					if(m == map[y][x])
						continue;
					
					for(int xy[] : dxy)
					{
						ny = y + xy[0];
						nx = x + xy[1];
						
						if(ny < 0 || nx < 0 || Y <= ny || X <= nx)
							continue;
						
						if(m == map[ny][nx])
						{
							info[y * X + x] = info[ny * X + nx];
							break;
						}
					}
				}
			}
			
			int midx = 0;
			HashMap<Integer, Integer> hmap = new HashMap<>();
			sb.append("Case #").append(i).append(":\n");
			for(int y=0; y<Y; y++)
			{
				for(int x=0; x<X; x++)
				{
					int idx = find(y * X + x, info);
					
					if(!hmap.containsKey(idx))
						hmap.put(idx, midx++);
					
					sb.append((char)(hmap.get(idx) + 'a')).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}
	static int find(int parent, int[] info) {
		if(info[parent] == parent) return parent;
		return info[parent] = find(info[parent], info);
	}
}