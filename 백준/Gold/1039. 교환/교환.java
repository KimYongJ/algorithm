// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;

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
	static boolean visit[][];
	
	// 처음 주어지는 START번호의 자릿수와 10단위 기준 값 구하는 함수
	public static void setting(int start) {
		digit=7;
		base = 1_000_000;
		while(start / base == 0) {
			base /= 10;
			digit--;
		}
	}
	// 빠른 입력을 위한 함수
	public static int read() throws Exception{
		int c, n = System.in.read() & 15;
		boolean negative = n == 13;
		if(negative) n = System.in.read() & 15;
		while((c = System.in.read()) > 32) n = (n<<3) + (n<<1) + (c & 15);
		if(c == 13) System.in.read();
		return negative?~n+1:n;
	}
	// num의 i와 j의 자릿수를 바꾸는 함수
	public static int makeNumber(int i, int j, int num) { 
		int typeI = (int) Math.pow(10,digit - i - 1); 	// i의 십의 자릿 수
		int digitI = (num/typeI) % 10; 					// i자리의 숫자 정보
		int typeJ = (int) Math.pow(10,digit - j - 1); 	// j의 십의 자릿 수
		int digitJ = (num/typeJ) % 10; 					// j자리의 숫자 정보
		// 이하 구한 자릿수를 빼고 더해 서로 바꾸는 과정.
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
		START 				= read();
		K 					= read();
		q 					= new ArrayDeque<>();
		visit				= new boolean[K+1][1_000_001];
			
		q.add(new Point(START , 0)); 	// 시작 숫자와 k를 몇번 썼는지 넣음
		setting(START); 				// digit과 base를 셋팅			
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
					START = makeNumber(i, j, now.number); 	// 새로운 숫자 생성
					if(!visit[now.k+1][START])  			// k+1번째에 해당 숫자가 있는지 체크 
					{
						visit[now.k+1][START] = true;		// 없다면 set, q에 추가
						q.add(new Point(START, now.k+1));
					}
				}
			}
		}
		System.out.println(result == 0 ? -1 : result);
	}
}