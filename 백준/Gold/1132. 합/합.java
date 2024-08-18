//https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
class Node{
	int ch;
	long value;
	Node(int ch, long value){
		this.ch = ch;
		this.value = value;
	}
}
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long valueOfChar[] = new long[10];
		String str[] = new String[N];
		boolean nonZeroList[] = new boolean[10]; // 0일 수 없는 것
		for(int i=0; i<N; i++)
		{
			str[i] = br.readLine();
			nonZeroList[str[i].charAt(0)-'A'] = true;
		}
		
		for(int i=0; i<N; i++) 
		{
			long mul = 1;
			int len = str[i].length();
			for(int j=len-1; j>=0; j--,mul*=10) {
				valueOfChar[str[i].charAt(j)-'A'] += mul;
			}
		}
		
		ArrayList<Node> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			if(valueOfChar[i] > 0)
				list.add(new Node(i,valueOfChar[i]));
		}
			
		Collections.sort(list,(a,b)->Long.compare(b.value,a.value));
		
		int rank[] = new int[10];
		
		for(int i=0,j=9; i<list.size(); i++,j--)
			rank[list.get(i).ch] = j;

		int psZeroIdx = -1; // 0이 가능하면서도, rank가 가장 작아야 한다.
		int dummyRank = 11;
		for(int i=0; i<10; i++) {
			if(!nonZeroList[i] && dummyRank > rank[i]) {
				psZeroIdx = i;
				dummyRank = rank[i];

			}
		}
		
		for(int i=0; i<10; i++) {
			if(rank[i] == 0 && nonZeroList[i]) {
				for(int j=0; j<10; j++) {
					if(rank[psZeroIdx] > rank[j]) {
						rank[j]++;
					}
				}
				rank[psZeroIdx] = 0;
				break;
			}
		}
		
		long res = 0;
		for(int i=0; i<N; i++) {
			StringBuilder sb = new StringBuilder();
			for(char c : str[i].toCharArray()) {
				sb.append(rank[c-'A']);
			}
			res += Long.parseLong(sb.toString());
		}
		
		System.out.print(res);
	}
}