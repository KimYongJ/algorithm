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
import java.util.List;
import java.util.StringTokenizer;

class Main{
	
	static int N;
	static int maxLen;
	static Node cnt[];
	static String result[];
	
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cnt = new Node[1001];
		result = new String[N];
		
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
		q.add("");// 초기 문자열 삽입
		
		for(int len = 1; len<=maxLen; len++) // 길이 1부터 가장긴 maxLen까지 반복
		{
			while(cnt[len].idx < cnt[len].order.size())// 특정 길이만큼 반복
			{
				if(q.isEmpty())// 큐가 비어있다면 maxLen까지 못만드므로 불가능
				{
					System.out.print(-1);
					return;
				}
			
				String s = q.pollFirst();// 초기 문자열을 꺼냄
				
				while(s.length() < len)// 해당 길이만큼 문자열을 만듦
				{
					q.addLast(s + "1");// 시작 문자열이 ""이고 len이 3이라면, 큐에 담기는 데이터는 순서대로 "1", "01", "001" 임 
					s = s + "0";// 다음 문자열은 "0"만 추가
				}
				
				int order = cnt[len].order.get(cnt[len].idx++);// 해당 길이의 최초 입력 순서를 꺼내옴
				result[order] = s;// 정답 문자열을 입력순서에 바로 넣음
			}
		}
		
		// 이하 문자열 출력
		StringBuilder sb = new StringBuilder();
		sb.append(1).append('\n');
		
		for(String res : result)
			sb.append(res).append('\n');
		
		System.out.print(sb);
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