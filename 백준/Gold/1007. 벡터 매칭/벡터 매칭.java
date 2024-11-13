//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1007
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Pair{
	int x,y;
	Pair(int x, int y){this.x=x; this.y=y;}
}
class Main{
	
	static double min;
	static List<Pair> coor;
	static List<Integer> picked;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			min			= Double.MAX_VALUE;
			coor		= new ArrayList<>();
			picked		= new ArrayList<>();
			int sumX	= 0;
			int sumY	= 0;
			int N		= Integer.parseInt(br.readLine());
			int x,y;
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				sumX += x = Integer.parseInt(st.nextToken());
				sumY += y = Integer.parseInt(st.nextToken());
				coor.add(new Pair(x,y));
			}
			
			selectMinus(N, N/2, sumX, sumY);
			
			sb.append(String.format("%.11f", min)).append('\n');
		}
		System.out.print(sb.toString());
	}
	static void selectMinus(int n, int toPick, int sumX, int sumY) {
		if(toPick == 0)
		{
			double result = Math.sqrt((long)sumX*sumX + (long)sumY*sumY);
			if(result < min)
				min = result;
			return;
		}
		
		int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;
		
		for(int i=smallest; i< n; i++)
		{
			picked.add(i);
			selectMinus(n, toPick - 1, sumX - (coor.get(i).x<<1), sumY - (coor.get(i).y<<1));
			picked.remove(picked.size() - 1);
		}
		
	}
}