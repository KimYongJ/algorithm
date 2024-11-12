//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1977

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s	= Integer.parseInt(br.readLine())-1;
		int e	= Integer.parseInt(br.readLine());
		int sum	= 0;
		int min	= 0;
		
		boolean findMin = false;
		while(++s <= e)
		{
			double num = Math.sqrt(s);
			if(num == (int)num)
			{
				sum += s;
				if(!findMin)
				{
					findMin = true;
					min = s;
				}
			}
		}
		if(findMin) {
			System.out.println(sum);
			System.out.println(min);
		}
		else
			System.out.print(-1);
	}
}