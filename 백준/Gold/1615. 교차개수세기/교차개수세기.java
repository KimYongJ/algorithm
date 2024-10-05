//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/1615
class Node{
	int node;
	Node next;
	Node(int node, Node next){
		this.node = node;
		this.next = next;
	}
}
public class Main {

    static final int N = 1 << 11;
    
    static long[] tree = new long[N << 1];
    
    static int read() throws Exception {// 빠른 입력을 위한 함수
    	int c, n = System.in.read() & 15;
    	while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
    	return n;
    }
    static long query(int treeNode, int s, int e, int l, int r) {
        if (l > e || r < s)
        	return 0;
        
        if (l <= s && e <= r)
        	return tree[treeNode];
        
        int m = (s + e) >> 1;
        
        return query(treeNode << 1, s, m, l, r) + query(treeNode << 1 | 1, m + 1, e, l, r);
    }

    static void update(int treeNode, int s, int e, int l, int r) {
        if (l > e || r < s)
        	return;
        if (l <= s && e <= r)
        {
            tree[treeNode]++;
            return;
        }
        
        int m = (s + e) >> 1;
        
        update(treeNode << 1, s, m, l, r);
        update(treeNode << 1 | 1, m + 1, e, l, r);
        
        tree[treeNode] = tree[treeNode << 1] + tree[treeNode << 1 | 1];
    }
    public static void main(String[] args) throws Exception {
        int n			= read();
        int m			= read();
        Node adNode[]	= new Node[n + 1];

        for (int i = 0; i < m; i++)
        {
            int a = read();
            int b = read();
            adNode[a] = new Node(b, adNode[a]);
        }

        long ans = 0;
        for (int i = 1; i <= n; i++)
        {
            for(Node next=adNode[i]; next != null; next=next.next)
                ans += query(1, 0, N - 1, next.node + 1, n);
            
            for(Node next=adNode[i]; next != null; next=next.next)
                update(1, 0, N - 1, next.node, next.node);
            
        }
        System.out.println(ans);
    }
}