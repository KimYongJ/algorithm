// https://github.com/KimYongJ/algorithm

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main{
	static class Point{
		int number, k;
		Point(int number, int k){
			this.number = number;
			this.k = k;
		}
	}
	static int START, K, result;
	static int digit, base; // START의 자릿수와 , 10단위 기준 값
	static ArrayDeque<Point> q;
	static HashSet<Integer> set[];
	
	// 처음 주어지는 START번호의 자릿수와 10단위 기준 값 구하는 함수
	public static void setting(int start) {
		digit=7;
		base = 1_000_000;
		while(start / base == 0) {
			base /= 10;
			digit--;
		}
	}
	// num의 i와 j의 자릿수를 바꾸는 함수
	public static int makeNumber(int i, int j, int num) {
		// i의 숫자를 알아 내야한다. 
		int typeI = (int) Math.pow(10,digit - i - 1); // i의 십의 자릿 수
		int digitI = (num/typeI) % 10; // i자리의 숫자 정보
		int typeJ = (int) Math.pow(10,digit - j - 1); // j의 십의 자릿 수
		int digitJ = (num/typeJ) % 10; // j자리의 숫자 정보
		
		
		num -= typeI * digitI;
		num -= typeJ * digitJ;
		num += typeI * digitJ;
		num += typeJ * digitI;
		
		if(num < base) { // 자릿 수 교환시 맨 앞이 0일 경우
			num = 0;
		}
		return num;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br 	= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st 	= new StringTokenizer(br.readLine());
		q 					= new ArrayDeque<>();
		START 				= Integer.parseInt(st.nextToken());
		K 					= Integer.parseInt(st.nextToken());
		set 				= new HashSet[K+1];
		q.add(new Point(START , 0)); // 시작 숫자와 k를 몇번 썼는지 넣음
		setting(START); // digit과 base를 셋팅
		for(int i=0; i<=K; i++)
			set[i] = new HashSet<Integer>() {{add(0);}};
			
		while(!q.isEmpty()) 
		{
			Point now = q.poll();
			
			if(now.k == K) 
			{ // K번 연산시 결과 비교 후 다음 연산
				result = Math.max(result, now.number);
				continue;
			}
			
			for(int i=0; i<digit-1; i++) 
			{
				for(int j=i+1; j< digit; j++) 
				{
					START = makeNumber(i, j, now.number);
					if(!set[now.k+1].contains(START)) 
					{
						set[now.k+1].add(START);
						q.add(new Point(START, now.k+1));
					}
				}
			}
		}
		System.out.println(result == 0 ? -1 : result);
	}
}