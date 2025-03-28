//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7469
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Object implements Comparable<Object>{
	int idx, originValue, compValue;
	Object(int i, int o, int c)
	{
		idx = i;
		originValue = o;
		compValue = c;
	}
	@Override
	public int compareTo(Object o) {
		if(originValue != o.originValue)
			return originValue - o.originValue;
		return idx - o.idx;
	}
}
class Node{
	int left, right, sum;
	Node(int l, int r, int s){
		left=l; right=r; sum=s;
	}
}
class Main{
	
	static int N, Q;
	
	static List<Integer> roots	= new ArrayList<>();// 갱신시마다 해당 루트 노드를 담을 리스트
	static List<Node> nodes		= new ArrayList<>();// 세그트리노드를 1차원으로 표현
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N	= Integer.parseInt(st.nextToken());
		Q	= Integer.parseInt(st.nextToken());
		ArrayList<Object> list = new ArrayList<>();
		
		TreeSet<Integer> set = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int n = Integer.parseInt(st.nextToken());
			list.add(new Object(i, n, 0));// 입력 순서와, 입력된값, 그리고 압축 숫자는 처음에 0으로 넣음
			set.add(n); // 값 압축을 위해 입력되는 값의 중복을 제거하고 정렬까지해주는 treeSet에 넣음
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		for(int s : set)
			map.put(s, idx++);
		
		for(int i=0; i<N; i++)
		{
			// 리스트를 순회하며 원래 값에 압축 값을 추가한다.
			Object now = list.get(i);
			now.compValue = map.get(now.originValue);
		}
		// 입력된 값을 오름차순정렬, 값이 같으면 먼저 입력된순(idx)으로 정렬
		Collections.sort(list);
		
		roots.add(init(0 , N));
		
		idx = 0;
		for(int value=0; value<N; value++)
		{
			while(idx < N && list.get(idx).compValue == value)
			{
				int rootNode = roots.get(roots.size() - 1);
				int root = update(rootNode, 0, N, list.get(idx).idx);
				
				roots.set(roots.size() - 1, root);
				
				++idx;
			}
			roots.add(roots.get(roots.size()-1));
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());
			
			int s = 0;
			int e = N;
			int res = 0;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				int cal = sum(roots.get(mid), 0, N, i, j);
				if(k <= cal)
				{
					res = mid;
					e = mid - 1;
				}
				else s = mid + 1;
			}
			sb.append(list.get(res).originValue).append('\n');
		}
		System.out.print(sb);
	}
	public static int sum(int nodeNum, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		Node now = nodes.get(nodeNum);
		if(left <=s && e<= right)
			return now.sum;
		
		int mid = (s + e) >> 1;
		
		return sum(now.left, s, mid, left, right) +
				sum(now.right, mid + 1, e, left, right);
	}
	public static int update(int nodeNum, int s, int e, int targetIdx) {
		Node node = nodes.get(nodeNum);
		if(s == e)
			nodes.add(new Node(-1,-1, node.sum + 1));
		else
		{
			int mid = (s + e) >> 1;
			if(targetIdx <= mid) {
				int l = update(node.left, s, mid, targetIdx);
				nodes.add(new Node(l, node.right, node.sum + 1));
			}
			else {
				int r = update(node.right, mid + 1, e, targetIdx);
				nodes.add(new Node(node.left, r, node.sum + 1));
			}
		}
		return nodes.size() - 1;
	}
	public static int init(int s, int e) {
		if(s == e)
			nodes.add(new Node(-1,-1,0));
		else
		{
			int mid = (s + e) >> 1;
			int l = init(s, mid);
			int r = init(mid + 1, e);
			nodes.add(new Node(l, r, 0));
		}
		return nodes.size() - 1;
	}
}