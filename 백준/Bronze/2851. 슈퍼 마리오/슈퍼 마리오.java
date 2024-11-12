//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2851

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum		= 0;
		int before	= 0;
		for(int i=0; i<10; i++)
		{
			sum += Integer.parseInt(br.readLine());
			if(100 <= sum)
			{
				int abs1 = 100 - before;
				int abs2 = sum - 100;
				if(abs1 < abs2)
					sum = before;
				break;
			}
			before = sum;
		}
		System.out.print(sum);
	}
}