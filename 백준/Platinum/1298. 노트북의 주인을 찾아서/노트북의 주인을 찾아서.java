//https://www.acmicpc.net/problem/1298
//2초 128MB
//5 13	// 사람 수 1<=100, 노트북 예상 수(0<=5,000)
//1 2	// 1번 사람이 2번 노트북이 자신의 것이라 주장
//1 3
//2 2
//2 3
//3 1
//3 2
//4 1
//4 2
//5 1
//5 2
//5 3
//5 4
//5 5
//답 : 4

class Main{
	
	static int N, M;
	static int[] match;						// idx : 노트북 번호, value : 매칭된 사람 번호
	static boolean[] visit;					// idx : 노트북 번호, value : 해당 노트북을 방문했었는지 체크
	static Node[] adNode;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N		= in.nextInt();	// 사람 수 1<=100
		M		= in.nextInt();	// 노트북 예상 수(0<=5,000)
		match	= new int[N + 1];
		adNode	= new Node[N + 1];
		
		for(int i=0; i<M; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			adNode[a] = new Node(b, adNode[a]);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
		{
			visit = new boolean[N + 1];
			if(dfs(i))
				++cnt;
		}
		
		System.out.print(cnt);
	}
	static boolean dfs(int person) {
		for(Node next = adNode[person]; next != null; next=next.next)
		{
			int noteBook = next.noteBook;
			
			if(visit[noteBook])
				continue;
			
			visit[noteBook] = true;
			
			if(match[noteBook] == 0 || dfs(match[noteBook]))
			{
				match[noteBook] = person;
				return true;
			}
			
		}
		return false;
	}
	static class Node{
		int noteBook;
		Node next;
		Node(int noteBook, Node next){
			this.noteBook = noteBook;
			this.next = next;
		}
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