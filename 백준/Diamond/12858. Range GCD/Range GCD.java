//https://www.acmicpc.net/problem/12858
//2초 512MB
//4//원소 개수 (1 ≤ N ≤ 100,000)
//6 3 38 49// 수열의 원소 값(1<=10억)
//5// 쿼리 수(1 ≤ Q ≤ 100,000)
//0 1 3// T A B : T가 0이라면, 수열의 A번째 원소부터 B번째 원소까지의 최대공약수를 출력해야 한다.
//9 2 2// T A B : T가 0이 아니라면, 수열의 A번째 원소부터 B번째 원소까지에 T라는 수를 더하는 연산이다.
//0 1 2// A <= B(1<=N)
//6 3 3
//0 3 4
//T가 0일 때마다 해당 구간의 최대공약수 출력
//1
//6
//1

class Main{
	
	static int N, Q;
	static long arr[];
	static long lazy[];
	static long diffSeg[];// 리프노드는 배열의 차를 저장하고, 내부노드는 gcd를 저장한다.
	static long pointSeg[];// 이 세그먼트는 그냥 리프노드에 값만 저장하고 lazy 업데이트를 적용할 수 있다.
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		StringBuilder sb = new StringBuilder();
		N = in.nextInt();//원소 개수 (1 ≤ N ≤ 100,000)
		arr = new long[N + 2];
		lazy = new long[N * 4];
		diffSeg = new long[N * 4];
		pointSeg = new long[N * 4];
		
		for(int i=1; i<=N; i++)// 수열의 원소 값(1<=10억)
			arr[i] = in.nextInt();
		
		init(1, 1, N);// diffSeg와 pointSeg를 업데이트한다.
		
		Q = in.nextInt();// 쿼리 수(1 ≤ Q ≤ 100,000)
		while(Q-->0)
		{
			int T = in.nextInt();
			int A = in.nextInt();
			int B = in.nextInt();
			
			if(T == 0)// T A B : T가 0이라면, 수열의 A번째 원소부터 B번째 원소까지의 최대공약수를 출력해야 한다.
			{
				long first = pointQuery(1, 1, N, A);
				long diff = diffQuery(1, 1, N, A, B-1);
				
				sb.append( gcd( first, Math.abs(diff)) ).append('\n');
				continue;
			}
			
			// T A B : T가 0이 아니라면, 수열의 A번째 원소부터 B번째 원소까지에 T라는 수를 더하는 연산이다.
			pointUpdate(1, 1, N, A, B, T);
			diffUpdate(1, 1, N, A - 1, T);
			diffUpdate(1, 1, N, B, -T);
		}
		System.out.print(sb);
	}
	public static void diffUpdate(int treeNode, int s, int e, int targetIdx, long value) {
		if(e < targetIdx || targetIdx < s)
			return;
		if(s == e)
		{
			diffSeg[treeNode] += value;
			return;
		}
		
		int mid = (s + e) >> 1;
		
		diffUpdate(treeNode << 1, s, mid, targetIdx, value);
		diffUpdate(treeNode << 1 | 1, mid + 1, e, targetIdx, value);
		
		diffSeg[treeNode] = gcd(diffSeg[treeNode << 1], diffSeg[treeNode << 1 | 1]);
	}
	public static long diffQuery(int treeNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left <= s && e <= right)
			return diffSeg[treeNode];
		
		int mid = (s + e) >> 1;
		
		long l = diffQuery(treeNode << 1, s, mid, left, right);
		long r = diffQuery(treeNode << 1 | 1, mid + 1, e, left, right);
		
		return gcd(l, r);
	}
	public static void pointUpdate(int treeNode, int s, int e, int left, int right, long value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		pointUpdate(treeNode << 1, s, mid, left, right, value);
		pointUpdate(treeNode << 1 | 1, mid + 1, e, left, right, value);
	}
	public static long pointQuery(int treeNode, int s, int e, int targetIdx) {
		propagate(treeNode, s, e);
		
		if(e < targetIdx || targetIdx < s)
			return 0;
		if(s == e)
			return pointSeg[treeNode];
		
		int mid = (s + e) >> 1;
		
		return targetIdx <= mid ? 
				pointQuery(treeNode << 1, s, mid, targetIdx)
				: pointQuery(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] == 0)
			return;
		
		if(s == e)
			pointSeg[treeNode] += lazy[treeNode];
		
		if(s != e)
		{
			lazy[treeNode << 1] += lazy[treeNode];
			lazy[treeNode << 1 | 1] += lazy[treeNode];
		}
		
		lazy[treeNode] = 0;
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			pointSeg[treeNode] = arr[s];
			diffSeg[treeNode] = arr[s + 1] - arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		diffSeg[treeNode] = gcd(diffSeg[treeNode << 1], diffSeg[treeNode << 1 | 1]);
	}
	public static long gcd(long a, long b) {
		if(b == 0) return a;
		return gcd(b, a % b);
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }
	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }
	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}