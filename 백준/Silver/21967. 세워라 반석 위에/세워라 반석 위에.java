//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/21967
//1초 / 512MB
//연속하는 부분 수열 중 최솟값과 최댓값 차이가 2이하인 가장 긴 부분수열의 길이를 구하라
class Main{
	public static void main(String[] args)throws Exception{
		int N		= read();//1<=백만
		int[] arr	= new int[N+1];
		int[] cnt	= new int[11];

		for(int i=0; i<N; i++)
			arr[i] = read();//1<=10
		
		int len = 1;
		int s = 0;
		int e = 0;
		while(e<N)
		{
			cnt[arr[e++]]++;
			int min = getMin(cnt);
			int max = getMax(cnt);
			if(max - min <= 2)
				len = Math.max(len, e - s);
			else
				cnt[arr[s++]]--;
		}
		System.out.print(len);
	}
	public static int getMin(int[] cnt) {
		int i=0;
		while(0>=cnt[++i]);
		return i;
	}
	public static int getMax(int[] cnt) {
		int i=11;
		while(0>=cnt[--i]);
		return i;
	}
	static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
}
// 이하 세그먼트 트리 방법(느림)
//class SegmentTree{
//	
//	static int N;
//	static int[] arr, minTree, maxTree;
//	
//	public static int minInit(int treeNode, int s, int e) {
//		if(s==e)
//			return minTree[treeNode] = arr[s];
//		
//		int nextNode = treeNode << 1;
//		int mid = (s + e) >> 1;
//		return minTree[treeNode] =
//			Math.min(
//					minInit(nextNode, s, mid)
//					,minInit(nextNode|1, mid + 1, e)
//			);
//	}
//	public static int maxInit(int treeNode, int s, int e) {
//		if(s==e)
//			return maxTree[treeNode] = arr[s];
//		
//		int nextNode = treeNode << 1;
//		int mid = (s + e) >> 1;
//		return maxTree[treeNode] =
//			Math.max(
//					maxInit(nextNode, s, mid)
//					,maxInit(nextNode|1, mid + 1, e)
//			);
//	}
//	public static int getMax(int treeNode, int s, int e, int left, int right) {
//		if(right < s || e < left)
//			return 0;
//		if(left <= s && e <= right)
//			return maxTree[treeNode];
//		int nextNode = treeNode << 1;
//		int mid =(s + e) >> 1;
//		return Math.max(
//					getMax(nextNode, s, mid, left, right),
//					getMax(nextNode|1,mid + 1, e, left, right)
//			);
//	}
//	public static int getMin(int treeNode, int s, int e, int left, int right) {
//		if(right < s || e < left)
//			return 11;
//		if(left <= s && e <= right)
//			return minTree[treeNode];
//		int nextNode = treeNode << 1;
//		int mid =(s + e) >> 1;
//		return Math.min(
//					getMin(nextNode, s, mid, left, right),
//					getMin(nextNode|1,mid + 1, e, left, right)
//			);
//	}
//	public static void main(String[] args)throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N		= Integer.parseInt(br.readLine());//1<=백만
//		arr		= new int[N+1];
//		minTree = new int[N<<2];
//		maxTree = new int[N<<2];
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i=1; i<=N; i++)
//			arr[i] = Integer.parseInt(st.nextToken());//1<=10
//		
//		minInit(1, 1, N);
//		maxInit(1, 1, N);
//		
//		 int len = 0;
//		 int s = 1;
//		 int e = 1;
//		 while(e<=N)
//		 {
//			 int max = getMax(1,1,N,s,e);
//			 int min = getMin(1,1,N,s,e);
//			 int diff = max - min;
//			 if(diff <= 2) {
//				 len = Math.max(len, e - s + 1);
//				 ++e;
//			 }
//			 else
//				 ++s;
//		 }
//		System.out.print(len);
//	}
//}
