//https://www.acmicpc.net/problem/25905
//1초 1024MB
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // "0", "0.1", "0.10", "1", "1.0", "1.00" 등을 받아서
    // 소수 둘째 자리까지를 100배 정수(0~100)로 변환
    static long parseProb100(String s) {
        s = s.trim();
        int dot = s.indexOf('.');
        if (dot < 0) { // "0" or "1"
            return Long.parseLong(s) * 100L;
        }

        String intPart = s.substring(0, dot);
        String fracPart = s.substring(dot + 1);

        if (intPart.equals("1")) return 100L; // 1.0, 1.00 등

        // intPart == "0" 가정(문제 확률 범위)
        if (fracPart.length() == 0) fracPart = "00";
        else if (fracPart.length() == 1) fracPart = fracPart + "0";
        else if (fracPart.length() > 2) fracPart = fracPart.substring(0, 2); // 안전장치

        // "05" -> 5, "50" -> 50
        return Long.parseLong(fracPart);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] p = new long[10];
        int idx = 0;

        // 입력이 줄/공백 어떤 형태든 10개 토큰을 읽도록 처리
        while (idx < 10) {
            String line = br.readLine();
            if (line == null) break;
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens() && idx < 10) {
                p[idx++] = parseProb100(st.nextToken());
            }
        }

        Arrays.sort(p); // 오름차순: p[0]이 최소

        long prod = 1L;
        for (int i = 1; i < 10; i++) { // 최소 하나 제외하고 9개 곱
            prod *= p[i];
        }

        final double DEN = 362880000000000.0; // 9! * 100^9 / 10^9
        double ans = prod / DEN;

        System.out.printf("%.6f%n", ans);
    }
}
