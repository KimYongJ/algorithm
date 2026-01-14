//https://www.acmicpc.net/problem/29267
//2초 1024MB
//save - 현재 탄약 수를 저장합니다.
//load - 마지막으로 저장된 탄약 수를 로드합니다.
//shoot - 탄약 한 발을 소모합니다.
//ammo - 한 상자를 선택해 k개의 탄약을 추가합니다.
//
//7 3 // 행동 횟수(1<=100), 한 상자에 들어있는 탄약수(1<=100)
//ammo
//save
//shoot
//ammo
//load
//shoot
//load
//답
//3
//3
//2
//5
//3
//2
//3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 행동 횟수(1<=100)
		int K = Integer.parseInt(st.nextToken());// 한 상자에 들어있는 탄약수(1<=100)
		int	bullet = 0;
		int save = 0;
		
		while(N-->0)
		{
			String cmd = br.readLine();
			if("ammo".equals(cmd)) bullet += K;
			else if("save".equals(cmd)) save = bullet;
			else if("shoot".equals(cmd)) bullet--;
			else bullet = save;
			
			sb.append(bullet).append('\n');
		}
		System.out.print(sb);
	}
}