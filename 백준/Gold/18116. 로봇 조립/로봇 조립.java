//https://www.acmicpc.net/problem/18116
//4초 1024MB
//4// 지시 횟수 1<=1,000,000
//I 1 2// I명령어는 두 노드가 같은 부품임을 의미
//I 3 2
//Q 1// Q명령어는 해당 노드가 포함된 로봇에 몇개의 부품이 있는지 출력
//Q 4

class Main{
	
	static int MAX = 1_000_000;
	static int N;
	static int parent[];
	static int cnt[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		parent = new int[MAX + 1];
		cnt = new int[MAX + 1];
		for(int i=1; i<=MAX; i++)
		{
			parent[i] = i;
			cnt[i] = 1;
		}
		
		StringBuilder sb = new StringBuilder();
		while(N-->0)
		{
			char cmd = in.nextChar();
			if(cmd == 'Q')
			{
				sb.append(cnt[find(in.nextInt())])
					.append('\n');
				continue;
			}
			
			int parent1 = find(in.nextInt());
			int parent2 = find(in.nextInt());
			
			if(parent1 == parent2)
			{
				continue;
			}
			
			if(parent[parent1] < parent[parent2])
			{
				cnt[parent1] += cnt[parent2];
				parent[parent2] = parent1;
				continue;
			}
			
			cnt[parent2] += cnt[parent1];
			parent[parent1] = parent2;
		}
		System.out.print(sb);
	}
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
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
	    char nextChar() throws Exception {
	        byte c;
	        while ((c = read()) <= 32); // SPACE 분리라면 <=로, SPACE 무시라면 <로
	        return (char)c;
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