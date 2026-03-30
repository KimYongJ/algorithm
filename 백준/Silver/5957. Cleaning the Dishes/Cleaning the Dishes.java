//https://www.acmicpc.net/problem/5957
//1초 128MB
//5 // 총 접시 개수(1<=10,000)
//1 3 // 명령 (1은 씼는 것, 2는 말리는것), 씻거나 말린 접시 개수 (1<=N)
//2 2
//1 2
//2 3
//최종 접시가 쌓인 순서
//1
//4
//5
//2
//3
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	
	static final int len = 10_001;
	static int N;
	static Stack origin, clean, dry;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N		= Integer.parseInt(br.readLine());
		origin	= new Stack(len);
		clean	= new Stack(len);
		dry		= new Stack(len);
		
		for(int i=N; i>=1; i--) origin.push(i);


		while(true)
		{
			String str = br.readLine();
			
			if(str == null || "".equals(str))
				break;
			
			StringTokenizer st = new StringTokenizer(str);
			int cmd = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			if(cmd == 1)
				while(cnt-->0)clean.push(origin.pop());
			else
				while(cnt-->0)dry.push(clean.pop());
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!dry.isEmpty()) sb.append(dry.pop()).append('\n');
		
		System.out.print(sb);
	}
	static class Stack{
		int arr[];
		int idx;
		Stack(int n){
			arr = new int[n];
			idx = -1;
		}
		boolean isEmpty() {
			return idx < 0;
		}
		int pop() {
			return arr[idx--];
		}
		int push(int num) {
			return arr[++idx] = num;
		}
	}
}