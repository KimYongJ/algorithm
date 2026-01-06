//https://www.acmicpc.net/problem/5675
//1초 128MB
//90
//65
//66
//67
//128
//0
//180
//답
//Y
//N
//Y
//N
//N
//Y
//Y
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			String str = br.readLine();
			if(str == null || str.length() == 0)
				break;
			
			sb.append(Integer.parseInt(str) % 6 == 0 ? 'Y' : 'N').append('\n');
		}
		System.out.print(sb);
	}
}