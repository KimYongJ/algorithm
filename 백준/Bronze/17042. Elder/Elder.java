//https://www.acmicpc.net/problem/17042
//1초 512MB
//A // 지팡이 초기 소유 사람
//3 // 결투 수
//B A // 왼쪽이 이긴것
//C B
//D A
//답
//C // 최종 소유자
//3 // 지팡이를 소유했던 사람의 총 수
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean visit[] = new boolean[27];
		
		int cnt = 1;
		int idx = br.readLine().charAt(0) - 'A';
		
		visit[idx] = true;
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int win = st.nextToken().charAt(0) - 'A';
			int lose = st.nextToken().charAt(0) - 'A';
			
			if(lose == idx) {
				idx = win;
				if(!visit[idx]) {
					visit[idx] = true;
					++cnt;
				}
			}
		}
		System.out.printf("%c\n%d",(idx+'A'), cnt);
	}
}