//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/16566
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Main{

	static int size;
	static int[] arr, tree;
	
	public static int init(int treeNode, int s, int e) {
		if(s == e)
			return tree[treeNode] = arr[s];
		int mid = (s + e) >> 1;
		return tree[treeNode] = Math.max(init(treeNode*2, s, mid) ,init(treeNode*2 + 1, mid + 1, e));
	}
	public static int update(int treeNode, int s, int e, int originIdx) {
		if(originIdx < s || e < originIdx) // 목표 인덱스가 범위 밖이면 현재 트리노드 값을 리턴하여 max연산을 하도록 한다.
			return tree[treeNode];
		if(s == e)
		{	// 리프노드 도달시
			if(s == originIdx)
			{
				tree[treeNode] = 0;// 목적지라면 그 목적지의 카드를 뺀다(0으로 바꾼다)
			}
			return tree[treeNode];
		}
		
		int mid = (s + e) >> 1;

		return tree[treeNode] = Math.max(update(treeNode*2, s, mid, originIdx), update(treeNode*2 + 1, mid + 1, e, originIdx));
	}
	public static int getTarget(int treeNode, int s, int e, int target) {
		if(s == e) {
			update(1, 1, size, s);
			return arr[s];
		}
		int mid = (s + e) >> 1;				// 다음 범위
		int nextTreeNode = treeNode * 2;	// 다음 세그먼트 노드
		
		if(target < tree[nextTreeNode] && target < tree[nextTreeNode + 1]) {// 다음 노드들도 모두 target보다 크다면
			if(tree[nextTreeNode] < tree[nextTreeNode + 1]) // 왼쪽이 작다면 왼쪽으로 
				return getTarget(nextTreeNode, s , mid, target);
			else
				return getTarget(nextTreeNode + 1, mid + 1, e, target); // 오른쪽이 작다면 오른쪽 노드로 내려감
		}
		if(target < tree[nextTreeNode])
			return getTarget(nextTreeNode, s , mid, target);
		else	
			return getTarget(nextTreeNode + 1, mid + 1, e, target);
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Integer.parseInt(st.nextToken());			// N, 숫자 범위 1<=사백만 안씀
		size = Integer.parseInt(st.nextToken());	// 내카드번호 1<=N
		int K = Integer.parseInt(st.nextToken());	// 철수가 낼 카드 순서
		int h = (int)Math.ceil(Math.log(size) / Math.log(2));
		tree= new int[1<<(h + 1)];
		arr = new int[size + 1];
		
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=size; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		init(1, 1, size);	// 세그먼트 트리 초기화
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			int targetIdx = Integer.parseInt(st.nextToken());
			int res = getTarget(1,1, size, targetIdx);
			sb.append(res).append('\n');
		}
		
		System.out.print(sb);
	}
}