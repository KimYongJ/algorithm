//https://www.acmicpc.net/problem/3985
//1초 128MB
//10 // 롤케익 길이(1<=1000)
//3 // 방청객수(1<=1000)
//2 4 // 조각의 시작 끝 인덱스(1<=롤케익길이)
//7 8
//6 9
//답
//3 // 가장 많은 조각을 받을 것으로 기대한 방청객 번호 출력(중복이면 번호작은것)
//1 // 가장 많은 조각을 받은 방청객의 번호를 출력(중복이면 번호작은것)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int maxIdx = N;
		int realMaxIdx = N;
		int maxCnt = 0;
		int realMaxCnt = 0;
		int num[] = new int[L + 1];
		
		for(int idx = 1; idx<=N; idx++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int want = e - s + 1;
			
			if(maxCnt < want){// 가장 많이 받길 원하는 인덱스 
				maxIdx = idx;
				maxCnt = want;
			}
			
			int cnt = 0;
			
			while(s<=e)
			{
				if(num[s] == 0)
				{
					num[s] = 1;
					++cnt;
				}
				++s;
			}
			
			if(realMaxCnt < cnt)
			{
				realMaxCnt = cnt;
				realMaxIdx = idx;
			}
		}
		
		
		System.out.print(new StringBuilder().append(maxIdx).append('\n').append(realMaxIdx));
	}
}