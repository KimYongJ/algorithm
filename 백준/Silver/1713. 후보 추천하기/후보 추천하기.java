//https://www.acmicpc.net/problem/1713
//3 // 사진틀 개수(1<=20)
//9 // 전체 학생의 추천 횟수
//2 1 4 3 5 6 2 7 2 // 추천받은 학생 번호(1<=100)
//최종 게시 학생 출력(오름차순) : 2 6 7
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
class Main{
	
	static PriorityQueue<Node> pq, dummy;
	static int time;
	static int N, M;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		dummy = new PriorityQueue<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(M-->0)
		{
			int num = Integer.parseInt(st.nextToken());
			
			if(isContains(num))
				continue;
			
			if(pq.size() >= N)
				pq.poll();

			pq.add(new Node(++time, num, 0));
		}
		
		List<Integer> list = new ArrayList<>();
		
		while(!pq.isEmpty())
			list.add(pq.poll().num);
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		for(int num : list)
			sb.append(num).append(' ');
		
		System.out.print(sb);
	}
	static boolean isContains(int target) {
		boolean isContain = false;
		
		while(!pq.isEmpty())
		{
			Node now = pq.poll();
			
			if(now.num == target) {
				now.cnt++;
				isContain = true;
			}
			
			dummy.add(now);
		}
		
		PriorityQueue<Node> d = pq;
		pq = dummy;
		dummy = d;
		
		return isContain;
	}
	static class Node implements Comparable<Node>{
		int time, num, cnt;
		Node(int t, int n, int c){
			time = t;
			num = n;
			cnt = c;
		}
		@Override
		public int compareTo(Node o) {
			if(o.cnt != cnt)
				return cnt - o.cnt;
			return time - o.time;
		}
	}
}