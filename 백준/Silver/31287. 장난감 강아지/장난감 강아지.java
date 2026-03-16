//https://www.acmicpc.net/problem/31287
//1초 1024MB
//4 2 // 문자열길이(1<=2000), 반복 횟수(1<=10^9)
//URLD
//답 : YES
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//문자열 길이(1<=2000)
		int T = Math.min(N,Integer.parseInt(st.nextToken()));//반복 횟수(1<=10^9)
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		
		while(T-->0) sb.append(str);
		
		int y = 0;
		int x = 0;
		
		for(int i=0; i<sb.length(); i++)
		{
			switch(sb.charAt(i))
			{
			case 'U' : y--; break;
			case 'D' : y++; break;
			case 'R' : x++; break;
			case 'L' : x--; break;
			}
			
			if(y == 0 && x == 0)
			{
				System.out.print("YES");
				return;
			}
		}
		
		System.out.print("NO");
	}
}
