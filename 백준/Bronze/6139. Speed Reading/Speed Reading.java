import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            int pagesPerCycle = S * T;
            int fullCycles = N / pagesPerCycle;
            int rem = N % pagesPerCycle;

            int answer;
            if (rem == 0) {
                answer = fullCycles * (T + R) - R;
            } else {
                answer = fullCycles * (T + R) + (rem + S - 1) / S;
            }

            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }
}