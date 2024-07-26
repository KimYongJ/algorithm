// https://github.com/kimyongj/algorithm
import java.util.PriorityQueue;
class Node{int s,e;Node(int s,int e){this.s=s; this.e=e;}}
class Main{
	private static int read() throws Exception{
        int val = 0;
        int c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        do {
            val = 10 * val + c - 48;
        } while ((c = System.in.read()) >= 48 && c <= 57);
        return val;
    }
	public static void main(String[] args)throws Exception{
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->a.e-b.e);
		StringBuilder sb = new StringBuilder();
		int P = read();
		int T = 0, cnt, arr[];
		while(P != 0) 
		{
			pq.clear();
			cnt = 0;
			arr = new int[24];
			for(int i=0; i<P; i++) 
			{
				pq.add(new Node(read(),read()));
			}
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				for(int s = now.s; s<now.e; s++) {
					if(arr[s] < 2) {
						++arr[s];
						++cnt;
						break;
					}
				}
			}
			
			sb.append(String.format("On day %d Emma can attend as many as %d parties.\n", ++T, cnt));
			P = read();
		}
		System.out.print(sb.toString());
	}
}