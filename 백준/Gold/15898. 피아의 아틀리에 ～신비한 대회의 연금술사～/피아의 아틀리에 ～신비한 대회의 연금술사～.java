//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15898
// 맵크기 5*5 / 재료는 최대 3개넣음 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{	int value;char ch;Node(int v, char c){value=v; ch=c;} }

class Main{
	
	static Node origin[][][];
	static int N, max;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N			= Integer.parseInt(br.readLine());	// 재료 개수(3 ≤ n ≤ 10)
		origin		= new Node[N][4][4];

		for(int i=0; i<N; i++)
		{
			// 초기 효능 값(숫자)
			for(int y=0; y<4; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=0; x<4; x++)
					origin[i][y][x] = new Node(Integer.parseInt(st.nextToken()),'W');
			}
			// 초기 원소 값(알파벳)
			for(int y=0; y<4; y++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=0; x<4; x++)
					origin[i][y][x].ch = st.nextToken().charAt(0);
			}
		}
		
		Node[][]param= new Node[5][5];
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				param[y][x] = new Node(0,'W');
		
		bruteforce(0, 0, param);		
		
		System.out.print(max);
	}
	public static void bruteforce(int depth, int bitmask, Node[][] result) {
		if(depth == 3)
		{
			cal(result);
			return;
		}
		for(int i=0; i<N; i++)
		{
			int flag = 1<<i;
			if((bitmask & flag) == 0)
			{
				for(int y=0; y<=1; y++)
					for(int x=0; x<=1; x++)
						for(int dir=0; dir<4; dir++)//현재(0),90도(1),180도(2),270(3)
						{
							Node[][] nextResult = copy(result);
							nextResult = INPUT( nextResult , i, y, x, dir); // 순서 : 최종결과배열, 넣을 재료 위치, 최종결과에넣을 시작y좌표, 최종결과에넣을 시작x좌표, 방향
							bruteforce(depth + 1, bitmask | flag, nextResult);
						}
			}
		}
	}
	public static Node[][] INPUT(Node[][] nextResult, int idx, int Y, int X, int dir){
		for(int y=0; y<4; y++)
			for(int x=0; x<4; x++)
			{
				switch(dir)
				{
				case 0:	// 그대로일 때
					nextResult[y+Y][x+X].value += origin[idx][y][x].value;
					if(origin[idx][y][x].ch != 'W')
						nextResult[y+Y][x+X].ch = origin[idx][y][x].ch;
					break;
				case 1: // 90도 회전일 때
					nextResult[y+Y][x+X].value += origin[idx][3-x][y].value;
					if(origin[idx][3-x][y].ch != 'W')
						nextResult[y+Y][x+X].ch = origin[idx][3-x][y].ch;
					break;
				case 2: // 180도 회전일 때
					nextResult[y+Y][x+X].value += origin[idx][3-y][3-x].value;
					if(origin[idx][3-y][3-x].ch != 'W')
						nextResult[y+Y][x+X].ch = origin[idx][3-y][3-x].ch;
					break;
				case 3:	// 270도 회전일 때
					nextResult[y+Y][x+X].value += origin[idx][x][3-y].value;
					if(origin[idx][x][3-y].ch != 'W')
						nextResult[y+Y][x+X].ch = origin[idx][x][3-y].ch;
					break;
				}
				if(nextResult[y+Y][x+X].value < 0)
					nextResult[y+Y][x+X].value = 0;
				else if(9 < nextResult[y+Y][x+X].value)
					nextResult[y+Y][x+X].value = 9;
			}
		
		return nextResult;
	}
	public static Node[][] copy(Node[][] origin){
		Node[][] result = new Node[5][5];
		
		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				result[y][x] = new Node(origin[y][x].value, origin[y][x].ch);
		
		return result;
	}
	public static void cal(Node[][] result) {
		// 폭탄의 품질 = 7R + 5B + 3G + 2Y
		int sum[] = new int['Z'];

		for(int y=0; y<5; y++)
			for(int x=0; x<5; x++)
				sum[result[y][x].ch] += result[y][x].value;
		
		max = Math.max(max, 7*sum['R'] + 5*sum['B'] + 3*sum['G'] + 2*sum['Y']);
	}
}