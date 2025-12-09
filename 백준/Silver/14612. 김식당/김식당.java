//https://www.acmicpc.net/problem/14612
//2초 256MB
//7 3 // 질의의 수(1<=100), 테이블 수 (1<=100)
//order 1 4 // 테이블 번호와 주문 시간, 주문이 들어오면 갖고 있는 포스트잇의 맨 뒤에 추가
//order 2 2
//order 3 3
//sort // 주문 시간이 빠른 순서대로 정렬, 시간이 같으면 테이블 번호가 작은 순으로 오름 차순
//complete 3 // 완성된 테이블 번호, 완성 시점에 테이블에 두 개 이상의 주문이 밀려있거나 없는 경우는 없음
//complete 2
//complete 1
//답(각 명령어 마다 포스트잇들에 적힌 테이블 번호 출력, 아무것도 없으면 sleep 출력)
//1
//1 2
//1 2 3
//2 3 1
//2 1
//1
//sleep

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Node> list = new ArrayList<>();
		while(N-->0)
		{
			st = new StringTokenizer(br.readLine());
			char o = st.nextToken().charAt(0);
			
			if(o == 'o')
				list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			else if(o == 's')
				Collections.sort(list);
			else
			{
				int c = Integer.parseInt(st.nextToken());
				for(int i=0; i<list.size(); i++)
				{
					if(list.get(i).idx == c)
					{
						list.remove(i);
						break;
					}
				}
			}
			
			if(list.size() == 0)
			{
				sb.append("sleep").append('\n');
				continue;
			}
			
			for(Node n : list)
				sb.append(n.idx).append(' ');
			sb.append('\n');
		}
		System.out.print(sb);
	}
	static class Node implements Comparable<Node>{
		int idx, time;
		Node(int i, int t){
			idx = i;
			time = t;
		}
		@Override
		public int compareTo(Node o) {
			if(o.time == time)
				return idx - o.idx;
			return time - o.time;
		}
	}
}