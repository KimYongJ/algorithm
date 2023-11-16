// https://github.com/KimYongJ/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
class Main{
	
	public static int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	int N = Integer.parseInt(br.readLine());
    	
    	char[][] blind_no = new char[N][N]; // 적록색약이 아닌 사람
    	char[][] blind_yes = new char[N][N];// 적록색약인 사람
    	for(int i=0; i<N; i++) {
    		blind_no[i] = br.readLine().toCharArray();
    		for(int j=0; j<N; j++) {
    			char c = blind_no[i][j];
    			if(c=='G') c = 'R';
    			blind_yes[i][j] = c; // 적록색약인 사람은 G를 R로 바꿔 저장한다.
    		}
    	}
    	
    	sb.append( check(blind_no,N) )
	    	.append(' ')
	    	.append( check(blind_yes,N) );
	
    	System.out.println(sb);
    	
    }
    public static int check(char[][] arr,int N) {
    	int cnt = 0; 
    	for(int i=0; i<N; i++) 
    		for(int j=0; j<N; j++) 
    			if(arr[i][j] != 0) {
    				cnt++;
    				DFS(arr,i,j,N,arr[i][j]);
    			}
    	return cnt;
    }
    public static void DFS(char[][]arr,int x, int y,int N,char base) {
    	arr[x][y] = 0;
    	for(int i=0; i<4; i++) {
    		int x1 = x + dxy[i][0];
    		int y1 = y + dxy[i][1];
    		
    		if( x1<0 || y1<0 || x1>=N || y1>=N || arr[x1][y1]==0 || arr[x1][y1] != base)
    			continue;
    		
    		DFS(arr,x1,y1,N,base);
    	}
    }
}


//// 이하 BFS방식 
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.ArrayDeque;
//class Main{
//	
//	public static int[][] dxy = {{-1,0},{1,0},{0,-1},{0,1}};
//    public static void main(String[] args)throws Exception{
//    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	StringBuilder sb = new StringBuilder();
//    	int N = Integer.parseInt(br.readLine());
//    	
//    	char[][] blind_no = new char[N][N]; // 적록색약이 아닌 사람
//    	char[][] blind_yes = new char[N][N];// 적록색약인 사람
//    	for(int i=0; i<N; i++) {
//    		blind_no[i] = br.readLine().toCharArray();
//    		for(int j=0; j<N; j++) {
//    			char c = blind_no[i][j];
//    			if(c=='G') c = 'R';
//    			blind_yes[i][j] = c; // 적록색약인 사람은 G를 R로 바꿔 저장한다.
//    		}
//    	}
//    	
//    	sb.append( check(blind_no,N) )
//	    	.append(' ')
//	    	.append( check(blind_yes,N) );
//	
//    	System.out.println(sb);
//    	
//    }
//    public static int check(char[][] arr,int N) {
//    	int cnt = 0; 
//    	for(int i=0; i<N; i++) 
//    		for(int j=0; j<N; j++) 
//    			if(arr[i][j] != 0) {
//    				cnt++;
//    				BFS(arr,i,j,N);
//    			}
//    	return cnt;
//    }
//    public static void BFS(char[][]arr,int x, int y,int N) {
//    	ArrayDeque<Point> q = new ArrayDeque<>();
//    	q.add(new Point(x,y));
//    	char base = arr[x][y];
//    	
//    	while(!q.isEmpty()) {
//    		Point cur = q.poll();
//    		for(int i=0; i<4; i++) {
//    			int x1 = cur.x + dxy[i][0];
//    			int y1 = cur.y + dxy[i][1];
//    			
//    			if( x1<0 || y1<0 || x1>=N || y1>=N || arr[x1][y1] != base) {
//    				continue;
//    			}
//    			arr[x1][y1] = 0;
//    			q.add(new Point(x1,y1));
//    		}
//    	}
//    }
//}
//class Point{
//	int x,y;
//	public Point(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}