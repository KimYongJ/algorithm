// https://github.com/kimyongj/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Node{
	int cost, idx;
	Node(int cost, int idx){this.cost=cost; this.idx=idx; }
}
class Main{
	static int read() throws Exception {// 빠른 입력을 위한 함수
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) n = (n << 3 ) + (n << 1) + (c & 15);
		return n;
	}
	public static void main(String[] args)throws Exception{
		ArrayList<Node> list = new ArrayList<>();
		int N		= read();
		int arr[]	= new int[N];

		for(int i=0; i<N; i++)
			arr[i] = read();// 해당 숫자당 가격
		
		int M = read(); // 총 시작 돈
		
		for(int i=0; i<N; i++) 
			list.add(new Node(arr[i], i));
		
		// 비용기준 오름차순, 숫자 기준 내림 차순;
		Collections.sort(list,(a,b)->a.cost != b.cost ? a.cost - b.cost : b.idx-a.idx);
		
		ArrayList<Integer> dummy = new ArrayList<>();
		Node now = list.get(0);
		while(M - now.cost >= 0)  // 먼저 가장 긴 숫자를 만든다.
		{
			M -= now.cost;
			dummy.add(now.idx);
		}
		
		//가장 앞이 0일 수 이으므로 0을 삭제하고 0이 아닐 때 까지 반복
		while(dummy.size()>0 && dummy.get(0) == 0) 
		{
			dummy.remove(0);
			M += arr[0]; // 남은돈
			int nextIdx = -1;
			int nextCost = 0;
			// remainCost로 살 수 있는 가장 큰 값을 찾는다.
			for(int i=1; i<N; i++) {
				if(arr[i] <= M) {
					nextCost = arr[i];
					nextIdx = i;
				}
			}
			if(nextIdx != -1) {
				// 해당 idx삭제 후 다른 idx로 교체
				dummy.add(0,nextIdx);
				M -= nextCost;
			}
		}
		// 가장 큰수로 변경 + 결과 삽입
		StringBuilder sb = new StringBuilder();
		int len = dummy.size();
		int idx = -1;
		while(++idx < len) 
		{
			int num = dummy.get(idx);
			int remainCost = arr[num] + M; // 해당 숫자를 사기 위한 가격 + 남은돈
			int nextIdx = -1;
			int nextCost = 0;
			// remainCost로 살 수 있는 가장 큰 값을 찾는다.
			for(int i=0; i<N; i++)
			{
				if(arr[i] <= remainCost) 
				{
					nextCost = arr[i];
					nextIdx = i;
				}
			}
			
			if(nextIdx != -1) 
			{
				// 해당 idx삭제 후 다른 idx로 교체
				num = nextIdx;
				M = remainCost - nextCost;
			}
			
			sb.append(num);
		}
		
		if(dummy.size() == 0) {
			sb.append(0);
		}
		
		System.out.print(sb.toString());
		
	}
}
