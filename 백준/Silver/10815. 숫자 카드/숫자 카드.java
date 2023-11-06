// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N,M, left, right, mid, arr[];
	
    public static void main(String[] args)throws Exception{
    	N = read();
    	arr = new int[N];
    	for(int i=0; i<N; i++) // 상근이의 숫자카드를 넣는다.  
    		arr[i] = read();
    	
    	Arrays.sort(arr); // 상근이 카드를 정렬 한다.
    	
    	M = read();
    	for(int i=0; i<M; i++) {
    		int num = read();
    		sb.append(find(num)).append(" ");
    	}
    	System.out.println(sb.toString());
    }
    public static int find(int num) {
    	int result = 0;
    	left = 0;
    	right = N-1;
    	while(left<=right) {
    		mid = right+left>>1;
    		if(arr[mid]==num) {
    			result = 1; break;
    		}else if(arr[mid]<num) {
    			left = mid+1;
    		}else {
    			right = mid-1;
    		}
    	}
    	return result;    	
    }
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative) n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return isNegative ? ~n + 1 : n;
    }
}
