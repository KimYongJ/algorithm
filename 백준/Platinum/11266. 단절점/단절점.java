//https://www.acmicpc.net/problem/11266
//1초 256MB
//7 7 // 1번부터 번호가 있는 정점의 수(1<=10,000), 간선수(1<=100,000)
//1 4 // 간선 수 만큼 연결된 두 정점 A,B가 주어진다.
//4 5
//5 1
//1 6
//6 7
//2 7
//7 3
//첫째 줄에 단절점의 개수 출력, 둘째 줄에는 단절점의 번호를 오름차순으로 출력
//3
//1 6 7

class Main{
	
	static int N, E;
	static int idx, cutSize;
	static int order[];
	static boolean cut[];
	static Node adNode[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N		= in.nextInt();// 1번부터 번호가 있는 정점의 수(1<=10,000)
		E		= in.nextInt();// 간선수(1<=100,000)
		order	= new int[N + 1];
		cut		= new boolean[N + 1];
		adNode	= new Node[N + 1];
		
		for(int i=0; i<E; i++)
		{
			int a = in.nextInt();
			int b = in.nextInt();
			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}
		
		for(int i=1; i<=N; i++)
			if(order[i] == 0) // 아직 방문하지 않은 정점인 경우, 해당 정점을 루트노드로 탐색
				dfs(i, 0);

		StringBuilder sb = new StringBuilder();
		
		sb.append(cutSize).append('\n');
		
		for(int i=1; i<=N; i++)
			if(cut[i])
				sb.append(i).append(' ');
		
		System.out.print(sb);
	}
	static int dfs(int now, int parent)
	{
		int min = order[now] = ++idx;
		int childCnt = 0;
		for(Node nextNode = adNode[now]; nextNode != null; nextNode=nextNode.next)
		{
			int next = nextNode.node;
			
			if(next == parent)// 부모노드일 경우 스킵
				continue;
			
			if(order[next] > 0)
			{
				// 이미 방문한 정점(조상)으로 가는 간선
				min = Math.min(min, order[next]);
				continue;
			}
			childCnt++;
			// 자기 자식노드 중 갈 수 있는 low의 가장 작은 값 반환
			int low = dfs(next, now);
			
			// 자기 자식노드중 어떤 하나가 나보다 위로 못가면 내가 없어지면 단절점이 되는것
			// 다만 루트노드인 경우는 다른 조건으로 체크하기 위해 parent != 0 조건 추가
			if(parent != 0 && low >= order[now])
				cut[now] = true;
			
			min = Math.min(min, low);
			
		}
		// 루트노드는 자식노드가 2개이상일 때 무조건 단절점
		if(parent == 0 && childCnt >= 2)
			cut[now] = true;
		// 단절점 체킹 후 단절점 개수 추가
		if(cut[now])
			++cutSize;
		
		return min;
	}
	static class Node{
		int node;
		Node next;
		Node(int node, Node next){
			this.node = node;
			this.next = next;
		}
	}
	static class Reader {
	    final int SIZE = 1 << 13;
	    byte[] buffer = new byte[SIZE];
	    int index, size;

	    int nextInt() throws Exception {
	        int n = 0;
	        byte c;
	        boolean isMinus = false;
	        while ((c = read()) <= 32) { if (size < 0) return -1; }
	        if (c == 45) { c = read(); isMinus = true; }
	        do n = (n << 3) + (n << 1) + (c & 15);
	        while (isNumber(c = read()));
	        return isMinus ? ~n + 1 : n;
	    }

	    boolean isNumber(byte c) {
	        return 47 < c && c < 58;
	    }

	    byte read() throws Exception {
	        if (index == size) {
	            size = System.in.read(buffer, index = 0, SIZE);
	            if (size < 0) buffer[0] = -1;
	        }
	        return buffer[index++];
	    }
	}
}