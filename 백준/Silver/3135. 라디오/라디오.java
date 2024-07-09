// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s	= Integer.parseInt(st.nextToken());
		int e	= Integer.parseInt(st.nextToken());
		int n	= Integer.parseInt(br.readLine());
		int min = Math.abs(s-e);
		int next;
		while(n-->0) 
		{
			next = Math.abs(e - Integer.parseInt(br.readLine())) + 1;
			if(min > next) 
			{
				min = next;
			}
		}
		System.out.print(min);
	}
	
}