// https://github.com/kimyongj/algorithm
import java.util.Arrays;
class Node{
	int idx,c, s; Node(int idx,int c, int s){this.idx=idx;this.c=c;this.s=s;}
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		int N		= read();
		int color[]	= new int[N+1];
		int result[]= new int[N];
		Node list[] = new Node[N];
		
		for(int i=0; i<N; i++)
			list[i] = new Node(i, read(), read());
		
		Arrays.sort(list,(a,b)->a.s-b.s); // 크기로 오름차순 정렬
		
		int sum = 0;
		int beforeSize = list[0].s;
		for(int i=0; i<N; i++) 
		{
			if(list[i].s != beforeSize) // 이전 사이즈랑 다르면 누적합(sum)과 color에 따른 사이즈를 갱신함.
			{
				int idx = i;
				while(--idx >=0 && list[idx].s == beforeSize)
				{
					sum += list[idx].s;
					color[list[idx].c]+= list[idx].s;
				}
				beforeSize = list[i].s;
			}
			result[list[i].idx] =  sum - color[list[i].c];
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++)
			sb.append(result[i]).append('\n');
		
		System.out.print(sb.toString());
	}
}
