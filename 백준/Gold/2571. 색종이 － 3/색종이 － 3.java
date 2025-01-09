//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2571
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N			= Integer.parseInt(br.readLine());// 색종이 수(0<=100)
		int max			= 0;
		boolean map[][]	= new boolean[100][100];
		
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());// 도화지와 색종이의 왼쪽변 사이거리 
			int Y = 99 - Integer.parseInt(st.nextToken()) - 9;// 도화지와 색종이의 아래쪽 변 사이의 거리
			
			for(int y=Y; y<Y+10; y++)
				for(int x=X; x<X+10; x++)
					map[y][x] = true;
		}
		
		int height[] = new int[100];
		
		for(int y=0; y<100; y++)
		{
			for(int x=0; x<100; x++)
			{
				if(map[y][x])
					height[x]++;
				else
					height[x] = 0;
			}
			
			max = Math.max(max, getArea(height));
		}
		System.out.print(max);
	}
	public static int getArea(int height[]) {
		int max = 0;
		Stack stack = new Stack();
		
		for(int i=0; i<=100; i++)
		{
			int h = i==100 ? 0 : height[i];
			while(!stack.isEmpty() && h < height[stack.peek()])
			{
				int H = height[stack.pop()];
				int W = i;
				if(!stack.isEmpty())
					W = i - stack.peek() - 1;

				max = Math.max(H*W, max);
			}
			stack.push(i);
		}
		return max;
	}
}
class Stack{
	Data data;
	int dataCnt;
	Stack(){
		data = null;
		dataCnt = 0;
	}
	void push(int num) {
		data = new Data(num, data);
		dataCnt++;
	}
	int pop() {
		int num = data.num;
		data = data.next;
		dataCnt--;
		return num;
	}
	int peek() {return data.num;}
	boolean isEmpty() {return dataCnt == 0;}
}
class Data{int num;Data next;Data(int n, Data nt){num = n; next = nt;}}
