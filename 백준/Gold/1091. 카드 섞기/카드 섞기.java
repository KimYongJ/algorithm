//https://www.acmicpc.net/problem/1091
//2초 128MB
//12 // 카드 개수(3<=48 && 3의배수)
//1 1 2 0 2 0 1 0 2 2 1 0 // 카드개수 만큼 수열 P (0,1,2중하나) , 맨앞이 1이면 0번째카드는 최종 1번에게 가야함을 의미
//5 0 9 7 1 8 3 10 4 11 6 2 // 카드개수만큼 수열 S( 중복되지 않으며 0<=N-1), 맨앞이 5면, 0번째 카드는 한번섞으면 5번위치로간다는 것
//답 : 59 // 섞어도 카드를 해당 플레이어에게 줄 수 없으면 -1 출력


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int P[] = new int[N];
		int S[] = new int[N];
		int card[] = new int[N];
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			card[i] = i;
			P[i] = Integer.parseInt(st1.nextToken());
			S[i] = Integer.parseInt(st2.nextToken());
		}
		
		int res = 0;
		boolean imp = true;
		boolean first = true;
		while(true)
		{
			if(validate(card, P))
			{
				imp = false;
				break;
			}
			
			if(!first && check(card))
				break;
			
			card = shuffle(card, S);
						
			++res;
			first = false;
		}
		
		System.out.print(imp ? -1 : res);
	}
	static boolean validate(int card[], int base[]) {
		for(int i=0; i<card.length; i++)
			 if(base[card[i]] != i % 3)
				 return false;
		return true;
	}
	static boolean check(int card[]) {
		for(int i=0; i<card.length; i++)
			if(card[i] != i)
				return false;
		return true;
	}
	static int[] shuffle(int card[], int order[]) {
		int arr[] = new int[card.length];
		for(int i=0; i<card.length; i++)
			arr[order[i]] = card[i];
		return arr;
	}
}