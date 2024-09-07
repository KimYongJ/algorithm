import java.util.Scanner;

public class Main {

    static final int NMAX = 500 * 2 + 1;

    static int[] origin = new int[NMAX];
    static int[] convert = new int[NMAX];

    static int n;
    static int l, r;
    static int t, tmp_cnt, cnt;

    public static void k_push() {
        for (int i = 0; i < n; i++) origin[i] = convert[i];

        for (int i = 0; i < n; i++) convert[(i + 1) % n] = origin[i];
    }

    public static void pq_flip(int p, int q) {
        for (int i = 0; i < n; i++) origin[i] = convert[i];

        for (int i = 0; i < p; i++) convert[i] = origin[i];
        for (int i = p, j = q; i <= j; i++, j--) {
            convert[i] = origin[j];
            convert[j] = origin[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            origin[i] = sc.nextInt() - 1;
            convert[i] = origin[i];
        }

        for (int i = 0; i < n; i++) {
            t = convert[i];
            tmp_cnt = 0;
            for (int j = (i + 1) % n; j != i; j = (j + 1) % n) {
                if ((t - 1 + n) % n != convert[j]) break;
                tmp_cnt++;
                t = (t - 1 + n) % n;
            }

            if (cnt < tmp_cnt) {
                cnt = tmp_cnt;
                l = i;
                r = (i + cnt) % n;
            }
        }

        if (r - l + 1 == n) {
            int p1, p2;

            // 1. 마지막 왼쪽 밀기 (뒤집힌 구간 찾기)
            p1 = 0;
            do {
                k_push();
                p1++;
            } while (convert[n - 1] == 0);

            // 2. 구간 뒤집기 (항상 맨 끝에서 뒤집기)
            pq_flip(0, n - 1);

            // 3. 첫 왼쪽 밀기 (1의 위치 찾기)
            for (p2 = 0; convert[p2] != 0; p2++);

            System.out.println(n - p2);
            System.out.println(1 + " " + n);
            System.out.println(p1);
        } else {
            int p1, p2;

            // 1. 마지막 왼쪽 밀기 (뒤집힌 구간 찾기)
            for (p1 = 1; p1 < n; p1++) {
                k_push();
                l = (l + 1) % n;
                r = (r + 1) % n;

                if (l > r) continue;
                else if ((l == 0 && convert[r] == 0) || convert[0] == 0) continue;
                else break;
            }

            // 2. 구간 뒤집기
            pq_flip(l, r);

            // 3. 첫 왼쪽 밀기 (1의 위치 찾기)
            for (p2 = 0; convert[p2] != 0; p2++);

            System.out.println(n - p2);
            System.out.println((l + 1) + " " + (r + 1));
            System.out.println(p1);
        }

        sc.close();
    }
}