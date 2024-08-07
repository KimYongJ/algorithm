// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Meat{
	int w, p;
	Meat(int w, int p){this.w=w; this.p=p;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		PriorityQueue<Meat> pq = new PriorityQueue<Meat>((a,b)->{
			if(a.p==b.p) return b.w-a.w;// 무게 기준 내림차순
			return a.p-b.p;				// 가격 기준 오름차순
		});
		int N = read();
		int M = read();
		
		while(N-->0) 
			pq.add(new Meat(read(),read()));
		
		int result		= Integer.MAX_VALUE;
		int weight		= 0;
		int price		= 0;
		int beforePrice = -1;
		while(!pq.isEmpty()) {
			Meat now = pq.poll();
			
			weight += now.w;
			
			if(beforePrice == now.p)
				price += now.p;
			else
				price = now.p;
			
			if(weight >= M) // 목표무게가 넘었어도 이전 가격과 현재 가격이 같은것이면 반복문 계속해야함.
			{
				result = Math.min(price, result);
				if(beforePrice != now.p)// 이전과 같은 무게일 경우는 반복문을 종료하지 않는다.
				{
					break;	
				}
			}
			beforePrice = now.p;
		}
		System.out.print(weight < M ? -1 : result);
	}
}