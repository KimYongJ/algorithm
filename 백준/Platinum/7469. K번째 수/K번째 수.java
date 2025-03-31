//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7469
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int left, right, sum;
	Node(int l, int r, int s){
		left=l; right=r; sum=s;
	}
}
class Obj implements Comparable<Obj>{
	int idx, value;
	Obj(int i, int v){
		idx = i; value = v;
	}
	@Override
	public int compareTo(Obj o) {
		if(value != o.value)
			return value - o.value;
		return idx - o.idx;
	}
}
class Main{
	
	static int N, Q;
	static ArrayList<Obj>		list;
	static ArrayList<Integer>	roots;
	static ArrayList<Node>		nodes;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N		= Integer.parseInt(st.nextToken());
		Q		= Integer.parseInt(st.nextToken());
		list	= new ArrayList<>();
		roots	= new ArrayList<>();		// 세그먼트 트리를 update할 때마다 버전(루트노드)를 담을 리스트
		nodes	= new ArrayList<>();		// 세그먼트 트리를 update할 대마다 해당 세그먼트 노드들을 1차원으로 담을 리스트
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			// 입력받은 순서대로, value를 담는다.
			list.add(new Obj(i,Integer.parseInt(st.nextToken())));
		// value가 낮은 순으로 그리고 idx가 낮은순으로 정렬
		Collections.sort(list);
		
		// init을 호출해 세그먼트 트리를 미리 생성해 놓는다.
		roots.add(init(0, N - 1)); 

		// list를 돌면서 인덱스를 순차적으로 마킹한다.
		for(Obj now : list)
		{
			int root = roots.get(roots.size() - 1);	// 루트노드 가져오기
			// 해당 루트 노드를 기준으로 새버전을 생성해가며 입력된 value의 인덱스를 마킹한다.
			// 세그먼트 트리에 마킹되는 것은, value가 작은 순으로 정렬된 입력순서(now.idx)이다.
			// 이렇게 인덱스를 마킹하면 추 후에 이분 탐색으로 k번째 수를 찾을 때 활용 가능하다.
			int rootsNode = update(root, 0, N - 1, now.idx);
			
			roots.add(rootsNode);	// 갱신된 버전(rootsNode)를 삽입
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int k = Integer.parseInt(st.nextToken());
			
			int s = 0;
			int e = N - 1;
			int res = 0;
			
			while(s<=e)
			{
				int mid = (s + e) >> 1;
				// 초기에 세그먼트 트리 생성을 위해 roots에 init을 add해놨기 때문에 초기값을 건너띄기 위해 mid에 + 1을 한다.
				int cal = sum(roots.get(mid + 1), 0, N - 1, i, j);
				if(k <= cal)
				{
					res = mid;
					e = mid - 1;
				}
				else
					s = mid + 1;
			}
			
			sb.append(list.get(res).value).append('\n');
		}
		System.out.print(sb);
	}
	public static int sum(int nodeNum, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return nodes.get(nodeNum).sum;
		
		int mid = (s + e) >> 1;
		
		return sum(nodes.get(nodeNum).left, s, mid, left, right) + 
				sum(nodes.get(nodeNum).right, mid + 1, e, left, right);
		
	}
	public static int update(int nodeNum, int s, int e, int targetIdx) {
		
		Node now = nodes.get(nodeNum);
		
		if(s == e)
			nodes.add(new Node(-1, -1, now.sum + 1));
		else
		{
			int mid = (s + e) >> 1;
			int l = now.left;
			int r = now.right;
			
			if(targetIdx <= mid)
				l = update(now.left, s, mid, targetIdx);
			else
				r = update(now.right, mid + 1, e, targetIdx);
			
			nodes.add(new Node(l, r, now.sum + 1));
		}
		return nodes.size() - 1;
	}
	public static int init(int s, int e) {
		if(s == e)
			nodes.add(new Node(-1, -1, 0));
		else
		{
			int mid = (s + e) >> 1;
			int l	= init(s, mid);
			int r	= init(mid + 1, e);
			
			nodes.add(new Node(l, r, 0));
		}
		return nodes.size() - 1;
	}
}
