//https://www.acmicpc.net/problem/7240
//1초 1024MB
//3 4 // 트랙 구간 수, 제한속도
//8 // 구간 지날때의 속도 변화량
//-3
//5
//최종 속도 : 9
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 트랙 구간 수
		int K = Integer.parseInt(st.nextToken());// 제한속도
		int speed = 0;
		
		for(int i=0; i<N; i++)
		{
			if(speed > K) speed = Math.max(speed - 1, 0);
			
			speed += Integer.parseInt(br.readLine());
		}
		
		System.out.print(speed);
	}
}
