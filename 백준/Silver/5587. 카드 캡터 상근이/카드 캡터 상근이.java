//https://www.acmicpc.net/problem/5587
//1초 128MB
//5 // 카드 수(1<=100)
//1 // 카드 수만큼 상근이에게 주어지는 카드
//7
//9
//6
//10
//답 :
//3 // 먼저 시작한 사람 점수 출력
//0 // 나중에 시작한 사람 점수 출력
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TreeSet<Integer> p1 = new TreeSet<>();
		TreeSet<Integer> p2 = new TreeSet<>();
		int N = Integer.parseInt(br.readLine());
		boolean first[] = new boolean[(N << 1) + 1];
		
		for(int i=0; i<N; i++)
			first[Integer.parseInt(br.readLine())] = true;
		
		for(int i=1; i<first.length; i++)
			if(first[i]) p1.add(i);
			else p2.add(i);
		
		boolean flag = true;
		int prev = -1;
		while(p1.size() > 0 && p2.size() > 0)
		{
			if(flag)
				prev = getNum(prev, p1);
			else
				prev = getNum(prev, p2);
			
			flag = !flag;
		}
		
		System.out.printf("%d\n%d", p2.size(), p1.size());
	}
	public static int getNum(int target, TreeSet<Integer> set)
	{
		Integer next = set.higher(target);
		
		if(next == null) return -1;
		
		set.remove(next);
		
		return next;
	}
}