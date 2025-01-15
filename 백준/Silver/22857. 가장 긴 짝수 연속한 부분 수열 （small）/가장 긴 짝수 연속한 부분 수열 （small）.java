//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/22857
//1초 / 1024MB
class Main{
	public static void main(String[] args)throws Exception{
		int N = read();	// 수열길이(1≤오만)
		int K = read();	// 삭제최대횟수(1≤K≤백)
		int arr[] = new int[N+1];
		
		for(int i=0; i<N; i++)
			arr[i] = read() % 2;
		
		int s = 0; 
		int e = 0;
		int k = 0;
		int m = 0;
		while(e < N)
		{
			if(arr[e] == 1)	// 값이 홀수면 k를 추가한다.
				++k;
			
			while(K < k)	// K개 초과시 s를 증가시켜 k를 하나 줄인다, 이 때 k를 하나 줄이면 s의 값증가는 멈춘다
				if(arr[s++] == 1)
					--k;
			// e와 s+1해서 e와s범위안에 숫자가 몇개인지 헤아린 후, 홀수 개수인 k를 빼주면 길이가 된다.
			m = Math.max(m, e-s-k+1);
			
			++e;
		}
		System.out.print(m);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}