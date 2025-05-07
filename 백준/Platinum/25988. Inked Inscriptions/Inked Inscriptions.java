//https://github.com/KimYongJ/algorithm
//https://www.acmicpc.net/problem/25988
//4초 2048MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//시편의 수 (1<=10,000)
		int sqrt = (int)Math.sqrt(N);
		
		ArrayList<Page> page = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int origin=1; origin<=N; origin++)
		{
			int copy = Integer.parseInt(st.nextToken());
			// 원래 페이지와 복사할 위치의 페이지 번호를 넣고, 복사할 위치 페이지 번호를 기준으로 구간을 정합니다.
			page.add(new Page(origin, copy, copy / sqrt));
		}
		
		Collections.sort(page);
		
		StringBuilder sb = new StringBuilder();
		// 출력시 원래 페이지 먼저 출력합니다.
		for(Page p : page)
			sb.append(p.origin).append(' ').append(p.copy).append('\n');

		System.out.print(sb);
	}
	static class Page implements Comparable<Page>{
		int origin, copy, factor;
		Page(int o, int c, int f){
			this.origin = o;
			this.copy = c;
			this.factor = f;
		}
		@Override
		public int compareTo(Page p) {
			// 구간이 동일 하지 않다면 구간 기준 오름차순 정렬
			if(this.factor != p.factor)
				return this.factor - p.factor;

			// 구간이 짝수면 원래 페이지 기준 오름차순 정렬
			if(this.factor % 2 == 0)
				return this.origin - p.origin;
			
			// 구간이 홀수면 원래 페이지 기준 내림차순 정렬
			return p.origin - this.origin;
		}
	}
}
//5			// 시편의 수 (1<=10,000)
//4 1 3 5 2	// i번째 정수가 j라면, i 페이지에 있는 시편을 새로운 책의 j페이지에 복사해야 함을 의미
//// 답 : 기존 책의 i페이지에 있는 시편을 새로운 책의 j페이지에 복사 해야 함을 출력
//2 1
//3 3
//5 2
//4 5
//1 4