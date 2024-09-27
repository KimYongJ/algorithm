//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/14003
class Main{
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	public static int getIdx(int s, int e, int target, int LIS[]) {
		int idx = 0;
		while(s <= e)
		{
			int mid = (s + e) >> 1;
			if(LIS[mid] < target)
				s = mid + 1;
			else {
				e = mid - 1;
				idx = mid;
			}
		}
		return idx;
	}
	public static void main(String [] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int N		= read();		// 1<=백만
		int len		= 1;
		int arr[]	= new int[N];	// -십억<=+십억
		int LIS[]	= new int[N];	// 수열을 임시 저장할 배열
		int idx[]	= new int[N];	// 각 숫자에 대해 LIS에 담긴 위치를 담을 배열(LIS에 담길 때 길이)
		
		for(int i=0; i<N; i++)
			arr[i] = read();
		
		LIS[0] = arr[0];
		idx[0] = 0;
		
		for(int i=1; i<N; i++)
			if(LIS[len - 1] < arr[i])
			{
				LIS[len] = arr[i];
				idx[i] = len;
				len++;
			}
			else
			{
				int idx2 = getIdx(0, len - 1, arr[i], LIS);
				LIS[idx2] = arr[i];
				idx[i] = idx2;
			}

		sb.append(len).append('\n');
		
		int result[] = new int[len--];					// 최종 결과를 담을 배열
		
		for(int i=N-1; i>=0; i--)
			if(idx[i] == len)
				result[len--] = arr[i];
		
		for(int r : result)
			sb.append(r).append(' ');
		
		System.out.print(sb.toString());
	}
}
/*
16
-60 -41 -100 8 -8 -52 -62 -61 -76 -52 -52 14 -11 -2 -54 46 
출
7
-100 -62 -61 -52 -11 -2 46 
*/