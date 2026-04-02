import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());

            double[] h = new double[N];
            for (int i = 0; i < N; i++) {
                h[i] = Double.parseDouble(st.nextToken());
            }

            double prev = h[0];
            double cur = h[0];

            for (int i = 1; i <= N - 2; i++) {
                cur = Math.min(h[i], (prev + h[i + 1]) / 2.0);
                prev = cur;
            }

            out.append(String.format(Locale.US, "Case #%d: %.6f%n", tc, cur));
        }

        System.out.print(out);
    }
}