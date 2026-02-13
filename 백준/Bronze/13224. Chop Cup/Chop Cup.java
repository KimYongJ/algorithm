//https://www.acmicpc.net/problem/13224
//2초 512MB
//인덱스 1에서 시작
//A => 1,2교환
//B => 2,3교환
//C => 1,3교환
//문제 : AB
//답 : 3(공의인덱스 출력)
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main{
	public static void main(String[] args)throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = 1;
		for(char c : br.readLine().toCharArray())
		{
			if(c == 'A' && idx != 3) idx ^= 3;
			if(c == 'B' && idx != 1) idx ^= 1;
			if(c == 'C' && idx != 2) idx ^= 2;
		}
		System.out.print(idx);
	}
}