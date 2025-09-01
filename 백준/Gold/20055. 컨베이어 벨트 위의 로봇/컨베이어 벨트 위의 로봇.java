//https://www.acmicpc.net/problem/20055
//1초 512MB
//4 5 // 칸 개수(2<=100), 0인 칸의 개수(1<=2N)
//10 1 10 6 3 4 8 2// 각 칸의 내구도(1<=1,000 / 칸의 개수 * 2만큼 주어짐 )
//답 : 24
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int zeroCnt;
	static int[] arr;
	static boolean[] isRobot;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 칸 개수(2<=100)
		zeroCnt = Integer.parseInt(st.nextToken());// 0인 칸의 개수(1<=2N)
		arr = new int[N<<1];
		isRobot = new boolean[N<<1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int step = 0;
		
		while(zeroCnt>0)
		{
			++step;
			
			rotate();
			moveRobots();
			loadRobot();
			
		}
		System.out.print(step);
	}
	static void rotate() {
		int last = arr[arr.length - 1];
		
		for(int i=arr.length - 1; i>0; i--)
		{
			arr[i] = arr[i - 1];
			isRobot[i] = isRobot[i - 1];
		}
		
		arr[0] = last;
		isRobot[0] = false;
		isRobot[N - 1] = false;
	}
	static void moveRobots() {
		for(int n=N-1; n>0; n--)
		{
			// 이동하려는 곳에 로봇이 없고, 내구도가 1이상이며, 현재 위치에 로봇이 있다면 로봇이동 
			if(!isRobot[n] && isRobot[n-1] && arr[n] > 0)
			{
				isRobot[n] = true;
				isRobot[n - 1] = false;
				arr[n]--;
				
				if(arr[n] == 0) --zeroCnt;
			}
		}
		
		isRobot[N - 1] = false; // N번째 위치의 로봇 내림
	}
	static void loadRobot() {
		if(arr[0] > 0)// 0번 인덱스에 내구도가 1이상이면
		{
			isRobot[0] = true;// 로봇을 올림
			arr[0]--;
			
			if(arr[0]==0) --zeroCnt;
		}
	}
}