import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int dxy[][] = {{0,1},{1,0},{-1,0},{0,-1}};
	static boolean visit_alpha[] = new boolean[26];
	static int Y, X, result = 1;
	static char arr[][];
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new char[Y][X];
        
        for(int i=0; i<Y; i++)
            arr[i] = br.readLine().toCharArray();
         
        DFS(0,0,0);
        
        System.out.println(result);
    }
    public static void DFS(int y, int x, int depth) {
    	if(visit_alpha[arr[y][x]-'A']) {
    		if(result < depth)
    			result = depth;
    		return;
    	}
    	visit_alpha[arr[y][x]-'A'] = true;
    	for(int xy[] : dxy) {
    		int newY = y + xy[0];
    		int newX = x + xy[1];
    		if(newY>=0 && newX>=0 && newY<Y && newX<X)
    			DFS(newY, newX, depth+1);
    	}
    	visit_alpha[arr[y][x]-'A'] = false;
    }
}
class Node{
    int y, x, dist;
    boolean visit[];
    Node(int y, int x, int dist, boolean visit[]){
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.visit = visit;
    }
}