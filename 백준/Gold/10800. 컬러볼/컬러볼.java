// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Node{
	int idx,c, s; Node(int idx,int c, int s){this.idx=idx;this.c=c;this.s=s;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader	br	= new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());
		int maxSize = 0;
		int color[]	= new int[N+1];
		int result[]= new int[N];
		Node list[] = new Node[N];
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			list[i] = new Node(i, col, size);
			maxSize = Math.max(maxSize, size);
		}
		int sum[] = new int[maxSize+1]; // 누적합
		
		Arrays.sort(list,(a,b)->a.s-b.s); // 크기로 오름차순 정렬
		
		int j = 0;
		for(int i=1; i<=maxSize; i++) 
		{
			int s;
			for(s=j;s<N; s++)
			{
				if(list[s].s > i) 
					break;
				result[list[s].idx] =  sum[i-1] - color[list[s].c];
				sum[i] += list[s].s;
			}
			for(s=j;s<N; s++)
			{
				if(list[s].s > i) 
					break;
				color[list[s].c]+= list[s].s;
			}
			j = s;
			sum[i] += sum[i-1];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++)
			sb.append(result[i]).append('\n');
		System.out.print(sb.toString());
	}
}
