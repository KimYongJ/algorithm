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
				if(100 - before < sum - 100)
					sum = before;
				break;
			}
			before = sum;
		}
		System.out.print(sum);
	}
}