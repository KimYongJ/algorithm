//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1007
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N, pair[][];
	static double min;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			N			= Integer.parseInt(br.readLine());
			min			= Double.MAX_VALUE;
			pair		= new int[N][2];
			int sumX	= 0;
			int sumY	= 0;
			int x,y;
			for(int i=0; i<N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				sumX += x = Integer.parseInt(st.nextToken());
				sumY += y = Integer.parseInt(st.nextToken());
				pair[i] = new int[] {x,y};
			}
			
			selectMinus(N>>1, sumX, sumY, 0);
			
			sb.append(String.format("%.11f", min)).append('\n');
		}
		System.out.print(sb.toString());
	}
	static void selectMinus(int toPick, int sumX, int sumY, int idx) {
		if(toPick == 0)
		{
			double result = Math.sqrt((long)sumX*sumX + (long)sumY*sumY);
			if(result < min)
				min = result;
			return;
		}
		
		for(int i=idx; i< N; i++)
			selectMinus(toPick - 1, sumX - (pair[i][0]<<1), sumY - (pair[i][1]<<1), i + 1);

	}
}