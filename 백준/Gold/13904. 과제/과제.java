// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int day, score;
	Node(int day, int score){this.day=day; this.score=score;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> scorePq	= new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Node> list		= new PriorityQueue<Node>((a,b)->b.day - a.day);
		
		int N		= read();
		int maxDay	= 0;
		for(int i=0; i<N; i++) 
		{
			int d = read(); // 마감일
			int s = read(); // 점수
			list.add(new Node(d,s));
			if(maxDay < d) {
				maxDay = d;
			}
		}
		
		int res		= 0;
		for(int i=maxDay; i>=1; i--) 
		{
			while(!list.isEmpty() && list.peek().day == i)
				scorePq.add(list.poll().score);
			
			if(!scorePq.isEmpty())
				res += scorePq.poll();
		}
		System.out.print(res);
	}
}
