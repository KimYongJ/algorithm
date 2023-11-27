// https://github.com/KimYongJ/algorithm
/*
 * 제한시간 1초 : 1억번 안에 연산이 끝나야 합니다.
 * 최대 메모리 256M 바이트 : 인접 노드를 생성할 때 int형 2차원 배열로 하면 메모리 초과가 납니다. 
 * 그 이유는 int는 4byte입니다. 최대 노드의 갯수 N이 10만개일 수 있습니다. N이 10만개일 때
 * 2차원 배열 생성시 배열에만 160메가 바이트가 소모됩니다. 그렇기에 필요한 인접 노드만 저장할 수 있도록
 * list로 선언해야 합니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Main{

	static StringBuilder sb = new StringBuilder();
	static int N,M;
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//1부터 N까지 자연수 중에서 M개를 고른 수열
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		combination(0,0,new int[M]);
		
		System.out.println(sb);
	}
	public static void combination(int r, int start, int[] arr) {
		if(r==M) {
			for(int i=0; i<M; i++) {
				sb.append(arr[i]).append(' ');
			}sb.append('\n');
			return;
		}
		for(int i=start; i<N; i++) {
			arr[r] = i+1;
			combination(r+1,i,arr);
		}
	}
}