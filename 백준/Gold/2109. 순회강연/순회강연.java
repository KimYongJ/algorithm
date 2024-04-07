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
			
			int parentNode = getParent(now.day);		// 현재 큐의 날짜로 부터 0과 가장 가까운 날을 가져옴 
			if(parentNode != 0) 					 	// 가장 가까운 날이 0이 아닌 경우
			{
				money += now.money;						// 강연 가능하므로 강연비 플러스
				parent[parentNode] = parentNode-1;		// 가장 가까운 날을 바로 전날로 세팅,
			}
		}
		System.out.println(money);
	}
	public static int getParent(int idx) {
		if(parent[idx] == idx) return idx;				// 특정 날의 강연 가능한 일자를 구해온다.
		return parent[idx] = getParent(parent[idx]);	// 경로 압축 기법, union find에서 find 효율을 높이기 위해 사용, 검색 경로상 모든 노드들이 직접 최종 루트를 가리키도록 업데이트함으로 속도 향상
	}
}