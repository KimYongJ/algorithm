//https://www.acmicpc.net/problem/34002
//1초 1024MB
//10 20 30 // 이벤트맵, 수련관, vip 에서 얻을 수 있는 경험치(1<=100)
//1 2 // 수련관, vip 입장권 개수(1<=10)
//1 // 현재 레벨(1<=249)
//답 : 2340
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // 분당 얻을 수 있는 경험치
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int b1 = Integer.parseInt(st.nextToken());
		int c1 = Integer.parseInt(st.nextToken());
		
		int exp = (250 - Integer.parseInt(br.readLine())) * 100;
		
		int time = 0;
		if(c1 > 0)
		{
			int sum = c1 * 30 * c;
			if(exp - sum > 0)
			{
				exp -= sum;
				time += c1 * 30;
			}
			else
			{
				time += ceilDiv(exp, c);
				System.out.print(time);
				return;
			}
		}
		if(b1 > 0)
		{
			int sum = b1 * 30 * b;
			
			if(exp - sum > 0)
			{
				exp -= sum;
				time += b1 * 30;
			}
			else
			{
				time += ceilDiv(exp, b);
				System.out.print(time);
				return;
			}
		}
		
		time += ceilDiv(exp, a);
		
		System.out.print(time);
	}
	static int ceilDiv(int exp, int a) {
		return (exp + a - 1) / a;
	}
}