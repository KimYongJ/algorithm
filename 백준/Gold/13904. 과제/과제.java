// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Node{
	int day, score;
	Node(int day, int score){this.day=day; this.score=score;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Integer> scorePq	= new PriorityQueue<Integer>((a,b)->b-a);
		PriorityQueue<Node> list		= new PriorityQueue<Node>((a,b)->b.day - a.day);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N		= Integer.parseInt(br.readLine());
		int maxDay	= 0;
		int res		= 0;
		for(int i=0; i<N; i++) 
		{
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken()); // 마감일
			int s = Integer.parseInt(st.nextToken()); // 점수
			list.add(new Node(d,s));
			if(maxDay < d) {
				maxDay = d;
			}
		}
		for(int i=maxDay; i>=1; i--) 
		{
			if(!list.isEmpty() && list.peek().day == i) 
			{
				while(!list.isEmpty() && list.peek().day == i)
					scorePq.add(list.poll().score);
			}
			if(!scorePq.isEmpty())
				res += scorePq.poll();
		}
		System.out.print(res);
	}
}