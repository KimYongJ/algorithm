//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1526
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		LOOP : 
		while(N >=4 )
		{
			int num = N;
			while(num != 0)
			{
				int mod = num % 10;
				if(mod != 7 && mod != 4)
				{
					--N;
					continue LOOP;
				}
				num /= 10;
			}
			System.out.print(N);
			return;
		}
	}
}