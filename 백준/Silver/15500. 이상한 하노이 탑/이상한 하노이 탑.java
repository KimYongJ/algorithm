//https://www.acmicpc.net/problem/15500
//2초 512MB
//3 // 원판개수 (1<=123)
//2 3 1 // 첫 번째 장대에 있는 원판들의 반경을 나타내는 정수(1<=N), 제일 아래 위치 부터 주어짐
//답
//4 // 원판 옮긴 횟수(0<=12345)
//1 2
//1 3
//1 3
//2 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int target = Integer.parseInt(br.readLine());
		int pos[] = new int[target + 1];
		Stack s[] = new Stack[3];
		 
		for(int i=0; i<3; i++) s[i] = new Stack(target);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<target; i++)
		{
			int n = Integer.parseInt(st.nextToken());
			s[1].push(n);
			pos[n] = 1;
		}
		
		target++;
		
		int cnt = 0;
		
		while(--target>0)
		{
			int idx = pos[target];
			
			while(!s[idx].isEmpty())
			{
				++cnt;
				
				int now = s[idx].pop();
				int next = idx ^ 3; // 다음 기둥
				
				sb.append(idx).append(' ').append(now == target ? 3 : next).append('\n');// 목표 원판을 찾으면 3, 아니면 다음 기둥 출력
				
				if(now == target)// 목표 원판을 찾은 경우 반복문 탈출
					break;
				
				s[next].push(now);// 해당 원판을 다음 기둥으로 이동
				pos[now] = next;// 이동한 내용을 표시
			}
			
		}
		System.out.println(cnt);
		System.out.print(sb);
	}
	static class Stack{
		int arr[];
		int idx;
		Stack(int N){
			arr = new int[N + 1];
			idx = -1;
		}
		boolean isEmpty() {return idx < 0;}
		void push(int n) {arr[++idx] = n;}
		int pop() {return arr[idx--]; }
	}
}