//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10025
class Main{
    private static int read() throws Exception {
        int val = 0;
        int c = System.in.read();
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
	public static void main(String[] args)throws Exception{
		final int MAX = 1_000_000;
		int N		= read();	// 양동이개수(1<=십만)
		int K		= read()*2;	// 이동가능거리(1<=이백만)
		int arr[]	= new int[MAX+1];
		
		for(int i=0; i<N; i++)
		{
			int val = read();
			int idx = read();
			arr[idx]= val;
		}
		
		int len = Math.min(MAX, K);
		int sum = 0;
		for(int i=0; i<=len; i++)
			sum += arr[i];
		
		int max = sum;
		for(int i=K+1; i<=MAX; i++)
		{
			sum += arr[i] - arr[i-K-1];
			max = Math.max(sum, max);
		}
		
		System.out.print(max);
	}
}