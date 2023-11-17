// https://github.com/KimYongJ/algorithm
// [해설]
// 최종 만들어야 하는 숫자 goal과 가장 가까운 숫자 c를 찾는다.
// DFS로 숫자 c를 만들며, 만들 때 자릿수 하나씩을 만들어간다.
// 최종 만들어진 숫자 c를 set에 담는다.(이 때 100은 default로 들어가있다.)
// 숫자 c의 길이 + c와 goal의 차이의 절댓값을 구한다(이 때 100은 길이가 0이다.)
// DFS의 인자 : 깊이, 만들어진 자릿수들의정보(string) , 가능한숫자배열(arr), 최대 깊이
// DFS 주의점 : 숫자 c를 만들 때 goal과 길이가 같은 것만 만들 경우 답이 틀리게 된다.goal길이 낮거나 높게 잡아 주어야 한다. 
// 반례1) 입력 1555 , 3 , 0 1 9 답 670나와야함 반례2) 99999 , 9 , 0 2 3 4 5 6 7 8 9 답 11118 나와야 함
// [빠른 처리를 위한 특별 분기]
// 1) goal이 100일 경우 0출력
// 2) 리모컨 모든 숫자가 입력 불가면 gaol-100의 절댓값
// 3) 리모컨의 모든 숫자가 입력 가능이면 gaol의 길이 반환
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
class Main{
	
	
	static String goal;    // 목표 문자열
	static int goalLength, goalInt; // 목표 문자열의 길이
	static int min = Integer.MAX_VALUE;
	static Set<Integer> nList = new HashSet<Integer>() {{
			add(0);add(1);add(2);add(3);add(4);add(5);add(6);add(7);add(8);add(9);
		   }};
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		goal = br.readLine();
		goalLength = goal.length();
		goalInt = Integer.parseInt(goal);
		
		// 입력 가능 버튼 추려내는 부분
		int t = Integer.parseInt(br.readLine());
		if(t==0) { // 특별 분기 : 리모컨 모든 숫자가 입력 가능한 경우
			min = Math.min(goalLength, Math.abs(100-goalInt));
			System.out.print(min);return;
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		while(t-->0) {
			nList.remove( Integer.parseInt(st.nextToken()) );
		}
		
		int arr[] = new int[nList.size()], idx = 0;
	
		for(int n : nList) arr[idx++] = n;
		
		
		// 특별 분기
		if(goalInt == 100) { // 목표가 100이면 100출력
			System.out.print(0); return;
		}else if(arr.length == 10) {
			System.out.print(Math.abs( goalInt - 100 ));return;
		}
		int i = goalLength == 1 ? goalLength : goalLength-1;
		for(; i<=goalLength+1; i++)
			DFS(0,"",arr,i); // 숫자 c 만들기 

        min = Math.min(min, Math.abs(goalInt-100));
        
		System.out.println(min);
	}
	public static void DFS(int depth,String make,int[] arr,int max) {
		if(depth==max) {
			// 최소 이동 값을 바로 min으로 저장.
			int len = make.length(); // 해당 숫자를 누르는데까지 걸리는 클릭수이며 100일 경우 길이는 0
			int value = Math.abs(Integer.parseInt(make)-goalInt) + len;// 만든숫자와 목표숫자를 빼고 길이를 더해서 이동횟수를 구한다.
			if(value<min)
				min = value;
			return;
		}
		for(int i=0; i<arr.length; i++) {
			DFS(depth+1, make+arr[i],arr,max);
		}
	}   
   
}