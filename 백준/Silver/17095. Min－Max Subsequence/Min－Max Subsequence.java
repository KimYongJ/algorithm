//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/17095
// 0.5초 / 256MB
// 요약 : 수열의 부분 수열중 최댓값과 최솟값의 차이가 최대가되는 부분 수열 중 길이가 가장 짧은 것의 길이를 구하라
// 해설 : 두 배열에서의 차이가 가장 짧은 것을 찾는 문제의 응용(백준 : 27931)
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N		= Integer.parseInt(br.readLine());	// 1<=십만
		int arr[]	= new int[N];	// 원소 : 1<=십만
		int min		= 1<<30;
		int max		= 0;
		// 값을 입력할 때 배열의 최솟값과 최댓값을 찾아놓는다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		
		ArrayList<Integer> minIdx = new ArrayList<>();
		ArrayList<Integer> maxIdx = new ArrayList<>();
		// 최소값과 최대값의 인덱스를 각 리스트에 넣는다.
		for(int i=0; i<N; i++)
		{
			if(arr[i] == min)minIdx.add(i);
			if(arr[i] == max)maxIdx.add(i);
		}
		
		int minSize = minIdx.size();
		int maxSize = maxIdx.size();
		int idx1	= 0;
		int idx2	= 0;
		int res		= 1<<30;
		
		while(idx1<minSize && idx2<maxSize)
		{
			int minValue	= minIdx.get(idx1);
			int maxValue	= maxIdx.get(idx2);
			int diff		= Math.abs(maxValue - minValue) + 1;
			res				= Math.min(res, diff);
			
			if(minValue < maxValue)	// 배열 인덱스 값이 작은것의 포인터를 증가시킨다.
				++idx1;
			else
				++idx2;
		}

		System.out.print(res);
	}
}
