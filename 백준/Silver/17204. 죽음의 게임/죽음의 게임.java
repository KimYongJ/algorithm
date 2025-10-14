//https://www.acmicpc.net/problem/17204
//2초 256MB
//5 3 // 게임 참여 수(3<=150), 보성이 번호(1<=N-1)
//1 // 각사람이 지목한 사람의 번호(0<=N-1)
//3
//2
//1
//4
//답 : 2
//(보성이가 걸리지않으면 -1출력)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int arr[] = new int[N];
		
		for(int i=0; i<N; i++) arr[i] = Integer.parseInt(br.readLine());
		
		int now = 0;
		int res = 0;
		
		for(int i=0; i<N; i++)
		{
			now = arr[now];
			res++;
			if(G == now) {
				System.out.print(res);
				return;
			}
		}
		System.out.print(-1);
	}
}