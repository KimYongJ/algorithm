//https://www.acmicpc.net/problem/12605
//5초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++)
		{
			String[] str = br.readLine().split(" ");
			sb.append("Case #").append(i).append(": ");
			
			for(int j=str.length-1; j>=0; j--)
				sb.append(str[j]).append(' ');
			
			sb.append('\n');
		}
		System.out.print(sb);
	}
}