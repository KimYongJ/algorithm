//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/10529
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static boolean[] check = new boolean[10], visit = new boolean[10];
    static int n = 0, answer = 0;
    static String[] str;
    static Map<Character, Integer> ch = new HashMap<>();
    static long[] sum = new long[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        str = new String[num];

        // 입력 처리 및 문자 매핑
        for (int i = 0; i < num; i++) {
            str[i] = br.readLine();
            for (char x : str[i].toCharArray()) {
                if (!ch.containsKey(x)) {
                    ch.put(x, n++);
                }
            }
        }

        // 각 문자에 대해 첫 글자인 경우 체크
        for (int i = 0; i < num; i++) {
            check[ch.get(str[i].charAt(0))] = true;
        }

        // 각 문자의 가중치 계산
        for (int i = 0; i < num; i++) {
            int len = str[i].length();
            long base = 1;
            for (int j = len - 1; j >= 0; j--, base *= 10) {
                if (i == num - 1) {
                    sum[ch.get(str[i].charAt(j))] -= base;
                } else {
                    sum[ch.get(str[i].charAt(j))] += base;
                }
            }
        }

        // 백트래킹 탐색
        f(0, 0);

        // 결과 출력
        System.out.println(answer);
    }

    public static void f(int idx, long cost) {
        if (idx == n) {
            if (cost == 0) {
                answer++;
            }
            return;
        }

        for (int k = 0; k <= 9; k++) {
            if (k == 0 && !check[idx] && !visit[k]) {
                visit[k] = true;
                f(idx + 1, cost);
                visit[k] = false;
            } else if (k > 0 && !visit[k]) {
                visit[k] = true;
                f(idx + 1, cost + sum[idx] * k);
                visit[k] = false;
            }
        }
    }
}
