// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    static int 	N;
    static long result = -1;
    public static boolean DFS(int depth, long sum) {
        if (depth == 0) {
            if (--N == 0)
            {
                result = sum;
                return true;
            }
            return false;
        }
        for(long i=0; i<=9; i++)			// 둘째 자리부터 0~9를 대입함
            if(sum%10 > i)					// 이전 숫자보다 작아야 만 DFS진행
                DFS(depth-1, sum*10L + i);
        return false;
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    
	    if (N < 10) {
	        System.out.print(N);
	    }
	    else 
	    {
	    	N -= 9; // N에 9를 빼서 9까지 경우의 수를 제거 한다.
	        Loop:
	        for (int i = 1; i < 10; i++)	// 자리수를 지정하는 것 2자리수는 1을 전달하기 때문에 i는 1부터 시작
	            for (int s = 1; s <= 9; s++)// 첫시작 숫자 1~9까지 바인딩
	                if(DFS(i, s))
	                    break Loop;
	        System.out.print(result);
	    }
    }
}
