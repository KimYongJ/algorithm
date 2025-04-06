//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/20212
//1초 512MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class First{
	int i, j, v;
	First(int i, int j, int v){
		this.i=i;
		this.j=j;
		this.v=v;
	}
}
class Second implements Comparable<Second>{
	int i, j, k, order;
	Second(int i, int j, int k, int o){
		this.i=i;
		this.j=j;
		this.k=k;
		this.order=o;
	}
	@Override
	public int compareTo(Second o) {
		return k - o.k;
	}
}
class Main{
	
	static int LEN;
	static long[] result;
	static long[] gajungchi, gajungchitree;
	static long[] lazy, tree;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		List<First> first	= new ArrayList<>();
		List<Second> second = new ArrayList<>();
		List<Integer> nums	= new ArrayList<>();
		for(int q=0; q<Q; q++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());// 1 or 2
			int i = Integer.parseInt(st.nextToken());// 1 ≤ i ≤ j ≤ 1,000,000,000
			int j = Integer.parseInt(st.nextToken());// 1 ≤ i ≤ j ≤ 1,000,000,000
			int k = Integer.parseInt(st.nextToken());// f가 1일경우( -100,000 ≤ k ≤ 100,000 ) 2일 경우(1 ≤ k ≤ 1번 쿼리의 개수)
			if(f == 1)	// 1인 경우 i~j까지에 K를 더함
			{
				first.add(new First(i,j,k));
			}
			else	// 2인 경우 k번째 1번 쿼리까지 적용되었을 때 i~j의 합을 출력
			{
				second.add(new Second(i, j, k, second.size()));
			}
			nums.add(i);
			nums.add(j);
		}

		result = new long[second.size()];
		// 입력된 인덱스들 정렬 및 중복제거
		Collections.sort(nums);
		
		nums = distinct(nums);
		
		// 좌표압축 실행
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<nums.size(); i++)
			map.put(nums.get(i), i);
		// 가중치 계산을 포함한 배열의 길이 생성
		LEN = nums.size()  * 2 - 1;
		
		gajungchi		= new long[LEN + 1];
		gajungchitree	= new long[LEN * 4];
		lazy			= new long[LEN * 4];
		tree			= new long[LEN * 4];
		// 좌표 압축시 압축된 숫자들 사이에 얼만큼의 갭이있는지 저장한다.
		for(int i=1; i<=LEN; i++)
		{
			if(i % 2 == 0)	// 압축된 숫자들 사이인 경우
			{
				int left = nums.get(i / 2 - 1);
				int right= nums.get(i / 2);
				gajungchi[i] = right - left - 1;
				continue;
			}
			
			gajungchi[i] = 1;
		}
		
		init(1, 1, LEN);// 가중치 트리 초기화
		
		Collections.sort(second);
		
		for(int i=0, j=0; i<first.size() && j<second.size(); i++)
		{
			First now = first.get(i);
			int s = map.get(now.i) * 2 + 1;
			int e = map.get(now.j) * 2 + 1; 
			
			update(1, 1, LEN, s, e, now.v);
			
			while(j < second.size() && second.get(j).k == i + 1)
			{
				Second sd = second.get(j);
				
				s = map.get(sd.i) * 2 + 1;
				e = map.get(sd.j) * 2 + 1;
				
				result[sd.order] = query(1, 1, LEN, s, e);
				
				j++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<second.size(); i++)
			sb.append(result[i])
				.append('\n');
		
		System.out.print(sb);
	}
	public static long query(int treeNode, int s,int e, int left, int right) {
		propagate(treeNode, s, e);
		if(e < left || right < s)
			return 0;
		if(left<=s && e<=right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		
		return query(treeNode << 1, s, mid, left, right)
				+ query(treeNode << 1 | 1, mid + 1, e, left, right);
	}
	public static void update(int treeNode, int s, int e, int left, int right, long value) {
		propagate(treeNode, s, e);
		
		if(e < left || right < s)
			return;
		
		if(left<=s && e<=right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		
		int mid = (s + e) >> 1;
		
		update(treeNode << 1, s, mid, left, right, value);
		update(treeNode << 1 | 1,mid + 1, e, left, right, value);
		
		tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] += lazy[treeNode] * gajungchitree[treeNode];
			if(s != e)
			{
				lazy[treeNode << 1]		+= lazy[treeNode];
				lazy[treeNode << 1 | 1] += lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
	public static long init(int treeNode, int s, int e) {
		if(s == e)
			return gajungchitree[treeNode] = gajungchi[s];

		int mid = (s + e) >> 1;
		
		return gajungchitree[treeNode] = 
				init(treeNode << 1, s, mid) 
				+ init(treeNode << 1 | 1, mid + 1, e);
	}
	public static List<Integer> distinct(List<Integer> origin){
		List<Integer> res = new ArrayList<>();
		int prv = -1;
		for(int i=0; i<origin.size(); i++)
		{
			int now = origin.get(i);
			
			if(now != prv)
				res.add(now);
			
			prv = now;
		}
		return res;
	}
}