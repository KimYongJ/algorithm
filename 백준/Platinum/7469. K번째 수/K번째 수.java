//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7469
//1초 256MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;
class Obj implements Comparable<Obj>{
	int idx, value;
	Obj(int i, int v){
		idx=i; value=v;
	}
	@Override
	public int compareTo(Obj o) {
		if(value != o.value)
			return value - o.value;
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
	
	static int N, Q, arr[];
	static ArrayList<Node> nodes	= new ArrayList<>();
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N	= Integer.parseInt(st.nextToken());// 주어진수의 개수(1<=십만)
		Q	= Integer.parseInt(st.nextToken());// 쿼리수(1<=오천)
		arr = new int[N];
		
		ArrayList<Integer> roots = new ArrayList<>();
		roots.add(init(0, N));	// 최초에 PST 트리를 미리 세팅해놓는다.
		
		st = new StringTokenizer(br.readLine());
		TreeSet<Integer> tset = new TreeSet<>();// 좌표압축을 위해 사용
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());// 절대값 10억이하
			tset.add(arr[i]);
		}
		// 좌표압축을 위한 map선언
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		for(int s : tset)
			map.put(s, idx++);// 원래 값을 입력시 압축된 숫자 반환
		
		// 압축된 값과 각 좌표의 idx기준으로 list를 재생성
		ArrayList<Obj> list = new ArrayList<>();
		for(int i=0; i<N; i++)
			list.add(new Obj(i, map.get(arr[i])));
		
		// 압축된 값을 기준으로 오름차순, 같을 경우 인덱스기준 오름차순
		Collections.sort(list);
		
		// 아래 반복문 목표 : 압축된 값이 낮은 순으로 돌면서 해당 idx를 순차적으로 PST에 마킹한다.
		// 여기서 중요한것은 압축된 값을 마킹하는게아니라, 압축된 값이 낮은순으로 그 idx를 마킹하는 점에있다.
		// PST는 업데이트시 1차원 리스트에 데이터를 지속적으로 쌓기 때문에 업데이트 전 정보도 갖고 있을 수 있다.
		idx = 0;// list에서의 인덱스
		for(int value=0; value<=N; value++)// 압축된 값은 결국 N이하일 것이기 때문에 최대는 N
		{
			while(idx<list.size() && list.get(idx).value == value)
			{
				int rootIdx = roots.get(roots.size()-1);
				int nodeIdx = update(rootIdx, 0, N, list.get(idx).idx);
				// 최종 루트값을 현재 구한 루트값으로 덮어씌움(set)
				roots.set(roots.size()-1, nodeIdx);
				
				++idx;
			}
			// 다음을 위해 마지막 루트노드를 하나 추가로 넣음(즉, 복사함)
			roots.add(roots.get(roots.size() - 1));
		}
		
		Arrays.sort(arr);
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;	// 범위
			int j = Integer.parseInt(st.nextToken()) - 1;	// 범위
			int k = Integer.parseInt(st.nextToken());		// k번째 수
			
			int s	= 0;
			int e	= N;
			int res = 0;
			
			while(s <= e)
			{
				int mid = (s + e) >> 1;
				// mid번째 i,j범위에 인덱스 마킹 값을 가져온다
				// k가 3이고, mid번째 i,j범위에 인덱스 마킹값이 5면, k번째 수가 이미 입력된것,
				// 그래서 sum함수의 결과가 k이상인 가장 작은수가 되도록 res를 세팅해야함
				int cal = sum(roots.get(mid), 0, N, i, j);
				if(k <= cal)
				{
					res = mid;
					e = mid - 1;
				}
				else
					s = mid + 1;
			}
			sb.append(arr[res]).append('\n');
		}
		System.out.print(sb);
	}
	public static int sum(int rootIdx, int s, int e, int left, int right) {
		Node node = nodes.get(rootIdx);
		
		if(left <= s && e <= right)
			return node.sum;
		if(e < left || right < s)
			return 0;
		
		int mid = (s + e) >> 1;
		
		return sum(node.left, s, mid, left, right) +
				sum(node.right, mid + 1, e, left, right);
	}
	public static int update(int rootIdx, int s, int e, int targetIdx) {
		Node node	= nodes.get(rootIdx);
		int nextSum = node.sum + 1;
		// 리프노드일 경우 리프노드 추가
		if(s == e)
			nodes.add(new Node(-1, -1, nextSum));
		else
		{
			int mid = (s + e) >> 1;
			// 1을 추가하려는 target인덱스가 mid 이하인 경우 왼쪽 탐색
			if(targetIdx <= mid)
			{
				int leftIdx = update(node.left, s, mid, targetIdx);
				nodes.add(new Node(leftIdx, node.right, nextSum));
			}
			else
			{
				int rightIdx = update(node.right, mid + 1, e, targetIdx);
				nodes.add(new Node(node.left, rightIdx, nextSum));
			}
		}
		
		return nodes.size() - 1;
	}
	public static int init(int s, int e) {
		if(s == e)// 리프노드일 경우
			nodes.add(new Node(-1, -1, 0));
		else
		{
			int mid		= (s + e) >> 1;
			int left	= init(s , mid);
			int right	= init(mid + 1, e);
			// left, right의 nodes안에서 인덱스를 집어 넣어 1차원에 트리를 표현
			nodes.add(new Node(left, right, 0));
		}
		return nodes.size() - 1;
	}
}