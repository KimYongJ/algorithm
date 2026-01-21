//https://www.acmicpc.net/problem/15465
//2초 512MB
//4 // 측정 정보
//7 Mildred +3 // 일자, 소이름, 생산량 변화
//4 Elsie -1
//9 Mildred -1
//1 Bessie +2
//답 : 3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());// 측정 횟수
		int milk[] = new int[] {7,7,7};
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		for(int i=0; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int name = getName(st.nextToken());
			int plus = Integer.parseInt(st.nextToken());
			pq.add(new Node(day, name, plus));
		}
		
		int bitMask = 7; // 가장 많이 우유를 생산한 소들을 비트마스크로 하나로 합침
		int res = 0;
		while(!pq.isEmpty())
		{
			int day = pq.peek().day;
			
			while(!pq.isEmpty() && pq.peek().day == day) // 같은 날일 경우 각 소의 우유 생산량을 세팅
			{
				Node now = pq.poll();
				milk[now.name] += now.plus;
				if(milk[now.name] < 0)
					milk[now.name] = 0;
			}
			
			int max = 0;
			for(int i=0; i<3; i++)
				max = Math.max(max, milk[i]);
			
			int newBitMask = 0;
			for(int i=0; i<3; i++)
				if(milk[i] == max)
					newBitMask |= 1<<i;
			
			if(newBitMask != bitMask)
			{
				++res;
				bitMask = newBitMask;
			}
		}
		System.out.print(res);
	}
	static int getName(String s) {
		if(s.charAt(0) == 'M') return 0;
		if(s.charAt(0) == 'E') return 1;
		return 2;
	}
	static class Node implements Comparable<Node>{
		int day, name, plus;
		Node(int d, int n, int p){
			day = d; name = n; plus = p;
		}
		@Override
		public int compareTo(Node o) {
			return day - o.day;
		}
	}
}