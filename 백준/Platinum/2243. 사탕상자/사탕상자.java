//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2243
class Main{
	
	static final int size = 1_000_000;
	static int tree[];
	
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        boolean m = n == 13;
        if (m)n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48) {
        n = (n << 3) + (n << 1) + (c & 15);}
        return m ? ~n + 1 : n;
    }
	
	public static void update(int node, int s, int e, int originIdx, int diff) {
		if(originIdx < s || e < originIdx)
			return;
		
		tree[node] += diff;
		
		if(s != e)
		{
			int mid = (s + e) >> 1;
			update(node*2, s, mid, originIdx, diff);
			update(node*2 + 1, mid + 1, e, originIdx, diff);
		}
	}
	public static int getRank(int node, int s, int e, int target) {
		if(s == e)						// 무조건 리프노드로 내려오게 되고, 정답인 리프노드로 내려올 수 있게 끔 아래 if문을 잘 만드는것이 중요
		{
			update(1, 1, size, s, -1);
			return s;
		}
		
		int mid = (s + e) >> 1;
		if(target <= tree[node * 2])	// target이 왼쪽 하위 노드 보다 작거나 같다면 왼쪽으로 탐색
			return getRank(node * 2, s, mid, target);
		else							// target이 오른쪽 하위 노드보다 작으면 오른쪽 탐색, 이 때 오른쪽 탐색시 왼쪽자식노드의 값을 target에서 빼주어야 한다.
			return getRank(node * 2 + 1, mid + 1, e, target - tree[node * 2]);
	}
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		
		int N = read();	// 사탕상자에 손을댄 횟수(1<=십만)
		int h = (int)Math.ceil(Math.log(size) / Math.log(2));
		
		tree = new int[(int)Math.pow(2,h + 1)];
		
		while(N-->0)
			if(read() == 1)			// 사탕을 꺼내는 경우
			{
				int rnk = read();
				sb.append(getRank(1, 1, size, rnk)).append('\n');
			}
			else					// 사탕을 넣는 경우
			{
				int idx = read();	// 1~백만 까지의 맛 중, 어느 맛인지
				int cnt = read();	// 몇개를 넣을 것인지
				update(1, 1, size, idx, cnt);
			}

		System.out.print(sb);
	}
}