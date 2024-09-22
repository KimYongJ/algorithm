//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12738
import java.util.Arrays;
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
        {n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static void main(String[] args)throws Exception{
		int N		= read();
		int len		= 0;
		int arr[]	= new int[N];
		int LIS[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i]	= read();
		
		for(int i=0; i<N; i++)
		{
			int idx = Arrays.binarySearch(LIS, 0, len, arr[i]);
			if(idx < 0)
				idx = ~idx;
			if(idx == len)
				len++;
			LIS[idx] = arr[i];
		}
		System.out.print(len);
	}
}