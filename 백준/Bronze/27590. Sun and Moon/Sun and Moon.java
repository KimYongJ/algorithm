//https://www.acmicpc.net/problem/27590
//1초 1024MB
//3 10 // 태양이 몇 년 전에 올바른 위치에 있었는지, 태양이 그 위치로 돌아오는데 몇년이 걸리는지
//1 2 // 달이 올바른 위치에 있었던 년수, 다시 그 위치로 돌아오는데 걸리는 년수
//답 : 7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ds = Integer.parseInt(st.nextToken());
		int ys = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int dm = Integer.parseInt(st.nextToken());
		int ym = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<=5000; t++)
		{
			boolean sun = (t + ds) % ys == 0;
			boolean moon= (t + dm) % ym == 0;
			if(sun && moon)
			{
				System.out.print(t);
				return;
			}
		}
	}
}