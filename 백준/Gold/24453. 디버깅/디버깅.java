import java.util.*;

public class Main {
    static boolean[] error = new boolean[20000001];
    static int N, M, X, Y;

    public static void input(Scanner sc) {
        N = sc.nextInt();
        M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            error[x] = true;
        }

        X = sc.nextInt();
        Y = sc.nextInt();
    }

    public static void solution() {
        int start = 1, end = 1;
        int er_cnt = 0; // start~end까지 에러의 개수
        int su_cnt = 0; // start~end까지 연속된 줄의 개수
        int ans = Integer.MAX_VALUE; // 최소 에러 개수

        if (N == 1) {
            System.out.println(M - Y); // N이 1일 때는 에러의 개수 - 인규가 찾아야 하는 에러 개수 출력
            return;
        }

        while (true) {
            if (start == N) break;

            if ((er_cnt < Y || su_cnt < X) && end <= N) {
                // 모든 조건을 만족하지 않은 경우 end를 증가
                if (error[end++]) er_cnt++; // 현재 줄에 에러가 있다면 에러 개수를 증가
                su_cnt++;
            } else {
                // 모든 조건을 만족하면 최소값 갱신
                if (er_cnt >= Y && su_cnt >= X) ans = Math.min(ans, er_cnt);
                if (error[start++]) er_cnt--; // 현재 줄에 에러가 있다면 에러 개수를 감소
                su_cnt--;
            }
        }

        // 전체 에러의 개수 - 최소로 인규가 지워야 하는 에러의 개수가 답
        System.out.println(M - ans);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input(sc);
        solution();
        sc.close();
    }
}
