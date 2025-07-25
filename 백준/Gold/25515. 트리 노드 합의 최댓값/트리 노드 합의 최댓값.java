//https://www.acmicpc.net/problem/25515
//3초 512MB

class Main{
	
	static Node adNode[];
	static int N;
	static long value[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// 노드 수(2<=십만)
		adNode = new Node[N];
		value = new long[N];
		
		for(int i=1; i<N; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			adNode[a] = new Node(b, adNode[a]);
		}
		
		for(int i=0; i<N; i++)// 각 노드의 고유 값 (-십만<=십만)
			value[i] = in.nextInt();
			
		System.out.print(dfs(0));
	}
	static long dfs(int now)
	{
		long val = value[now];
		
		for(Node next = adNode[now]; next != null; next=next.next)
		{
			long nextVal = dfs(next.node);
			
			if(nextVal >= 0)
				val += nextVal;
		}
		return val;
	}
	static class Node{
		int node;
		Node next;
		Node(int node, Node next){
			this.node = node;
			this.next = next;
		}
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
