// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

    static int N;
    static int cnt;
    static long result = -1;
    public static boolean DFS(int depth, long sum) {
        if (depth == 0) {
            if (N == ++cnt)
            {
                result = sum;
                return true;
            }
            return false;
        }
        for(long i=0; i<=9; i++)
            if(sum%10 > i)
                DFS(depth-1, sum*10L + i);
        
        return false;
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    cnt = 9;
	    if (N < 10) 
	    {
	        System.out.print(N);
	    } 
	    else 
	    {
	        Loop:
	        for (int i = 1; i <= 10; i++)
	            for (int s = 1; s <= 9; s++)
	                if(DFS(i, s))
	                    break Loop;
	        System.out.print(result);
	    }
    }
}