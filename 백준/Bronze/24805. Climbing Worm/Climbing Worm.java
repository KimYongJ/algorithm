//https://www.acmicpc.net/problem/24805
//1초 1024

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());//벌레가 한 번에 기어올라갈 수 있는 높이
		int b = Integer.parseInt(st.nextToken());//벌레가 쉬는 동안 내려가는 높이
		int h = Integer.parseInt(st.nextToken());//기둥의 높이(인치)를 의미한다
		
		int cnt = 0;
		int now = 0;
		while(now < h) {
			++cnt;
			now += a;
			if(now >= h)
				break;
			now -= b;
		}
		System.out.print(cnt);
	}
}