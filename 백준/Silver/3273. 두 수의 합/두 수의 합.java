//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/3273
class Main{
	public static void main(String[] args)throws Exception{
		int cnt			= 0;
		int N			= read();
		int arr[]		= new int[N];
		boolean visit[] = new boolean[1_000_001];
		
		for(int i=0; i<N; i++)
			visit[arr[i] = read()] = true;
		
		int x = read();
		
		for(int num : arr)
		{
			int target = x - num;
			if(0 <= target && target <= 1_000_000 && visit[target])
				++cnt;
		}
		System.out.print(cnt >> 1);
	}
    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}