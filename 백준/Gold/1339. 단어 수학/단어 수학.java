// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
class Node{
	char c;
	long score;
	Node(char c, int score){this.c=c; this.score=score;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb[] = new StringBuilder[N];
		ArrayList<Node> list = new ArrayList<>();
		
		for(int i=0; i<26; i++)
			list.add(new Node((char)(i+'A'),0));
		
		for(int i=0; i<N; i++) 
		{
			sb[i] = new StringBuilder(br.readLine());
			for(int j=sb[i].length()-1,z=9; j>=0; j--,z--) 
			{
				Node now = list.get(sb[i].charAt(j) - 'A');
				now.score += Math.pow(10, 9-z);
			}
		}
		// 점수기준 내림차순
		Collections.sort(list,(a,b)->Long.compare(b.score,a.score));
		char flag = '9';
		for(int i=0; i<26; i++) {
			Node now = list.get(i);
			if(now.score == 0)
				break;
			for(int n=0; n<N; n++) {
				for(int l=0; l<sb[n].length(); l++) {
					if(sb[n].charAt(l) == now.c) {
						sb[n].setCharAt(l, flag);
					}
				}
			}
			--flag;
		}
		
		long sum = 0;
		for(int i=0; i<N; i++)
			sum += Integer.parseInt(sb[i].toString());
		
		System.out.print(sum);
	}
}