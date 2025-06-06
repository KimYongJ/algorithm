//https://www.acmicpc.net/problem/14595
//1초 512MB

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Main{
	
	static int N, M;
	static List<Edge> edge;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		M = in.nextInt();
		edge = new ArrayList<>();
		
		for(int i=0; i<M; i++)
			edge.add(new Edge(in.nextInt(), in.nextInt()));
		
		Collections.sort(edge);
		
		int prevE = 0;
		int remove = 0;
		
		for(Edge now : edge)
		{
			if(prevE < now.s)
			{
				prevE = now.e;
				remove += now.e - now.s;
			}
			else if(now.e > prevE)
			{
				remove += now.e - prevE;
				prevE = now.e;
			}
		}
		System.out.print(N - remove);
	}
	static class Edge implements Comparable<Edge>{
		int s,e;
		Edge(int s, int e){
			this.s=s;
			this.e=e;
		}
		@Override
		public int compareTo(Edge o) {
			return s == o.s ? e - o.e : s - o.s;
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