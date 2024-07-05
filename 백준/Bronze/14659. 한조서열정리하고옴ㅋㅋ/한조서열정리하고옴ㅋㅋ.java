// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int result	= 0;
		int N		= Integer.parseInt(br.readLine());
		st			= new StringTokenizer(br.readLine());
		int cnt		= 0;
		int num1	= 0;
		for(int i=0; i<N; i++) 
		{
			int num2 = Integer.parseInt(st.nextToken());
			if(num1 > num2) 
			{
				cnt++;
			}
			else 
			{
				result	= Math.max(result, cnt);
				num1	= num2;
				cnt		= 0;
			}
		}
		System.out.print(Math.max(result, cnt));
	}
}