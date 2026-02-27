//https://www.acmicpc.net/problem/25705
//1초 1024MB
//4 // 돌림판에 적힌 알파벳 소문자 수 1<=100
//abcd // 돌림판에 적힌 알파벳 소문자들(돌림판은 처음에마지막으로 입력된 문자를 가리키고있음)
//6 // 만들어야하는 문자열 길이 1<=100
//aacdbc // 목표 문자열
//회전 최소 횟수(불가능하면-1) : 11
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int idx = N-1;
		
		char arr[] = br.readLine().toCharArray();
		
		br.readLine(); // 버림
		
		Loop :
		for(char c : br.readLine().toCharArray())
		{
			++cnt;
			idx = (idx + 1) % N;
			
			int T = N;
			
			while(T-->0)
			{
				if(arr[idx] == c)
					continue Loop;
				
				++cnt;
				idx = (idx + 1) % N;
			}
			
			cnt = -1;
			break;
		}
		
		System.out.print(cnt);
	}
}