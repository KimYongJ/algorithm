//https://www.acmicpc.net/problem/6188
//1초 128MB
//5 2 // 노드 개수, 주어지는 간선정보 개수
//3 5 4 // 부모노드번호, 자식노드 번호 2개
//1 2 3
//
//1 // i번째 노드에서 루트노드 까지의 거리 출력
//2
//2
//3
//3

class Main{
	
	static int N, C;
	static int cnt[];
	static Node adList[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		C = in.nextInt();
		cnt = new int[N + 1];
		adList = new Node[N + 1];
		
		for(int i=0; i<C; i++)
		{
			int r = in.nextInt();
			int c1 = in.nextInt();
			int c2 = in.nextInt();
			adList[r] = new Node(c1, adList[r]);
			adList[r] = new Node(c2, adList[r]);
		}
		
		dfs(1, 0, 1);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++)
			sb.append(cnt[i]).append('\n');
		
		System.out.print(sb);
	}
	static void dfs(int now, int prev, int depth) {
		
		cnt[now] = depth;
		
		for(Node next = adList[now]; next != null; next=next.next)
			dfs(next.node, now, depth + 1);
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
