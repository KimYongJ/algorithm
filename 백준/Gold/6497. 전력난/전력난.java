//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/6497
//1초 256MB

import java.util.Collections;
import java.util.PriorityQueue;

class Node implements Comparable<Node>{
	int n1, n2, dist;
	Node(int n1, int n2, int dist){
		this.n1=n1;
		this.n2=n2;
		this.dist = dist;
	}
	@Override
	public int compareTo(Node o) {
		return dist - o.dist;
	}
}

class Main{

	static int parent[];
	
	public static void main(String[] args)throws Exception{
		
		StringBuilder sb= new StringBuilder();
		Reader in		= new Reader();
		
		while(true)
		{
			PriorityQueue<Node> pq = new PriorityQueue<>();
			int M		= in.readInt();	// 노드 수 1<=이십만
			int N		= in.readInt();	// 길의 수 m-1 <=이십만
			int distSum = 0;
			parent		= new int[N + 1];
			
			if(M == 0)
				break;
			
			for(int i=1; i<=N; i++)
				parent[i] = i;
			
			while(N-->0)
			{
				int n1	= in.readInt();
				int n2	= in.readInt();
				int dist= in.readInt();
				
				distSum += dist;
				
				pq.add(new Node(n1,n2,dist));
			}
			
			
			int sum = 0;
			int cnt = 1;
			while(cnt != M)
			{
				Node now = pq.poll();
				int parent1 = getParent(now.n1);
				int parent2 = getParent(now.n2);
				if(parent1 != parent2)
				{
					if(parent1 < parent2)
						parent[parent2] = parent1;
					else
						parent[parent1] = parent2;
					
					sum += now.dist;
					cnt += 1;
				}
			}
			
			sb.append(distSum - sum)
				.append('\n');
		}
		System.out.print(sb);
	}
	public static int getParent(int node) {
		if(parent[node] == node) return node;
		return parent[node] = getParent(parent[node]);
	}
}


class Reader {
    final int SIZE = 1 << 13;
    byte[] buffer = new byte[SIZE];
    int index, size;

    int readInt() throws Exception {
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
