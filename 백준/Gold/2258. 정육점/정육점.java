// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Meat{
	int w, p;
	Meat(int w, int p){this.w=w; this.p=p;}
}
class Main{
	public static void main(String[] args)throws Exception{
		PriorityQueue<Meat> pq = new PriorityQueue<Meat>((a,b)->{
			if(a.p==b.p) return b.w-a.w; // 무게 기준 내림차순
			return a.p-b.p; // 가격 기준 오름차순
		});
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 전과 무게와 가격이 같다면 갱신하지 않는다.
		while(N-->0) 
		{
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			pq.add(new Meat(w,p));
		}
		// 목표무게가 넘었어도 같으면 안됨 달라야만 가능
		int result		= Integer.MAX_VALUE;
		int weight		= 0;
		int price		= 0;
		int beforePrice = -1;
		while(!pq.isEmpty()) {
			Meat now = pq.poll();
			weight += now.w;
			if(beforePrice == now.p) {
				price += now.p;
			}else {
				price = now.p;
			}
			
			if(weight >= M) {
				result = Math.min(price, result);
				if(beforePrice != now.p) {
					break;	
				}
			}
			beforePrice = now.p;
		}
		System.out.print(weight < M ? -1 : result);
	}
}