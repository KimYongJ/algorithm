//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/5926
//1초 / 128MB
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

class Node{int xpos, id;Node(int x, int i){xpos=x; id=i;}}

class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();
		Node node[] = new Node[N];
		
		TreeSet<Integer> set		= new TreeSet<>();	// id의 종류를 담을 배열
		HashMap<Integer,Integer> hm = new HashMap<>();	// id 압축을 위한 map
		for(int i=0; i<N; i++)
		{
			int xpos	= read();
			int id		= read();
			node[i]		= new Node(xpos, id);
			set.add(node[i].id);
		}
		
		Arrays.sort(node, (a,b)->a.xpos - b.xpos);
		
		int rnk = 0;
		for(Iterator<Integer> ite = set.iterator(); ite.hasNext();)
			hm.put(ite.next(), rnk++);
		
		for(int i=0; i<N; i++)
			node[i].id = hm.get(node[i].id);
		
		int count[]	= new int[rnk];
		int cnt		= 0;
		int s		= 0;
		int e		= 0;
		int min 	= Integer.MAX_VALUE;
		while(e<N)
		{
			if(count[node[e].id]++ == 0)
				cnt++;
			
			while(cnt == rnk)
			{
				min = Math.min(min, node[e].xpos - node[s].xpos);
				if(--count[node[s++].id] == 0)
					--cnt;
			}
			
			++e;			
		}
		System.out.print(min);
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}