//https://www.acmicpc.net/problem/1765
//2초 256MB

class Main{
	
	static int N, M;
	static int parent[];
	static int enemy[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();// 학생수 (2 ≤ 1000) 
		M = in.nextInt();// 인간관계 수(1 ≤ 5000)
		parent = new int[N + 1];
		enemy = new int[N + 1];
		
		for(int i=0; i<=N; i++)
			parent[i] = i;

		while(M-->0)
		{
			char cmd = in.nextChar();
			int n1 = in.nextInt();
			int n2 = in.nextInt();
			if(cmd == 'E')
			{
				setEnemy(n1, n2);
				continue;
			}
			
			union(n1, n2);
		}
		
		int cnt = 0;
		
		for(int i=1; i<=N; i++)
			if(parent[i] == i)
				++cnt;

		System.out.print(cnt);
	}
	static void setEnemy(int n1, int n2) {
		if(enemy[n1] != 0) union(enemy[n1], n2);
		if(enemy[n2] != 0) union(enemy[n2], n1);
		
		enemy[n1] = n2;
		enemy[n2] = n1;
	}
	static void union(int n1, int n2) {
		int parent1 = find(n1);
		int parent2 = find(n2);
		
		if(parent1 == parent2)
			return;
		
		if(parent[parent1] < parent[parent2])
			parent[parent2] = parent1;
		else
			parent[parent1] = parent2;
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;
	    char nextChar() throws Exception {
	        byte c;
	        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
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
}