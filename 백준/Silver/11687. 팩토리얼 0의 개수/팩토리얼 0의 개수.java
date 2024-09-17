//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11687
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
    public static int getZeroCnt(int N){// N!에서 뒤에 붙는 0의 개수를 구하는 함수
        int cnt = 0;				// 0의 개수를 저장하는 변수
        for(int i=5; i<=N; i*=5)	// 5의 배수마다 N을 나누며 N!에 포함된 5의 개수를 세는 방식
            cnt += N/i;				// N을 i로 나누어 5의 배수가 몇 개 있는지 카운트
        return cnt;					// 결과적으로 N!에 포함된 0의 개수를 반환
    }

    public static void main(String[] args)throws Exception{
        int M	= read();					// 목표로 하는 0의 개수 M (1 <= M <= 1억)
        int s	= 1;						// 시작점은 1
        int e	= 1_000_000_000;			// 끝점은 10억 (최대값 설정)
        int res = -1;						// 결과값을 저장할 변수, 초기값은 -1 (찾지 못했을 때)
        
        while(s <= e)
        {
            int mid		= (s + e) >> 1; 	// 중간값을 구함 (s + e) / 2와 동일
            int zeroCnt = getZeroCnt(mid);	// mid의 팩토리얼에서 0의 개수를 구함

            if(M <= zeroCnt)				// 현재 중간값의 0의 개수가 목표값 M보다 크거나 같을 때
            {
                e = mid - 1; 				// 0의 개수가 더 크거나 같다면, 더 작은 값에서 탐색
                if(M == zeroCnt)			// 0의 개수가 정확히 M개인 경우 res에 mid 저장
                    res = mid;
            }
            else 
                s = mid + 1;				// 0의 개수가 M보다 적을 때는 더 큰 값에서 탐색
        }
        System.out.print(res);
    }
}