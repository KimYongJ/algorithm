//https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
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
		int N					= Integer.parseInt(br.readLine());		
		String str[]			= new String[N];
		boolean nonZeroList[]	= new boolean[10];	// 0일 수 없는 것
		long valueOfChar[]		= new long[10];		// 각 알파벳 마다 가중치
		for(int i=0; i<N; i++)
		{
			str[i] = br.readLine();
			nonZeroList[str[i].charAt(0)-'A'] = true;
			
			long mul	= 1;
			int len		= str[i].length();
			for(int j=len-1; j>=0; j--, mul*=10)
				valueOfChar[str[i].charAt(j)-'A'] += mul;
		}

		PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b)->Long.compare(b.value,a.value));
		for(int i=0; i<10; i++)
			if(valueOfChar[i] > 0)
				pq.add(new Node(i,valueOfChar[i]));
			
		int rank[] = new int[10];
		int f = 9;
		while(!pq.isEmpty())
			rank[pq.poll().ch] = f--;
		
		for(int i=0; i<10; i++)
			if(rank[i] == 0 && nonZeroList[i]) // 0이면 안 되지만 0인것을 발견했을 때 
			{
				int psZeroIdx = -1; // 0이 가능하면서도, rank가 가장 작아야 한다.
				int dummyRank = 11; // 0이 가능하면서도, rank가 가장 작아야 한다.
				for(int j=0; j<10; j++)
					if(!nonZeroList[j] && dummyRank > rank[j]) // 0이 가능하면서도, rank가 가장 작은 것을 저장해 나간다.
					{
						psZeroIdx = j;
						dummyRank = rank[j];

					}
				
				for(int j=0; j<10; j++) 
					if(rank[psZeroIdx] > rank[j])  	// 목표 랭크(0이 가능하면서도 rank가 가장 작았던 것)보다 작은 것들은 모두 +1을 해버린다.
						rank[j]++;

				rank[psZeroIdx] = 0;				// 목표 랭크는 0으로 치환
				break;
			}
		
		
		long res = 0;
		for(int i=0; i<10; i++)
			res += valueOfChar[i] * rank[i];
		
		System.out.print(res);
	}
}