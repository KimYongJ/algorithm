//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/27655
//1초 1024MB
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;
class Node {
	int left, right, floor;
	Node(int l, int r, int f){
		left=l;
		right=r;
		floor=f;
	}
}
class Main{
	
	static final int MAX = 1<<30;
	static int N, K, LEN;
	static int[] tree, lazy;
	
	public static void main(String[] args)throws Exception{
		Reader in = new Reader();
		
		N = in.nextInt();	// 발판의 개수
		K = in.nextInt();	// 가장 높은 발판의 층
		
		if(N == 1)
		{
			System.out.print(0);
			return;
		}
		
		ArrayList<Node> list = new ArrayList<>();
		
		TreeSet<Integer> set = new TreeSet<>();
		for(int i=0; i<N; i++)
		{
			int l = in.nextInt();
			int r = in.nextInt();
			int k = in.nextInt();
			list.add(new Node(l,r,k));
			set.add(l);// 좌표압축을 위해 한 set에 left와 right모두 담는다.
			set.add(r);// 좌표압축을 위해 한 set에 left와 right모두 담는다.
		}
		HashMap<Integer, Integer> map = new HashMap<>();

		LEN = 1;
		// 좌표 압축
		for(int num : set)
			map.put(num, LEN++);
		
		LEN -= 1;
		// 층기준 정렬
		Collections.sort(list, (a,b)-> a.floor - b.floor);
		
		tree = new int[LEN * 4];
		lazy = new int[LEN * 4];
		
		int ans = MAX;
		
		Arrays.fill(tree, ans);
		
		for(Node node : list)
		{
			if(node.floor == 1)	// 1층은 무조건 1로 다업데이트
			{
				update(1, 1, LEN, map.get(node.left), map.get(node.right), 1);
			}
			else
			{
				// 1층 이상부터, 해당 발판 범위의 최소 점프횟수를 가져온다.
				int min = query(1, 1, LEN, map.get(node.left), map.get(node.right));
				// 최소 점프 횟수 + 1을 바인딩
				update(1, 1, LEN, map.get(node.left), map.get(node.right), min + 1);
				// 현재 층이 목표층이면 ans갱신
				if(node.floor == K)
				{
					ans = Math.min(ans, min);
				}
			}
		}
		// ans가 갱신된적이 없다는 것은 목표층에 못간다는 것이므로 -1 출력
		System.out.print(ans == MAX ? -1 : ans);
	}
	public static int query(int treeNode, int s, int e, int left, int right) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return MAX;
		if(left<=s && e<= right)
			return tree[treeNode];
		
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		
		int r1 = query(nxt, s, mid, left, right);
		int r2 = query(nxt | 1, mid + 1, e, left, right);
		
		return Math.min(r1, r2);
	}
	public static void update(int treeNode, int s, int e, int left, int right, int value) {
		
		propagate(treeNode, s, e);
		
		if(right < s || e < left)
			return;
		if(left<=s && e<= right)
		{
			lazy[treeNode] = value;
			propagate(treeNode, s, e);
			return;
		}
		int mid = (s + e) >> 1;
		int nxt = treeNode << 1;
		update(nxt, s, mid, left, right, value);
		update(nxt | 1, mid + 1, e, left, right, value);
		
		tree[treeNode] = Math.min(tree[nxt], tree[nxt | 1]);
	}
	public static void propagate(int treeNode, int s, int e) {
		if(lazy[treeNode] != 0)
		{
			tree[treeNode] = lazy[treeNode];
			if(s != e)
			{
				
				lazy[treeNode << 1]		= lazy[treeNode];
				lazy[treeNode << 1 | 1] = lazy[treeNode];
			}
			lazy[treeNode] = 0;
		}
	}
}

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


