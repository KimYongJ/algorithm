//https://www.acmicpc.net/problem/30512
//1초 1024MB
//10// 노드 수 1<=100,000
//10 5 6 9 2 4 7 1 8 3// 정수 0<=1,000,000
//5// 쿼리 수 1<=100,000
//1 2 8// L, R, X(0<=1,000,000)
//2 4 6
//5 9 4
//8 10 1
//3 6 7
//쿼리 수행 후 총 결과와, 각 업데이트당 잊힌 원소 개수 출력
//8 5 6 6 2 4 4 1 1 1
//6 7 8 10 10

class Main{

	static final int MAX = 1<<30;
	static int N, Q;
	static int arr[];
	static Node lazy[];
	static Node tree[];
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		N = in.nextInt();
		arr = new int[N + 1];
		tree = new Node[N * 4];
		lazy = new Node[N * 4];
		
		for(int i=0; i<tree.length; i++)
		{
			tree[i] = new Node(MAX,0);
			lazy[i] = new Node(MAX,0);
		}
		
		for(int i=1; i<=N; i++)
			arr[i] = in.nextInt();
		
		init(1, 1, N);
		
		Q = in.nextInt();
		for(int i=1; i<=Q; i++)
		{
			int L = in.nextInt();
			int R = in.nextInt();
			int X = in.nextInt();//(0<=1,000,000)
			update(1, 1, N, L, R, X, i);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int prefixSum[] = new int[Q + 1];
		for(int i=1; i<=N; i++)
		{
			Node now = query(1, 1, N, i);// 원소마다 값을 꺼내옴
			prefixSum[now.lastQuery] += 1;// 꺼내온 값의 마지막 영향을 받은 쿼리 개수 추가
			sb.append(now.num).append(' ');// 최종 결과값 입력
		}
		
		sb.append('\n');
		// 쿼리마다 돌면서 첫번 째 쿼리부터 마지막 쿼리까지 몇번 영향을 미쳤는지 출력
		for(int i=1; i<=Q; i++)
			sb.append(prefixSum[i] += prefixSum[i-1]).append(' ');
		
		System.out.print(sb);
	}
	static Node query(int treeNode, int s, int e, int targetIdx) {
		propagate(treeNode, s, e);
		
		if(s == e)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		if(targetIdx <= mid)
			return query(treeNode << 1, s, mid, targetIdx);
		
		return query(treeNode << 1 | 1, mid + 1, e, targetIdx);
	}
	static void update(int treeNode, int s, int e, int left, int right, int value, int queryIdx) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left <= s && e <= right)
		{
			lazy[treeNode].num = value;
			lazy[treeNode].lastQuery = queryIdx;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value, queryIdx);
		update(treeNode << 1 | 1, mid + 1, e, left, right, value, queryIdx);
	}
	static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode].lastQuery != 0)
		{
			int num = lazy[treeNode].num;
			int lastQuery = lazy[treeNode].lastQuery;
			
			if(tree[treeNode].num > num)
			{
				tree[treeNode].num = num;
				tree[treeNode].lastQuery = lastQuery;
			}
			
			if(s != e)
			{
				int left = treeNode << 1;
				int right = treeNode << 1 | 1;
				
				if(lazy[left].num > num)
				{
					lazy[left].num = num;
					lazy[left].lastQuery = lastQuery;
				}
				if(lazy[right].num > num)
				{
					lazy[right].num = num;
					lazy[right].lastQuery = lastQuery;
				}
			}
			lazy[treeNode].num = MAX;
			lazy[treeNode].lastQuery = 0;
		}
	}
	static void init(int treeNode, int s, int e) {
		if(s == e)
		{
			tree[treeNode].num = arr[s];
			return;
		}
		
		int mid = (s + e) >> 1;
		
		init(treeNode << 1, s, mid);
		init(treeNode << 1 | 1, mid + 1, e);
	}
	static class Node{
		int num,lastQuery;
		Node(int n, int l){
			num = n;
			lastQuery = l;
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