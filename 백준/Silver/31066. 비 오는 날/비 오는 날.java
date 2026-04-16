//https://www.acmicpc.net/problem/31066
//1초 1024MB
//3 // 테스트 케이스 수(1<=1,000)
//7 2 2 // N(학생수(1<=10), M우산개수(1<=10), K(한우산에 허용된 최대인원)
//2 1 1
//1 3 5
//답
//3 // 모든 학생이 건너갈 수 있는 최소 횟수
//-1 // 건너지 못하면-1출력
//1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());// N(학생수(1<=10)
			int M = Integer.parseInt(st.nextToken());// M우산개수(1<=10)
			int K = Integer.parseInt(st.nextToken());// K(한우산에 허용된 최대인원)
			int cnt = 0;
			
			while(true)
			{
				int prevN = N;
				N -= M * K;
				++cnt;
				
				if(N <= 0) break;
				
				++N;
				
				if(prevN == N) {
					cnt = -1;
					break;
				}
				++cnt;
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb);
	}
}