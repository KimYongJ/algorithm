//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/11012
import java.util.ArrayList;
import java.util.Arrays;
class Point implements Comparable<Point>{
	int x, y;
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	@Override
	public int compareTo(Point o) {
		return y - o.y;
	}
}
class Node{
	int left, right, sum;
	Node(int l, int r, int s){
		left=l; right=r; sum=s;
	}
}
class Main{
	
	static final int LEN = 100_001;
	static ArrayList<Node> nodes;
	
	public static void main(String[] args)throws Exception{
		StringBuilder sb = new StringBuilder();
		Reader in = new Reader();
		int T = in.nextInt();//테스트케이스 수(1<=20)
		while(T-->0)
		{
			int N = in.nextInt();// 좌표수
			int M = in.nextInt();// 쿼리수
			Point point[] = new Point[N];
			int roots[] = new int[LEN + 1];
			nodes = new ArrayList<>();
			
			for(int i=0; i<N; i++)
			{
				int x	= in.nextInt() + 1;// 좌표의수N(0<=10,000)
				int y	= in.nextInt() + 1;// 쿼리수M(0<=50,000)
				point[i]= new Point(x,y);
			}
			// 초기 세그먼트 트리 생성, roots[0]에는 초기 세그먼트트리의 root번호, 즉 nodes의 사이즈가 들어간다.
			roots[0] = init(1, LEN);
			
			Arrays.sort(point);
			
			int prevY = 0;
			for(Point p : point)
			{
				for(int y=prevY; y<=p.y; y++)
				{
					// 이전 탐색한 y좌표 부터 현재 파악하려는 y좌표까지 복사를 진행한다.
					// 이로써 쿼리에서 어떤 y좌표가 입력되어도 그 때의 세그먼트트리 루트노드를 찾아갈 수 있다.
					roots[y] = roots[prevY]; 
				}
				// 복사된 루트노드(root[p.y])를 시작으로 x의 인덱스를 마킹하고 새로운 루트 노드를 재저장한다.
				// p.y가 같은 값이 여러개 입력되어도, 결국 roots[p.y]에는 마지막으로 x좌표를 업데이트한 루트노드번호만 저장됨
				roots[p.y] = update(roots[p.y], 1, LEN, p.x);
				
				prevY = p.y;
			}
			// 남은 y좌표를 끝까지 update한다.
			for(int y=prevY; y<=LEN; y++)
				roots[y] = roots[prevY];
			
			int result = 0;
			
			while(M-->0)
			{
				// l, r, b, t가 주어짐 (0<=10의5승), l과r은 x좌표, b,t는 y좌표
				int l = in.nextInt() + 1;
				int r = in.nextInt() + 1;
				int b = in.nextInt() + 1;
				int t = in.nextInt() + 1;
				
				result += sum(roots[t], 1, LEN, l, r)
								- sum(roots[b - 1], 1, LEN, l, r);
			}
			sb.append(result)
				.append('\n');
		}
		System.out.print(sb);
	}
	public static int init(int s, int e) {
		if(s == e)	// 리프노드인 경우 노드를 추가하고 해당 노드 번호를 반환하고 종료
		{
			nodes.add(new Node(-1, -1, 0));
			return nodes.size() - 1;
		}
		// 리프노드가 아닌 경우 왼쪽 오른쪽을 탐색하며 노드를 만들어나간다.
		int mid = (s + e) >> 1;
		int l = init(s, mid);
		int r = init(mid + 1, e);
		nodes.add(new Node(l, r, 0));
		// 최종적으로 자기자신의 노드번호(size - 1)를 반환
		return nodes.size() - 1;
	}
	public static int update(int nowNode, int s, int e, int targetIdx) {
		
		Node now = nodes.get(nowNode);
		
		if(s == e)// 리프노드인 경우, 기존 sum에서 + 1을 하여 x좌표에 대해 입력된 횟수를 1더함
		{
			nodes.add(new Node(-1, -1, now.sum + 1));
			return nodes.size() - 1;
		}
		// 리프노드가 아닌 경우, targetIdx의 값, 즉, 목표로하는 x좌표의 값에 따라 왼쪽으로갈지, 오른쪽으로 갈지 정하여 탐색
		int mid = (s + e) >> 1;
		int l = now.left;
		int r = now.right;
		
		if(targetIdx <= mid)
			l = update(now.left, s, mid, targetIdx);
		else
			r = update(now.right, mid + 1, e, targetIdx);
		
		// 왼쪽, 혹은 오른쪽 자식이 갱신되었을 텐데, 그 갱신된 값을 통해 노드를 새롭게 생성한다. 이 때 x좌표에 대해 입력된 횟수 + 1을함
		nodes.add(new Node(l,r, now.sum + 1));
		// 갱신된 노드번호(size - 1)반환
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
//1				// 테스트케이스 수(1<=20)
//3 1			// 좌표의수N(0<=10,000), 쿼리수M(0<=50,000)
//3 5			// N개의 줄에 x,y 각 좌표가 주어짐 각수는 0<=10의5승
//2 3
//1 1
//1 2 1 3		// M개의 줄에 l, r, b, t가 주어짐 (0<=10의5승), l과r은 x좌표, b,t는 y좌표
////답 : 2

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
