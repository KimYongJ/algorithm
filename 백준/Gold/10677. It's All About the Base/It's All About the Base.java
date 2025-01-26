//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10677
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			// 가능한 진수 : 10~15,000진수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());	// X진수로 쓰인 숫자 N
			int num2 = Integer.parseInt(st.nextToken());	// Y진수로 쓰인 숫자 N
			
			for(int i=10; i<=15_000; i++)
			{
				int dec = decimal(num1, i);	// num1을 모든 진법을 사용해 10진수로 변경
				// dec을, 이진탐색으로 num2로 만들 수 있는지 탐색
				
				int s	= 10;
				int e	= 15_000;
				int res = 0;
				while(s <= e)
				{
					int mid = (s + e) >> 1;
					int dec2= decimal(num2, mid);
					if(dec == dec2) {
						res = mid;
						break;
					}
					if(dec < dec2)
						e = mid - 1;
					else s = mid + 1;
				}
				
				
				if(res != 0)
				{
					sb.append(i).append(' ').append(res).append('\n');
				}
			}
			
		}
		System.out.print(sb);
	}
	public static int decimal(int num, int d) {
		int res = 0;
		int mul = 1;
		while(num != 0)
		{
			res += (num%10) * mul;
			num/=10;
			mul *= d;
		}
		
		return res;
	}
}