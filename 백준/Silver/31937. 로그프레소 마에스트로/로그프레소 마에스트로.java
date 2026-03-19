//https://www.acmicpc.net/problem/31937
//1초 1024MB
//5 4 3// 컴퓨터 개수(2<=10^3), 파일 전송 수(1<=10^4), 감염된 컴퓨터의 개수(1<=N)
//3 4 5// 감염된 컴퓨터의 번호를 나타내는 k개의 정수가 공백으로 구번되어 출력됨(1<=N)
//4 4 5// 셋째줄부터 파일 전송 수 만큼 어디서 어디로 보냈는지 나옴
//3 3 4// t a b / t시각에 a에서 b로 파일은 전송했다는 의미
//2 2 3// 1<=t<=10^9 / t는 유일하다.
//1 1 2
//답 : 3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
 
class Main{
	
	static int N, M, K;
	static boolean[]x, x1;
	static List<Node> order;
	static List<Integer> list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());// 컴퓨터 개수(2<=10^3)
		M = Integer.parseInt(st.nextToken());// 파일 전송 수(1<=10^4)
		K = Integer.parseInt(st.nextToken());// 감염된 컴퓨터의 개수(1<=N)
		x = new boolean[N + 1]; // 감염 유무
		list = new ArrayList<>();
		order = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++)
		{
			int n = Integer.parseInt(st.nextToken()); 
			x[n] = true;
			list.add(n);
		}
		
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			order.add(new Node(t,a,b));
		}
		
		Collections.sort(order);
		
		x1 = new boolean[N + 1];
		
		for(int i : list)
		{
			Arrays.fill(x1, false);
			x1[i] = true;
			if(cal())
			{
				System.out.print(i);
				return;
			}
		}
	}
	static boolean cal() {
		
		for(Node now : order)
		{
			if(x1[now.a])
			{
				if(!x[now.a] || !x[now.b])
					return false;
				
				x1[now.b] = true;
			}
		}
		
		for(int j=1; j<=N; j++)
			if(x1[j] != x[j])
				return false;

		return true;
	}
	static class Node implements Comparable<Node>{
		int t, a, b;
		Node(int t, int a, int b){
			this.t=t;
			this.a=a;
			this.b=b;
		}
		@Override
		public int compareTo(Node o) {
			return t - o.t;
		}
	}
}