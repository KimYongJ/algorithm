//https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Node{
	int s,e;
	Node(int s,int e){this.s=s;this.e=e;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String args[])throws Exception{
		ArrayList<Node> list = new ArrayList<>();
		int N = read();
		
		for(int i=0; i<N; i++) 
		{
			int a = read() * 100 + read();
			int b = read() * 100 + read();
			list.add(new Node(a,b));
		}
		
		Collections.sort(list,(a,b)->{
			if(a.e==b.e) return a.s-b.s;
			return b.e-a.e;
		});
		
		int maxIdx = N;
		int cnt = 0;
		int start = 301;
		int before = 0;
		while(start < 1201 && before != start)
		{
			before = start;
			for(int j=0; j<maxIdx; j++)
			{
				Node now = list.get(j);
				if(now.s <= start) {
					start = now.e;
					cnt++;
					maxIdx = j;
					break;
				}
			}
		}
		
		if(start < 1201)
			cnt = 0;
		System.out.print(cnt);
	}
}