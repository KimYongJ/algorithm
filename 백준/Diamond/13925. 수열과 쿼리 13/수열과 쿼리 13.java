//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/13925
//2초 512MB

class Main{
	
	static final long MOD = 1_000_000_007;
	static int N, len, arr[];
	static long tree[];
	static Node[] lazy;

	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		N		= in.nextInt();	// 1<=100,000
		len 	= N * 4;
		arr 	= new int[N + 1];
		tree	= new long[len];
		lazy	= new Node[len];
		
		for(int i=1; i<=N; i++)
			arr[i] = in.nextInt();// 1<=1,000,000,000
		
		// 트리 초기화 
		init( 1, 1, N );
		// lazy 초기화 
		for(int i=0; i<len; i++)
			lazy[i] = new Node(0, 1);
		
		int Q = in.nextInt();	// 1<= 100,000
		
		while(Q-->0)
		{
			int f = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();
			if(f == 4)
			{				
				sb.append( query( 1, 1, N, x, y) )
					.append('\n');
			}
			else
			{
				long v = in.nextInt(); // 1<=1,000,000,000
				
				update( 1, 1, N, x, y, v, f );
			}
		}
		System.out.print(sb);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value, int flag) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		if(left <= s && e <= right)
		{
			if(flag == 1)// 더하기
			{
				lazy[treeNode].mul = 1;		// 곱할 숫자를 1로 만든다.
				lazy[treeNode].sum = value;	// 더할 숫자를 value로 만든다.
			}
			else if(flag == 2)// 곱하기
			{
				lazy[treeNode].mul = value;	// 곱할 숫자를 value로 만든다.
				lazy[treeNode].sum = 0;		// 더할 수는 0으로 만든다.
			}
			else// 대입하기
			{
				lazy[treeNode].mul = 0;		// 대입이기 때문에 곱하는 것은 0으로 한다.
				lazy[treeNode].sum = value;	// 대입이라 value를 넣는다.
			}
			
			propagate(treeNode , s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value, flag);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value, flag);
		
		tree[treeNode] = (tree[treeNode << 1] + tree[treeNode << 1 | 1]) % MOD;
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode].sum != 0 || lazy[treeNode].mul > 1)
		{
			// 현재 값에 곱하기를 하고, 더할 값은 하위 노드수 만큼 곱해서 더한다.
			tree[treeNode] = (tree[treeNode] * lazy[treeNode].mul) + (e - s + 1) * lazy[treeNode].sum;
			tree[treeNode] %= MOD;
			if(s != e)
			{
				int nextNode = treeNode << 1;
				// 현재의 mul 값을 다음 lazy에 내려줄 때 곱해준다. 
				lazy[nextNode].mul		= (lazy[nextNode].mul		* lazy[treeNode].mul) % MOD;
				lazy[nextNode | 1].mul	= (lazy[nextNode | 1].mul	* lazy[treeNode].mul) % MOD;
				// 다음 sum을 구할 때는, 현재 mul의 값을 곱한 다음, 현재 sum의 값을 더한다
				lazy[nextNode].sum		= (lazy[nextNode].sum		* lazy[treeNode].mul + lazy[treeNode].sum) % MOD;
				lazy[nextNode | 1].sum	= (lazy[nextNode | 1].sum	* lazy[treeNode].mul + lazy[treeNode].sum) % MOD;
			}
			
			lazy[treeNode].sum = 0;
			lazy[treeNode].mul = 1;
		}
	}
	public static long query(int treeNode, int s, int e, int left, int right) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return (
					query(treeNode << 1, s, mid, left, right)
					+ query(treeNode << 1 | 1, mid + 1, e, left, right)
				) % MOD;
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = (tree[treeNode << 1] + tree[treeNode << 1 | 1]) % MOD;
	}
}

class Node{
	long sum, mul;
	Node(long s, long m){
		sum = s;
		mul = m;
	}
}

class Reader {
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
