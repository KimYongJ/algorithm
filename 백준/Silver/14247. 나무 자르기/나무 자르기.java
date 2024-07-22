// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Node{
	int base, inc;
	Node(int base, int inc){this.base=base; this.inc=inc;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		ArrayList<Node> list = new ArrayList<>();
		int N = read();
		
		for(int i=0; i<N; i++)list.add(new Node(read(), 0));
		for(int i=0; i<N; i++)list.get(i).inc = read();
	
		Collections.sort(list,(a,b)->{
			if(a.inc == b.inc)return b.base - a.base;
			return a.inc - b.inc;
		});

		long sum = 0;
		for(int i=0; i<N; i++) 
		{
			Node node = list.get(i);
			sum += node.base + (node.inc * (long)(i));
		}
		System.out.print(sum);
	}
}