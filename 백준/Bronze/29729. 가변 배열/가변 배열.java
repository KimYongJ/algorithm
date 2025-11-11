//https://www.acmicpc.net/problem/29729
//1초 1024MB
//1 5 1// 초기 크기, 저장명령 개수, 삭제명령 개수
//1 // 1은 저장명령, 0은 삭제 명령
//1
//1
//1
//0
//1
//배열의 최대 크기 : 4
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int o = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		int idx = 0;

		while(o-->0)
		{
			idx += Integer.parseInt(br.readLine()) == 0 ? -1 : 1;
			if(idx > N)
				N <<= 1;
		}
		System.out.print(N);
	}
}