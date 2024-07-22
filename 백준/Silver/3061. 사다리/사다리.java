// https://github.com/kimyongj/algorithm
class Main{
    private static int read() throws Exception {
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        boolean minus = false;
        if (c == '-') {
            minus = true;
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        if (minus) return -val;
        return val;
    }
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-->0) 
		{
			int cnt = 0;
			int N = read();
			int arr[] = new int[N];
			for(int i=0; i<N; i++) 
			{
				arr[i] = read();
			}
			for(int i=1; i<N; i++)					// 1번 인덱스부터 맞춰감
			{
				for(int j=i-1; j>=0 && arr[j] > arr[j + 1]; j--) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
					cnt++;
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.print(sb.toString());
	}
}