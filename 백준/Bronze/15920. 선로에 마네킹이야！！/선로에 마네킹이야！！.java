//https://www.acmicpc.net/problem/15920
//2초 512MB
//8 // 문자열 길이 (1<=10)
//PPPWWWPP // W는 1초 기다리는 행동, P는 레버를 당기는 행동
//답 : 1
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean isDown = true;
		boolean all = false;
		int loc = 0;
		String str = br.readLine();
		for(int i=0; i<N && loc < 2; i++)
		{
			char c = str.charAt(i);
			if(c == 'W')
			{
				++loc;
				continue;
			}
			
			if(loc == 1)
				all = true;
			
			isDown = !isDown;
		}
		
		int res = 0;
		
		if(loc == 2)
			res = all ? 6 : (isDown ? 5 : 1);
		
		System.out.print(res);
	}
}