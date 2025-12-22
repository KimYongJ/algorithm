//https://www.acmicpc.net/problem/20923
//1초 1024MB
//10 12 // 카드 개수(1<=30,000), 게임 진행 횟수(1<=2,500,000)
//1 2 // 카드 개수만큼 도도, 수연이 순으로 덱의 맨 아래에 위치한 카드에 적혀있는 수부터 보여줌(1<=5)
//2 2
//1 2
//2 3
//3 1
//2 2
//2 5
//2 1
//5 1
//2 3
//게임 M번 진행 후 승리한 사람 : do
//시작은 도도가 먼저, 도도가 이기면 do, 수연이가 이기면 su, 비기면 dosu출력
//게임 도중 무조건 덱에 카드가 0이면 패배
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 카드 개수(1<=30,000)
		int M = Integer.parseInt(st.nextToken()); // 게임 진행 횟수(1<=2,500,000)
		
		ArrayDeque<Integer> deck[] = new ArrayDeque[2];
		ArrayDeque<Integer> ground[] = new ArrayDeque[2];
		
		for(int i=0; i<2; i++)
		{
			deck[i] = new ArrayDeque<>();
			ground[i] = new ArrayDeque<>();
		}
		
		for(int i=0; i<N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<2; j++)
				deck[j].addFirst(Integer.parseInt(st.nextToken()));
		}
		
		int player = 0; // 시작하는 사람이 0
		
		while(M-->0)
		{
			int nextPlayer = (player + 1) % 2;
			
			ground[player].addFirst(deck[player].pollFirst());
			
			if(deck[player].size() == 0) // 덱의 카드가 0이되는 순간 종료
			{
				System.out.print(player == 1 ? "do" : "su");
				return;
			}
			
			int p1 = ground[player].peekFirst();
			int p2 = ground[nextPlayer].isEmpty() ? -5 : ground[nextPlayer].peekFirst();
			
			boolean SU = !ground[player].isEmpty() && !ground[nextPlayer].isEmpty() && p1 + p2 == 5;
			boolean DO = (!ground[player].isEmpty() && p1 == 5) || (!ground[nextPlayer].isEmpty() && p2 == 5);

			if(SU)
				push(deck[1], ground[0], ground[1]);
			else if(DO)
				push(deck[0], ground[1], ground[0]);
			
			player = nextPlayer;
		}
		String result = "dosu";
		
		if(deck[0].isEmpty() || deck[1].isEmpty())
			result = deck[0].isEmpty() ? "su" : "do";
		else if(deck[0].size() != deck[1].size())
			result = deck[0].size() < deck[1].size() ? "su" : "do";
	
		System.out.print(result);
	}
	static void push(ArrayDeque<Integer> deck, ArrayDeque<Integer> g1, ArrayDeque<Integer> g2) {
		while(!g1.isEmpty()) deck.addLast(g1.pollLast());
		while(!g2.isEmpty()) deck.addLast(g2.pollLast());
	}
}