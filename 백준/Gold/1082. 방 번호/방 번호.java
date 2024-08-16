// https://github.com/kimyongj/algorithm
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Node{
	int cost, idx;
	Node(int cost, int idx){
		this.cost=cost; this.idx=idx; 
	}
}
class Main{
	
	public static int changeNumber(ArrayList<Integer> dummy,int arr[], int M, int N) {
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
				dummy.set(idx,nextIdx);
				M = remainCost - nextCost;
			}
		}
		
		return M;
	}
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		ArrayList<Node> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());// 해당 숫자당 가격
		
		int M = Integer.parseInt(br.readLine()); // 총 시작 돈
		
		for(int i=0; i<N; i++) 
			list.add(new Node(arr[i], i));
		
		// 비용기준 오름차순, 숫자 기준 내림 차순;
		Collections.sort(list,(a,b)->a.cost != b.cost ? a.cost - b.cost : b.idx-a.idx);
		
		ArrayList<Integer> dummy = new ArrayList<>();
		Node now = list.get(0);
		while(M - now.cost >= 0)  // 먼저 가장 긴 숫자를 만든다.
		{
			M -=now.cost;
			dummy.add(now.idx);
		}
		
		M = changeNumber(dummy,arr,M, N);
		//가장 앞이 0일 수 이으므로 맨뒤 0을 삭제하고, 반복.
		int remainCost = M;
		while(dummy.size()>0 && dummy.get(0) == 0) 
		{
			dummy.remove(0);
			remainCost += arr[0]; // 남은돈
			int nextIdx = -1;
			int nextCost = 0;
			// remainCost로 살 수 있는 가장 큰 값을 찾는다.
			for(int i=1; i<N; i++) {
				if(arr[i] <= remainCost) {
					nextCost = arr[i];
					nextIdx = i;
				}
			}
			if(nextIdx != -1) {
				// 해당 idx삭제 후 다른 idx로 교체
				dummy.add(0,nextIdx);
				remainCost -= nextCost;
			}
		}
		
		changeNumber(dummy,arr,remainCost, N); // 다시 가장 큰 수 들로 변경
		
		if(dummy.size() == 0)
			dummy.add(0);
			
		StringBuilder sb = new StringBuilder();
		for(int d : dummy)sb.append(d);
		System.out.print(sb.toString());
		
	}
}
