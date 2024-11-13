//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/2304
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br		= new BufferedReader(new InputStreamReader(System.in));
		ArrayList<int[]> list	= new ArrayList<>();
		
		int N = Integer.parseInt(br.readLine());
		while(N-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());	// 가로 인덱스
			int h = Integer.parseInt(st.nextToken());	// 높이
			list.add(new int[] {i,h});
		}
		
		Collections.sort(list,(a,b)->a[0]-b[0]);
		
		Stack<int[]> stack = new Stack<>();
		int res = 0;
		int h	= list.get(0)[1];
		int idx	= list.get(0)[0];
		for(int[] now : list)
		{
			if(h < now[1])	// 기준 높이보다 현재 나온것이 크다면
			{
				res		+= (now[0] - idx) * h;	// 기존 스택의 모든 것들을 계산하고 스택을 초기화
				h		= now[1];			// 기준 높이 갱신
				idx		= now[0];
				stack	= new Stack<>();
			}
			stack.add(now);
		}
		int lastIdx = 0;
		if(!stack.isEmpty())
		{
			h	= stack.peek()[1];
			idx = stack.peek()[0];
			while(!stack.isEmpty())
			{
				int now[] = stack.pop();
				if(h < now[1])
				{
					res += h * (idx - now[0]);
					idx = now[0];
					h	= now[1];
				}
				lastIdx = now[0] - 1;
			}
		}
		System.out.print(res + h * (idx - lastIdx));
	}
}