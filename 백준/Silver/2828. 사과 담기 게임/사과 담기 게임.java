// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N		= Integer.parseInt(st.nextToken());
		int end		= Integer.parseInt(st.nextToken());
		int T		= Integer.parseInt(br.readLine());
		int start	= 1;
		int result	= 0;
		int move;
		while(T-- > 0) 
		{
			int num	= Integer.parseInt(br.readLine());
			if(num < start)
			{
				result	+= move = (start-num);
				start	-= move;
				end		-= move;
			}
			else if(end < num) 
			{
				result	+= move = num - end;
				start	+= move;
				end		+= move;
			}
		}
		System.out.print(result);
	}
}