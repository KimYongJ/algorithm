// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int inc		= Integer.parseInt(st.nextToken());
		int work	= Integer.parseInt(st.nextToken());
		int dec		= Integer.parseInt(st.nextToken());
		int MAX		= Integer.parseInt(st.nextToken());
		int energy	= 0;
		int result	= 0;
		
		for(int i=1; i<=24; i++) 
		{
			if(energy + inc <= MAX) 
			{
				energy += inc;
				result += work;
			}
			else 
			{
				energy -= dec;
				if(energy < 0) 
				{
					energy = 0;
				}
			}
		}
		System.out.print(result);
	}
}