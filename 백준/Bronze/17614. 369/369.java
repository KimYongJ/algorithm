//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17614
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int C = 0;
		for(int i=3,num = 3; i<=N; num = ++i)
			while(num != 0)
			{
				int mod = num % 10;
				if(mod == 3 || mod == 6 || mod == 9)
					++C;
				num /= 10;
			}

		System.out.print(C);
	}
}