//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/2660
import java.util.ArrayDeque;
import java.util.ArrayList;

class Node{
	int node; Node next;
	Node(int node, Node next){this.node=node; this.next=next;}
}

class Main{
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
    
	public static void main(String[] args)throws Exception{
		int N			= read();
		Node[] adNode	= new Node[N+1];
		
		while(true)
		{
			int a = read();
			int b = read();
			if(a == -1 && b== -1)
				break;
			
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		// 결과를 담을 리스트
		ArrayList<Integer> result = new ArrayList<>();
		int min = 51;
		LOOP:
		for(int i=1; i<=N; i++)
		{
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean visit[] = new boolean[N+1];
			visit[i] = true;
			q.add(i);
			
			int score = 0;
			while(!q.isEmpty())
			{
				score++;
				int size = q.size();
				while(size-- > 0)
				{
					int idx = q.poll();
					for(Node now=adNode[idx]; now!=null; now=now.next)
						if(!visit[now.node])
						{
							visit[now.node] = true;
							q.add(now.node);
						}
				}
				if(min < score)
					continue LOOP;
			}
			if(1 < score && score < min)
			{
				result.clear();
				result.add(i);
				min = score;
			}else if(score == min)
				result.add(i);
		}
		
		StringBuilder sb1 = new StringBuilder().append(min-1).append(' ').append(result.size()).append('\n');
		
		for(int r : result)
			sb1.append(r).append(' ');
		
		System.out.print(sb1.toString());
	}
}
