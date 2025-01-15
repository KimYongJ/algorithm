//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/31785
//1초 / 1024MB
class Main{
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T		= read();
		int arr[]	= new int[500_001];
		int s		= 0;
		int e		= 0;
		int total	= 0;
		while(T-->0)
		{
			int type = read();
			
			if(type == 1)
			{
				total += arr[e++] = read();
			}
			else
			{
				int mid = s + (e - s)/2;
				
				int sum1 = getSum(arr, s, mid);
				int sum2 = total - sum1;
				if(sum1 <= sum2)
				{
					sb.append(sum1).append('\n');
					s = mid;
					total -= sum1;
				}
				else
				{
					sb.append(sum2).append('\n');
					e = mid;
					total -= sum2;
				}
			}
		}
		
		while(s<e)
			sb.append(arr[s++]).append(' ');
		
		System.out.print(sb);
	}
	public static int getSum(int arr[], int s, int e) {
		int sum = 0;
		while(s<e)
			sum += arr[s++];
		return sum;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}