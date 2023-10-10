// https://github.com/KimYongJ/algorithm
import java.util.HashSet;
class Main{
	public static int[] parent;
    public static void main(String[] args)throws Exception{
        int n = read(); // 정점의 갯수
        int m = read(); // 간선의 갯수
        
        parent = new int[n+1]; // 부모 노드를 담을 배열
        for(int i=1; i<n+1; i++)
        	parent[i] = i; // 자기자신으로 부모노드 초기화
        
        for(int i=0; i<m; i++) {
        	int aNode = read();
        	int bNode = read();
        	
        	int aParent = getParent(aNode);
        	int bParent = getParent(bNode);
        	
        	if(aParent!=bParent) { // 부모노드가 같지 않다면 aParent의 값을 모두 bParent로 치환
        		changeAtoB(aParent,bParent,n);
        	}
        	
        }
        
        HashSet<Integer> set = new HashSet<>();
        for(int i=1; i<n+1; i++)
        	set.add(parent[i]);
        
        System.out.println(set.size());
    }
    public static void changeAtoB(int a, int b,int len) {
    	for(int i=0; i<len+1; i++) {
    		if(parent[i]==a) {
    			parent[i] = b;
    		}
    	}
    }
    public static int getParent(int x) {
    	if(parent[x]==x) return x;
    	return getParent(parent[x]);
    }
    static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
