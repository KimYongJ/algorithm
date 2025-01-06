//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2230
import java.util.Arrays;
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		int M		= read();
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();
		
		Arrays.sort(arr);
		
		int l = 0;
		int r = 1;
		int min = ~(1<<31);
		while(r<N && min != M)
		{
			int diff = arr[r] - arr[l];

			if(diff < M)
				++r;
			else
			{
				++l;
				min = Math.min(min, diff);
			}
		}
		
		System.out.print(min);
	}
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
}