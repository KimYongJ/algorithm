//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/7569
import java.util.ArrayDeque;
class Node{
	int z, y, x;
	Node(int z, int y, int x){this.z=z; this.x=x; this.y=y;}
}

class Main{

	static int read() throws Exception {
        int c, N = System.in.read() - 48;
        while ((c = System.in.read()) > 32)
            N = 10 * N + c - 48;
        return N;
    }
   
    public static void main(String[] args)throws Exception{
    	final int[][] dxy	= {{0,0,1},{0,0,-1},{0,1,0},{0,-1,0},{1,0,0},{-1,0,0}};
    	ArrayDeque<Node> q	= new ArrayDeque<>();
    	
        int X			= read();
        int Y			= read();
        int Z			= read();
        int result		= 0;
        int zero		= 0;
        int arr[][][]	= new int[Z][Y][X];
        
        for(int z=0; z<Z; z++)
            for(int y=0; y<Y; y++)
                for(int x=0; x<X; x++)
                {
                    arr[z][y][x] = read();
                    if(arr[z][y][x]==1)
                        q.add(new Node(z,y,x));
                    else if(arr[z][y][x] == 0)
                    	zero++;
                }

        while(!q.isEmpty())
        {
        	int size = q.size();
        	while(size-- >0)
        	{
            	Node now = q.poll();
            	
                for(int[] xy : dxy)
                {
                    int z1 = now.z + xy[0];
                    int y1 = now.y + xy[1];
                    int x1 = now.x + xy[2];

                    if(z1<0 || z1>=Z || x1<0 || x1>=X || y1<0 || y1>=Y)
                        continue;
                    if(arr[z1][y1][x1] == 0)
                    {
                        arr[z1][y1][x1] = 1;
                        zero--;
                        q.add(new Node(z1, y1, x1));    
                    }
                }
        	}
        	result++;
        }
        System.out.println(zero == 0 ? result - 1 : -1);
    }

}