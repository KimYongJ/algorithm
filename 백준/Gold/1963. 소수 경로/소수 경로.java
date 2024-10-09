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
	
	public static int[] getIdx(int num) {
		return new int[] {num/1000,(num/100)%10, (num/10)%10, num%10};
	}
	public static int getNum(int a, int b, int c, int d) {
		return a*1000 + b*100 + c*10 + d;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean[] isPrime = new boolean[10000];
		for(int num=1001; num<=9999;num++) {
			boolean flag = true;
			for(int i=2; i*i<=num; i++) {
				if(num%i == 0) {
					flag = false;
					break;
				}
			}
			if(flag)
				isPrime[num] = true;
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
			boolean[] visit = new boolean[10000];
			visit[start] = true;
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
				for(int i=0; i<4; i++)
				{
					idx = getIdx(now.num);
					for(int n=i==0?1:0; n<10; n++)
					{
						idx[i] = n;
						int nextNum = getNum(idx[0], idx[1], idx[2], idx[3]);
						if(isPrime[nextNum] &&!visit[nextNum])
						{
							visit[nextNum] = true;
							q.add(new Node(nextNum, cnt));
						}
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