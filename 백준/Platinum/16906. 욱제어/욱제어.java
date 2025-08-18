//https://www.acmicpc.net/problem/16906
//1초 512MB
//3 // 단어의 개수(1<=1,000)
//1 2 3// 단어의 길이, 단어길이 총합은 1,000이하 자연수
//단어 N개를 만드는 것이 가능하면 첫째 줄에 1출력 후 이 후부터 한 줄에 하나씩 단어를 출력 불가시 -1출력
//1
//0
//10
//110
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int maxLen;
	static Node cnt[];
	static List<Result> list;
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new Node[1001];
		list = new ArrayList<>();
		
		for(int i=0; i<=1000; i++)
			cnt[i] = new Node();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			int len = Integer.parseInt(st.nextToken());
			cnt[len].order.add(i);// len이 등장한 순서

			if(maxLen < len)
				maxLen = len;
		}
		
		ArrayDeque<String> q = new ArrayDeque<>();
		q.add("");
		
		for(int len = 1; len<=maxLen; len++)
		{
			while(cnt[len].idx < cnt[len].order.size())
			{
				if(q.isEmpty())
				{
					System.out.print(-1);
					return;
				}
			
				String s = q.pollFirst();
				
				while(s.length() < len)
				{
					q.addLast(s + "1");
					s = s + "0";
				}
				
				int order = cnt[len].order.get(cnt[len].idx++);
				list.add(new Result(s, order));
			}
		}
		
		
		Collections.sort(list, (a,b) -> a.order - b.order);
		
		StringBuilder sb = new StringBuilder();
		sb.append(1).append('\n');
		
		for(Result res : list)
			sb.append(res.str).append('\n');
		
		System.out.print(sb);
	}

	static class Result{
		String str;
		int order;
		Result(String s, int o){
			str = s;
			order = o;
		}
	}
	static class Node{
		List<Integer> order;
		int idx;
		Node(){
			order = new ArrayList<>();
			idx = 0;
		}
	}
}