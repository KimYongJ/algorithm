//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1637

class Main{
	
	static int N, H, arr[], tree[];
	
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return tree[treeNode] = Math.max(init(treeNode*2, s, mid), init(treeNode*2+1, mid+1, e));
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		if(right < s || e < left)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		int mid = (s + e) >> 1;
		return Math.max(query(treeNode*2, s, mid, left, right), query(treeNode*2+1, mid+1, e, left, right));
	}
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		int T = read();
		while(T-- >0)
		{
			N	= read();
			H	= (int)Math.ceil(Math.log(N) / Math.log(2));
			arr = new int[N+1];
			tree= new int[1 <<(H+1)];
			
			for(int i=1; i<=N; i++)
				arr[i] = read();
			
			init(1, 1, N);
			
			boolean flag = true;
			int idxArr[] = new int[N + 1];
			
			for(int i=1; i<=N; i++)
			{
				if(idxArr[arr[i]] != 0)
				{
					int maxValue = query(1, 1, N, idxArr[arr[i]], i);
					if(maxValue != arr[i])
					{
						flag = false;
						break;
					}
				}
				idxArr[arr[i]] = i;
			}
			
			if(flag)
				sb.append("Yes");
			else
				sb.append("No");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}