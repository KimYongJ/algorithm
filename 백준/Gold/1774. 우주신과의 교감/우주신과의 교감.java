//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1774
//2초 128MB

import java.util.PriorityQueue;

class Main{
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		
		int N			= in.nextInt();	// 1<=1000
		int M			= in.nextInt();	// 1<=1000
		int cnt			= N - 1;
		int parent[]	= new int[N + 1];
		int data[][]	= new int[N + 1][2];
		
		
		for(int i=1; i<=N; i++)
		{
			data[i][0] = in.nextInt();
			data[i][1] = in.nextInt();
			
			parent[i] = i;
		}
		
		for(int i=0; i<M; i++)
		{
			int parent1 = getParent(in.nextInt(), parent);
			int parent2 = getParent(in.nextInt(), parent);
			
			if(parent1 != parent2)
			{
				if(parent1 < parent2)
					parent[parent2] = parent1;
				else
					parent[parent1] = parent2;

				cnt -= 1;
			}
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for(int i=1; i<N; i++)
		{
			for(int j=i+1; j<=N; j++)
			{
				double dx = data[i][0] - data[j][0];
				double dy = data[i][1] - data[j][1];
				
				pq.add(new Node(i, j, Math.sqrt(dx * dx + dy * dy)));
			}
		}
		
		double ans = 0;
		
		while(cnt != 0)
		{
			Node now = pq.poll();
			int parent1 = getParent(now.a, parent);
			int parent2 = getParent(now.b, parent);
			
			if(parent1 != parent2)
			{
				if(parent1 < parent2)
					parent[parent2] = parent1;
				else
					parent[parent1] = parent2;

				ans += now.dist;
				cnt	-= 1;
			}
		}
		
		System.out.printf("%.2f", ans);
	}
	public static int getParent(int node, int[] parent) {
		if(parent[node] == node)
			return node;
		
		return parent[node] = getParent(parent[node], parent);
	}
}

class Node implements Comparable<Node>{
	int a,b;
	double dist;
	Node(int a, int b, double dist){
		this.a=a;
		this.b=b;
		this.dist=dist;
	}
	@Override
	public int compareTo(Node o) {
		return Double.compare(dist , o.dist);
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
