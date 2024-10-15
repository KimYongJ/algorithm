import java.util.ArrayDeque;
class Node {
    int x, y;
    public Node(int x, int y){this.x = x;  this.y = y;}
}
public class Main {
	
	static final int[][] dxy = {{1,0}, {0,1}, {-1,0}, {0,-1}};	// 동서남북
	static int[] dice = {1, 2, 3, 4, 5, 6};						// 윗 - 뒷 - 오 - 왼 - 앞 - 바닥
    static int Y, X, K, dir, dy, dx, map[][];
    
    static int read() throws Exception {// 빠른 입력을 위한 함수
    	int c, n = System.in.read() & 15;
    	while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
    	return n;
    }
    
    public static void change_dice(){
    	int temp = dice[0];
    	if(dir == 0)
    	{
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[2];
            dice[2] = temp;
        }
    	else if(dir == 1)
        {
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[4];
            dice[4] = temp;
        }
    	else if(dir == 2)
    	{
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = temp;
    	}
    	else if(dir == 3)
    	{
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[1];
            dice[1] = temp;
        }
    }

    public static int bfs(){
    	ArrayDeque<Node> q	= new ArrayDeque<>();
    	boolean[][] visite	= new boolean[Y+1][X+1];
    	visite[dy][dx]	= true;
        q.offer(new Node(dy, dx));
        
        int cnt = 1;
        while (!q.isEmpty())
        {
            Node node = q.poll();
            for(int xy[] : dxy)
            {
            	int ny = node.y + xy[1];
                int nx = node.x + xy[0];

                if(1 <= nx && nx <= Y && 1 <= ny && ny <= X 
                	&&!visite[nx][ny] && map[nx][ny] == map[dy][dx])
                {
                	visite[nx][ny] = true;
                    cnt++;
                    q.offer(new Node(nx, ny));
                }
            }
        }
        return cnt;
    }
    public static void main(String[] args) throws Exception {
        Y	= read();
        X	= read();
        K	= read();
        map	= new int[Y+1][X+1];

        for(int y=1;y<=Y;y++)
            for(int x=1;x<=X;x++)
                map[y][x] = read();
        
        int score	= 0;
        dy			= 1;
        dx			= 1;
        
        while (K-->0)
        {
            // 해당 방향으로 이동
        	dx += dxy[dir][0];
        	dy += dxy[dir][1];
            if(dy < 1 || dy > Y || dx < 1 || dx > X)
            {// 이동할 수 없다면 반대로 전환
            	dx -= dxy[dir][0];
            	dy -= dxy[dir][1];
                dir = (dir+2) % 4;
                dx += dxy[dir][0];
                dy += dxy[dir][1];
            }

            change_dice();// 주사위 면 변화
            
            score += bfs() * map[dy][dx];// 점수 획득

            // 이동방향 변경
            if(dice[5] > map[dy][dx])
                dir = (dir + 1) % 4;
            else if(dice[5] < map[dy][dx])
                dir = (dir==0?3:dir-1);
        }

        System.out.println(score);
    }
}