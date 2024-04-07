// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{
	int money, day;
	Node(int money, int day){this.money = money; this.day = day;}
}
class Main{
	static Node now;
	static int N, money, parent[];
	static PriorityQueue<Node> pq;
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
        return n;
    }
	public static void main(String[] args)throws Exception{
		N 		= read();
		pq 		= new PriorityQueue<Node>((a,b)->b.money-a.money);
		parent 	= new int[10001];
		for(int i=1; i<10001; i++)
			parent[i] = i;
	
		for(int i=0; i<N; i++) 
			pq.add(new Node(read() , read()));

		while(!pq.isEmpty())
		{
			now = pq.poll();
			
			int parentNode = getParent(now.day);
			if(parentNode != 0) 
			{
				money += now.money;
				setParent(parentNode - 1, parentNode);
			}
		}
		System.out.println(money);
	}
	public static int getParent(int idx) {
		if(parent[idx] == idx) return idx;
		return getParent(parent[idx]);
	}
	public static void setParent(int beforeNode, int nowNode) {
		int aParent = getParent(beforeNode);
		int bParent = getParent(nowNode);
		parent[bParent] = aParent;
	}
}