//https://www.acmicpc.net/problem/17202
//2초 256MB
//100 // 초기 주어지는 현금
//10 20 23 34 55 30 22 19 12 45 23 44 34 38 // 0<=1,000
//1월 14일 기준 준현이의 자산이 더 크다면 BNP를, 성민이의 자산이 더 크다면 TIMING을 출력,같다면 SAMESAME을 출력
//답 : BNP
//준현이는 1월 1일에 10주를 매수한다. 따라서 1월 14일 380원의 자산을 가지게 된다.
//성민이는 1월 8일에 5주를 매수한다. 따라서 1월 14일 195원의 자산을 가지게 된다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int base = Integer.parseInt(br.readLine());
		
		Node a = new Node(base);
		Node b = new Node(base);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<14; i++)
		{
			int price = Integer.parseInt(st.nextToken());
			
			a.junhyun(price);
			b.sungmin(price);
			
			if(i == 13)
			{
				a.trans(price);
				b.trans(price);
			}
		}
		
		String res = "SAMESAME";
		
		if(a.cash > b.cash)
			res = "BNP";
		else if(a.cash < b.cash)
			res = "TIMING";
		
		System.out.print(res);
	}
	static class Node{
		int cash, stock, prev, inc, dec;
		Node(int c){
			cash = c;
			stock = 0;
			prev = -1;
			inc = 0;
			dec = 0;
		}
		public void trans(int price) {
			this.cash += this.stock * price;
		}
		public void junhyun(int price) {
			if(price == 0)
				return;
			
			if(cash / price > 0)
			{
				stock += cash / price;
				cash %= price;
			}
		}
		public void sungmin(int price) {
			if(prev == -1)
			{
				prev = price;
				return;
			}
			
			if(prev < price) {
				inc++;
				dec = 0;
			}
			else if(prev > price) {
				dec++;
				inc = 0;
			}
			else {
				dec = 0;
				inc = 0;
			}
			prev = price;
			
			if(inc >= 3) {
				cash += stock * price;
				stock = 0;
			}
			if(dec >= 3 && price > 0) {
				stock += cash / price;
				cash %= price;
			}
		}
	}
}