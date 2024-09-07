import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		if(N == 0) 
		{
			System.out.print(0);
			return;
		}
		
		long num	= 0;
		int res		= 0;

		while(num < N) 
		{
			res++;
			if(num == 0) num = 1;
			else num <<= 1;
		}

		System.out.print(res);
	}
}