//https://www.acmicpc.net/problem/15151
//1초 512MB

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long k = Long.parseLong(st.nextToken());
        long d = Long.parseLong(st.nextToken());

        long time = k;   // 이번 책 소요시간: k, 2k, 4k, ...
        long sum = 0;    // 누적 소요시간
        int cnt = 0;     // 완성한 책 수

        while (sum + time <= d) {
            sum += time;    // 이번 책을 완성
            cnt++;
            time *= 2;      // 다음 책은 2배
        }

        System.out.print(cnt);
    }
}