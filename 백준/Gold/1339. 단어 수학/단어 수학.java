// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	char c;
	long score;
	Node(char c, int score){this.c=c; this.score=score;}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		String arr[] = new String[N];
		ArrayList<Node> list = new ArrayList<>();
		
		for(int i=0; i<26; i++)
			list.add(new Node((char)(i+'A'),0));
		
		for(int i=0; i<N; i++) {
			arr[i] = br.readLine();
			for(int j=arr[i].length()-1,z=9; j>=0; j--,z--) {
				char c = arr[i].charAt(j);
				Node now = list.get(c-'A');
				now.score += Math.pow(10, 9-z);
			}
		}
		// 점수기준 내림차순
		Collections.sort(list,(a,b)->Long.compare(b.score,a.score));
		int flag = 9;
		for(int i=0; i<26; i++) {
			Node now = list.get(i);
			if(now.score == 0)
				break;
			for(int n=0; n<N; n++) {
				arr[n] = arr[n].replaceAll(now.c+"",flag+"");
			}
			--flag;
		}
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += Integer.parseInt(arr[i]);
		}
		System.out.print(sum);
	}
}