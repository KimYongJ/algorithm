//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/1963
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
class Node{
	int num, cnt; Node(int num, int cnt){this.num=num; this.cnt=cnt;}
}
class Main{
	
	static boolean[][][][] visit, isPrime;
	
	public static int[] getIdx(int num) {
		return new int[] {num/1000,(num/100)%10, (num/10)%10, num%10};
	}
	public static boolean validate(int a, int b, int c, int d) {
		return isPrime[a][b][c][d] &&!visit[a][b][c][d];
	}
	public static int getNum(int a, int b, int c, int d) {
		return a*1000 + b*100 + c*10 + d;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		isPrime = new boolean[10][10][10][10];
		for(int num=1001; num<=9999;num++) {
			boolean flag = true;
			for(int i=2; i*i<=num; i++) {
				if(num%i == 0) {
					flag = false;
					break;
				}
			}
			if(flag)
			{
				int idx[] = getIdx(num);
				isPrime[idx[0]][idx[1]][idx[2]][idx[3]] = true;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			boolean flag = false;
			int idx[] = getIdx(start);
			int cnt = 0;
			ArrayDeque<Node> q = new ArrayDeque<>();
			visit = new boolean[10][10][10][10];
			visit[idx[0]][idx[1]][idx[2]][idx[3]] = true;
			q.add(new Node(start, cnt));
			while(!q.isEmpty())
			{
				Node now = q.poll();
				if(now.num == end) {
					flag = true;
					sb.append(now.cnt);
					break;
				}
				idx = getIdx(now.num);
				cnt = now.cnt + 1;
				for(int n=1; n<10; n++)// 첫째 자리 변경
				{
					if(validate(n,idx[1],idx[2],idx[3]))
					{
						visit[n][idx[1]][idx[2]][idx[3]] = true;
						int nextNum = getNum(n, idx[1], idx[2], idx[3]);
						q.add(new Node(nextNum, cnt));
					}
				}
				for(int n=0; n<10; n++)// 첫째 자리 변경
				{
					if(validate(idx[0],n,idx[2],idx[3]))
					{
						visit[idx[0]][n][idx[2]][idx[3]] = true;
						int nextNum = getNum(idx[0], n, idx[2], idx[3]);
						q.add(new Node(nextNum, cnt));
					}
				}
				for(int n=0; n<10; n++)// 첫째 자리 변경
				{
					if(validate(idx[0],idx[1],n,idx[3]))
					{
						visit[idx[0]][idx[1]][n][idx[3]] = true;
						int nextNum = getNum(idx[0], idx[1], n, idx[3]);
						q.add(new Node(nextNum, cnt));
					}
				}
				for(int n=0; n<10; n++)// 첫째 자리 변경
				{
					if(validate(idx[0],idx[1],idx[2],n))
					{
						visit[idx[0]][idx[1]][idx[2]][n] = true;
						int nextNum = getNum(idx[0], idx[1], idx[2], n);
						q.add(new Node(nextNum, cnt));
					}
				}
			}
			
			if(!flag)
				sb.append("Impossible");
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}