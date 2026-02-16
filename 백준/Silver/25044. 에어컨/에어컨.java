//https://www.acmicpc.net/problem/25044
//1초 1024MB
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long startDay = (long) N * 1440;
        long endDay = startDay + 1440;

        // 15:00 -> 18:00 (+180), 18:00 -> 21:00 (+180), 21:00 -> next 15:00 (+1080)
        int[] gap = {180, 180, 1080};

        long t = 15 * 60; // 첫 꺼짐: 0일 15:00 = 900분
        int cnt = 0;      // 현재 꺼짐이 몇 번째인지 (0부터)

        ArrayList<Long> ans = new ArrayList<>();

        while (t < endDay) {
            if (t >= startDay) ans.add(t);

            // 다음 꺼짐 시각 계산
            t += gap[cnt % 3];
            if (cnt % 3 == 2) t += K; // 세 번째 꺼짐 직후, 시계가 K분 멈춰 다음 꺼짐이 K분 늦어짐
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans.size()).append('\n');
        for (long time : ans) {
            long m = time % 1440; // 해당 날의 시각(분)
            long hh = m / 60;
            long mm = m % 60;
            sb.append(String.format("%02d:%02d", hh, mm)).append('\n');
        }

        System.out.print(sb.toString());
    }
}

