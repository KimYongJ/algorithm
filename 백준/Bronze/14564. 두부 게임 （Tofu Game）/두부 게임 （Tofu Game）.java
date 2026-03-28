import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken()); // 현재 기준 사람(1-indexed)

        int center = m / 2 + 1; // 기준이 되는 모 수
        StringBuilder sb = new StringBuilder();

        while (true) {
            int t = Integer.parseInt(br.readLine());

            if (t == center) {
                sb.append(0);
                break;
            }

            int diff = t - center;
            a = ((a - 1 + diff) % n + n) % n + 1;
            sb.append(a).append('\n');
        }

        System.out.print(sb);
    }
}