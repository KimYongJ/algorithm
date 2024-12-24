//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/25825
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	static int group[][] = {{0,1},{2,3},{4,5},{6,7},{8,9},{10,11}};
	static int map[][];
	static boolean visit[];
	static int MAX = 1<<30;
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map		= new int[12][12];
		visit	= new boolean[12];
		
		for(int y=0; y<12; y++)
		{
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<12; x++)
				map[y][x] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<6; i++)
		{
			visit[group[i][0]] = true;
			back(0, 0, i, group[i][0]);
			visit[group[i][0]] = false;
			visit[group[i][1]] = true;
			back(0, 0, i, group[i][1]);
			visit[group[i][1]] = false;
		}
		
		System.out.print(MAX);
	}
	public static void back(int depth, int time, int groupIdx, int prev) {
		if(depth == 11)
		{
			MAX = Math.min(MAX, time);
			return;
		}
		if(!visit[group[groupIdx][0]])
		{
			visit[group[groupIdx][0]] = true;
			back(depth + 1, time + map[prev][group[groupIdx][0]], groupIdx, group[groupIdx][0]);
			visit[group[groupIdx][0]] = false;
		}
		else if(!visit[group[groupIdx][1]]) {
			visit[group[groupIdx][1]] = true;
			back(depth + 1, time + map[prev][group[groupIdx][1]], groupIdx, group[groupIdx][1]);
			visit[group[groupIdx][1]] = false;
		}
		else {
			for(int i=0; i<6; i++)
			{
				if(!visit[group[i][0]])
				{
					visit[group[i][0]] = true;
					back(depth + 1, time + map[prev][group[i][0]], i, group[i][0]);
					visit[group[i][0]] = false;
				}
				if(!visit[group[i][1]]) {
					visit[group[i][1]] = true;
					back(depth + 1, time + map[prev][group[i][1]], i, group[i][1]);
					visit[group[i][1]] = false;
				}
			}
		}
	}
}
