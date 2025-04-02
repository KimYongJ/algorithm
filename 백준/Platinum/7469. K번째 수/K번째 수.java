//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7469
//1초 256MB
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Object implements Comparable<Object>{
	int idx, value;
	Object(int i, int v){
		this.idx = i;
		this.value = v;
	}
	@Override
	public int compareTo(Object o) {
		if(value != o.value)
			return value - o.value;
		return idx - o.idx;
	}
}
class Node{
	int left, right, sum;
	Node(int l, int r, int s){
		this.left	= l;
		this.right	= r;
		this.sum	= s;
	}
}
class Main{
	
	static ArrayList<Node> nodes;

	public static void main(String[] args)throws Exception{
		Reader in			= new Reader();
		int N				= in.nextInt();// 배열의크기 N(1<=100,000)
		int Q				= in.nextInt();// 쿼리수 Q(1<=5,000)
		int roots[]			= new int[N + 1];// 루트는 원래 N개만 필요하지만, 최초 세그먼트 초기화시킨 후 담을 루트노드 값 때문에 + 1함
		List<Object> list	= new ArrayList<>();
		nodes				= new ArrayList<>();

		for(int i=0; i<N; i++)
			list.add(new Object(i, in.nextInt()));
		
		Collections.sort(list);// value가 낮은 기준으로 오름차순 정렬
		
		// 세그먼트트리를 초기화하고 루트노드 번호를 roots[0]에 담는다.
		roots[0] = init(0, N - 1);
		
		for(int i=0; i<list.size(); i++)
		{
			// 배열의 크기만큼 PST버전을 만들어 주고 만든 후 PST의 각각의 루트 번호를 roots에 담는다.
			// i+1번째 루트노드는, i번째 루트 노드를 통해 구해진다.
			// value기준으로 오름차순 정렬된 입력순서(idx)를 세그먼트 트리에 마킹하며 버전을 갱신해준다.
			roots[i + 1] = update(roots[i], 0, N - 1, list.get(i).idx);
		}
		
		StringBuilder sb = new StringBuilder();
		while(Q-->0)
		{
			int i = in.nextInt() - 1; // 인덱스가 0부터시작했기 때문에 -1을 해주어 인덱스 보정
			int j = in.nextInt() - 1; // 인덱스가 0부터시작했기 때문에 -1을 해주어 인덱스 보정
			int k = in.nextInt();
			
			int s = 0;
			int e = N - 1;
			int res = 0;
			while(s <= e)
			{
				int mid = (s + e) >> 1;
                // 이분탐색을 통해 i,j범위의 합이 k가되는 가장 작은 mid를 찾는다.
				int cal = sum(roots[mid + 1], 0, N - 1, i, j);
				if(k <= cal)
				{
					res = mid;
					e = mid - 1;
				}
				else s = mid + 1;
			}
			
			sb.append(list.get(res).value)
				.append('\n');
		}
		System.out.print(sb);
	}
	public static int init(int s, int e) {
		if(s == e)// 리프 노드인 경우 왼쪽, 오른쪽 자신은 -1로, sum 0으로
		{
			nodes.add(new Node(-1, -1, 0));
			return nodes.size() - 1;
		}
		int mid = (s + e) >> 1;
		int l = init(s, mid);
		int r = init(mid + 1, e);
		
		nodes.add(new Node(l, r, 0));
		
		return nodes.size() - 1;
	}
	public static int update(int nowNode, int s, int e, int targetIdx) {
		
		Node now = nodes.get(nowNode);
		
		if(s == e)
		{
			nodes.add(new Node(-1, -1, now.sum + 1));
			return nodes.size() - 1;
		}
		int mid = (s + e) >> 1;
		int l = now.left;
		int r = now.right;
		
		if(targetIdx <= mid)
			l = update(now.left, s, mid, targetIdx);
		else
			r = update(now.right, mid + 1, e, targetIdx);
		
		nodes.add(new Node(l, r, now.sum + 1));
		
		return nodes.size() - 1;
	}
	
	public static int sum(int nowNode, int s, int e, int left, int right) {
		if(e < left || right < s)
			return 0;
		
		if(left<=s && e<=right)
			return nodes.get(nowNode).sum;
		
		int mid = (s + e) >> 1;
		
		return sum(nodes.get(nowNode).left, s, mid, left, right)
				+ sum(nodes.get(nowNode).right, mid + 1, e, left, right);
	}
}
//7 3			// 배열의크기 N(1<=100,000), 쿼리수 Q(1<=5,000)
//1 5 2 6 3 7 4	// 각 정수는 절대 값 십억을 넘지 않는 정수
//2 5 3			// 왼쪽범위i,오른쪽범위j,찾을k번째수(1<i,j<=N / 1<= k<= j-i+1)
//4 4 1
//1 7 3
////답
//5
//6
//3

class Reader {
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
