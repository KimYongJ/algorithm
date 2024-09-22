//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12738
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
        {n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static int getIdx(int LIS[], int target, int e) {
		int s	= 0;
		int res = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(target <= LIS[mid])
			{
				e = mid - 1;
				res = mid;
			}
			else
				s = mid + 1;
		}
		return res;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int arr[]	= new int[N];
		
		for(int i=0; i<N; i++)
			arr[i]	= read();
		
		int len		= 1;
		int LIS[]	= new int[N];
		LIS[0]		= arr[0];
		
		for(int i=1; i<N; i++)
		{
			if(LIS[len-1] < arr[i])
				LIS[len++] = arr[i];
			else
				LIS[getIdx(LIS, arr[i], len - 1)] = arr[i];
		}
		System.out.print(len);
	}
}