//https://www.acmicpc.net/problem/13871
//2초 512MB
//8 8 3 // 충전소 개수, 명령어 개수, 확인을 원하는 위치
//1 -1 1 1 1 -1 1 1 // 1은 시계방향으로 한칸, -1은 그 반대 
//답 : 2
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken()) - 1;
		int cnt = G == 0 ? 1 : 0;
		int idx = 0;
		
		st = new StringTokenizer(br.readLine());
		while(C-->0)
		{
			idx += Integer.parseInt(st.nextToken());
			idx %= N;
			
			if(idx < 0) idx += N;
			
			if(G == idx) cnt++;
		}
		System.out.print(cnt);
	}
}