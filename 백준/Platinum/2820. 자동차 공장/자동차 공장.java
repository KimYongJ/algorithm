//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2820
//1초 / 256MB

class Main{
	
	static int cnt;
	static int N, M;
	static int[] cost;
	static int[][] range;
	static int[] tree, lazy;
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();

		StringBuilder sb = new StringBuilder();
		N		= in.nextInt();
		M		= in.nextInt();
		cost	= new int[N + 1];
		adNode	= new Node[N+1];
		range	= new int[N + 1][2];
		tree	= new int[N<<2];
		lazy	= new int[N<<2];
		
		
		cost[1] = in.nextInt();
		for(int i=2; i<=N; i++)
		{
			cost[i] = in.nextInt();
			int parent = in.nextInt();
			// 빠른 연산을 위해 자료구조를 직접 정의함
			adNode[parent] = new Node(i, adNode[parent]);
		}
		
		DFS(1);

		while(M-->0)
		{
			if(in.nextChar() == 'p')
			{
				int l = in.nextInt();
				int x = in.nextInt();
				update(1, 1, N, range[l][0]+1, range[l][1], x);
			}
			else {
				int idx = in.nextInt();
				sb.append(cost[idx] + query(1, 1, N, range[idx][0])).append('\n');
			}
		}
		System.out.print(sb);
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
	}
	public static int query(int treeNode, int s, int e, int idx) {
		
		propagate(treeNode, s, e);
		
		if(e < idx || idx < s)
			return 0;
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, idx) 
				+ query(treeNode <<1 | 1, mid + 1, e, idx);
	}

	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += (e - s + 1) * lazy[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static void DFS(int node) {
		range[node][0] = ++cnt;
		
		for(Node next=adNode[node]; next!=null; next=next.next)
				DFS(next.now);

		range[node][1] = cnt;
	}
}

class Node{
	int now;
	Node next;
	Node(int now, Node next){
		this.now = now;
		this.next = next;
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

