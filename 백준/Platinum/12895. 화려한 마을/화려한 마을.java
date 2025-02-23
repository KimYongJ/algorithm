//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/12895
//2초 512MB
class Main{
	
	static int N, T, Q;
	static int[] tree, lazy;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb= new StringBuilder();
		Reader in		= new Reader();

		N		= in.nextInt();//집개수 1<=십만
		T		= in.nextInt();//사용할 색개수 1<=30
		Q		= in.nextInt();//작업의 개수 1<=십만
		tree	= new int[N*4];
		lazy	= new int[N*4];
		
		init(1, 1, N);
		
		while(Q-->0)
		{
			char c = in.nextChar();
			int x = in.nextInt();
			int y = in.nextInt();
			if(y < x)
			{
				int t = y;
				y = x;
				x = t;
			}
			
			if(c == 'C')// x~y 사이 z번으로 칠함
				update(1, 1, N, x, y, 1 << (in.nextInt() - 1));
			
			else// x~y 출력
				sb.append(Integer.bitCount(query(1, 1, N, x, y)))
					.append('\n');
		}
		System.out.print(sb);
	}
	public static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode] = 1;
			return;
		}
		
		int mid = (s + e) >> 1;
				
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
		
		tree[treeNode] = tree[treeNode << 1] | tree[treeNode << 1 | 1];
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return 0;
		
		if(left <= s && e <= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				| query(treeNode << 1 | 1, mid + 1, e, left , right);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] | tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = lazy[treeNode];
			if(s!=e)
			{
				lazy[treeNode << 1]		= lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}

class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    char nextChar() throws Exception {
        byte c;
        while ((c = read()) < 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
        return (char)c;
    }
    
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

