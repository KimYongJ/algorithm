//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/18185
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static void main(String[] args)throws Exception{
		int min, res= 0;
		int i, N	= read(); // 라면공장개수 N (3<=만개)
		int arr[]	= new int[N+2];
		for(i=0; i<N; i++)
			arr[i] = read();
		
		i = -1;
		while(++i<N)
			if(arr[i] != 0)
			{
				res += 3 * arr[i];
				
				min = Math.min(arr[i], arr[i+1]);
				res += 2 * min;
				arr[i+1] -= min;
				
				min = Math.min(min, arr[i+2] - Math.min(arr[i+1], arr[i+2]));
				res += 2 * min;
				arr[i+2] -= min;
			}
		System.out.print(res);
	}
}
/*
10
7 5 3 2 1 7 5 3 2 1
답 89
 * */