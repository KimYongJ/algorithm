
//https://www.acmicpc.net/problem/1942
//2초 128MB
// 00:59:58 01:01:24 // 시작, 끝 시간이 주어짐
// 22:47:03 01:03:24
// 00:00:09 00:03:37
// 구간에 포함되는 시계들 중 3의 배수 개수 출력
// 29
// 2727
// 70

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(
	        new StringBuilder()
	        	.append(cal(br.readLine())).append('\n')
	        	.append(cal(br.readLine())).append('\n')
	        	.append(cal(br.readLine()))
        );
    }
    static int cal(String str){
        StringTokenizer st = new StringTokenizer(str);
        String[] s = st.nextToken().split("\\:");
        String[] e = st.nextToken().split("\\:");
        int start = Integer.parseInt(s[0]) * 3600 + Integer.parseInt(s[1]) * 60 + Integer.parseInt(s[2]);
        int end = Integer.parseInt(e[0]) * 3600 + Integer.parseInt(e[1]) * 60 + Integer.parseInt(e[2]);
        int cnt = 0;
		int now = start;
		int mod = 24 * 3600;
		while(true){
			if(getTime(now) % 3 == 0){
				++cnt;
			}
			if(now++ == end)
				break;
			now %= mod;
		}
	        return cnt;
    }
    static int getTime(int time){
        int hh = time / 3600;
        int mm = (time % 3600) / 60;
        int ss = time % 60;
        return hh * 10000 + mm * 100 + ss;
    }
}