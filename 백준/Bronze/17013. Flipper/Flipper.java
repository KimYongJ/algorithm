//https://www.acmicpc.net/problem/17013
//1초 512MB
//HV // 1<=1,000,000 , H는 수직(양옆)뒤집기, V는 수평(위아래)뒤집기
//답
//4 3
//2 1
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = 1, b = 2, c = 3, d = 4;
		
		
		for(char cmd : br.readLine().toCharArray())
		{
			if(cmd == 'V')
			{
				int tmp = a;
				a = b;
				b = tmp;
				tmp = c;
				c = d;
				d = tmp;
				continue;
			}
			
			int tmp = a;
			a = c;
			c = tmp;
			tmp = b;
			b = d;
			d = tmp;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(a).append(' ').append(b).append('\n').append(c).append(' ').append(d);
		System.out.print(sb);
	}
}