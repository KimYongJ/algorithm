//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10677
//1초 / 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			// 가능한 진수 : 10~15,000진수
			String str = br.readLine();
			int A[] = new int[3];// X진수로 쓰인 숫자 N
			int B[] = new int[3];// Y진수로 쓰인 숫자 N
			
			for(int i=0; i<3; i++)A[i] = str.charAt(i)-'0';
			for(int i=0; i<3; i++)B[i] = str.charAt(i+4)-'0';
			
			int x = 10;
			int y = 10;
			while(x<=15_000 && y<=15_000)
			{
				int dec1 = decimal(A, x);
				int dec2 = decimal(B, y);
				
				if(dec1 == dec2)
				{
					sb.append(x).append(' ').append(y).append('\n');
					break;
				}
				if(dec1 < dec2)
					++x;
				else
					++y;
			}
			
		}
		System.out.print(sb);
	}
	public static int decimal(int[] num, int d) {
		return d*d*num[0] + d*num[1] + num[2];
	}
}