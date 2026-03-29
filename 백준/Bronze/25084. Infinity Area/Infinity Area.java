import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long r = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            double sum = 0.0;

            while (r > 0) {
                sum += Math.PI * r * r; // 현재 반지름 원
                r *= a;
                sum += Math.PI * r * r; // A배한 반지름 원
                r /= b;                 // 다음 시작 반지름
            }

            sb.append("Case #").append(tc).append(": ")
              .append(String.format(Locale.US, "%.6f", sum))
              .append('\n');
        }

        System.out.print(sb);
    }
}