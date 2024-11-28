//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15898
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{	int value;char ch;Node(int v, char c){value=v; ch=c;} Node(){value=0;ch='W';} }
class Final{ int y, x, idx, dir;Final(int y, int x, int i, int d){this.y=y; this.x=x; idx=i;dir=d;} Final(){y=0;x=0;idx=0;dir=0;}}

class Main{
	
	static Node[][][][] origin;
	static Node[][] result, dummy;
	static Final finalData[];
	static int N, max;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N			= Integer.parseInt(br.readLine());	// 재료 개수(3 ≤ n ≤ 10)
		origin		= new Node[4][N][4][4];
		dummy		= new Node[5][5];
		result		= new Node[5][5];
		finalData	= new Final[3];
		
		for(int i=0; i<3; i++)
			finalData[i] = new Final();
		
		for(int i=0; i<N; i++)
		{
			// 초기 효능 값(숫자)
			for(int y=0; y<4; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=0; x<4; x++)
					origin[0][i][y][x] = new Node(Integer.parseInt(st.nextToken()),'W');
			}
			// 초기 원소 값(알파벳)
			for(int y=0; y<4; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=0; x<4; x++)
					origin[0][i][y][x].ch = st.nextToken().charAt(0);
			}
		}
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
			{
				dummy[y][x]	= new Node();
				result[y][x]= new Node();
			}
		// 각 재료에 대해 미리 모든 회전을 저장해 놓음
		for(int i=1; i<4; i++)
			origin[i] = rotate(origin[i-1]);

		bruteforce(0, 0);		
		
		System.out.print(max);
	}
	public static Node[][][] rotate(Node o[][][]) {
		Node[][][] arr = new Node[N][4][4];
		
		for(int n=0; n<N; n++)
			for(int y=0; y<4; y++)
				for(int x=0; x<4; x++)
					arr[n][y][x] = new Node(o[n][y][x].value, o[n][y][x].ch);
		
		for(int i=0; i<N; i++)
		{
			for(int y=0; y<4; y++)
				for(int x=0; x<4; x++)
				{
					dummy[y][x].value = arr[i][y][x].value;
					dummy[y][x].ch = arr[i][y][x].ch;
				}
			for(int y=0; y<4; y++)
				for(int x=0; x<4; x++)
				{
					arr[i][y][x].value = dummy[3-x][y].value;
					arr[i][y][x].ch = dummy[3-x][y].ch;
				}
		}
		return arr;
	}
	public static void bruteforce(int depth, int bitmask) {
		if(depth == 3)
		{
			cal();
			return;
		}
		for(int i=0; i<N; i++)
			if((bitmask & (1<<i)) == 0)
				for(int dir=0; dir<4; dir++)//현재(0),90도(1),180도(2),270(3)
					for(int y=0; y<=1; y++)
						for(int x=0; x<=1; x++)
						{
							finalData[depth].y		= y;
							finalData[depth].x		= x;
							finalData[depth].idx	= i;
							finalData[depth].dir	= dir;
							bruteforce(depth + 1, bitmask | (1<<i));
						}
	}
	public static void cal() {
		// 결과 연산을 위한 result 초기화
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
			{
				result[y][x].value = 0;
				result[y][x].ch = 'W';
			}
		// result에 값을 하나씩 넣는다.
		for(int i=0; i<3; i++)
			result = INPUT( result , finalData[i].idx, finalData[i].y, finalData[i].x, finalData[i].dir);
		
		// 폭탄의 품질 = 7R + 5B + 3G + 2Y
		int sum[] = new int['Z'];

		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				sum[result[y][x].ch] += result[y][x].value;
		
		max = Math.max(max, 7*sum['R'] + 5*sum['B'] + 3*sum['G'] + 2*sum['Y']);
	}	
	public static Node[][] INPUT(Node[][] nextResult, int idx, int Y, int X, int dir){
		for(int y=0; y<4; y++)
			for(int x=0; x<4; x++)
			{
				nextResult[y+Y][x+X].value = Math.min(9,Math.max(0,nextResult[y+Y][x+X].value + origin[dir][idx][y][x].value));
				if(origin[dir][idx][y][x].ch != 'W')
					nextResult[y+Y][x+X].ch = origin[dir][idx][y][x].ch;
			}
		
		return nextResult;
	}
}