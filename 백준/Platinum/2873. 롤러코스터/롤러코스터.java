//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2873
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	static final char ud[] = {'U','D'};
	static final char lr[] = {'L','R'};
	public static void input(StringBuilder sb, int X, int Y,char mm[], char dir) {
		int flag = 1;
		for(int x=0; x<X; x++)
		{
			for(int y=1; y<Y; y++)
				sb.append(mm[flag]);
			sb.append(dir);
			flag = (flag+1) % 2;
		}
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int Y = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		if(Y % 2 == 1 || X % 2 == 1)
		{
			if(Y % 2 == 0)
				input(sb, X, Y, ud, 'R');// 다운부터 시작 W모양
			else
				input(sb, Y, X, lr, 'D');// 오른쪽 부터 시작 ㄹ모양
		}else {
			int my = 0, mx = 0, mn = 1001;
			for(int y=0; y<Y; y++)
			{
				st = new StringTokenizer(br.readLine());
				for(int x=0; x<X; x++)
				{
					int n = Integer.parseInt(st.nextToken());
					if((y+x)%2 == 1 && mn > n)
					{
						mn = n;
						my = y;
						mx = x;
					}
				}
			}
			
			int sy = 0;
			int lrFlag = 1; // 왼쪽 오른쪽 플레그
			while(sy<Y)
			{
				if(sy == my || sy + 1 == my)
				{
					StringBuilder part = new StringBuilder();
					part.append('D');
					String RU = "RU";
					String RD = "RD";
					boolean flag = true;
					int repeat = X-2;
					while(repeat-->0)
					{
						if(flag) part.append(RU);
						else part.append(RD);
						flag = !flag;
					}
					if(mx == 0) {
						part.insert(0, 'R');
					}else if(mx == X-1) {
						part.append('R');
					}else {
						part.insert(mx*2, 'R');
					}
					
					
					sb.append(part.toString()).append('D');
					lrFlag = 0; // 다음엔 왼쪽 탐색토록함
				}
				else
				{
					for(int i=0; i<2; i++)
					{
						for(int x=1; x<X; x++)
						{
							sb.append(lr[lrFlag]);
						}
						sb.append('D');
						lrFlag = (lrFlag + 1) % 2;
					}
				}
				
				sy+=2;
			}
		}
		
		sb.deleteCharAt(sb.length()-1);
		System.out.print(sb.toString());
	}
}