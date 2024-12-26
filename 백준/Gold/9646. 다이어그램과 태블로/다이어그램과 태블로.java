import java.util.*;

public class Main {
    // 문제에서 사용된 전역 변수들을 static으로 선언
    static int n, k;           // n, k
    static int[][] arr = new int[10][10]; // [10][10]로 잡아, 인덱스 1~8 사용 가능
    static int[] info = new int[10];      // info 또한 1~8 인덱스 사용 가능
    static int cnt = 0;

    // dfs 함수: C++ 코드의 dfs(r, c)와 동일한 로직
    static void dfs(int r, int c) {
        // 종료 조건
        if (r == 1 && c > info[1]) {
            cnt++;
            return;
        }

        // 왼쪽(left), 위(up) 값을 구해서
        int left = arr[r][c - 1];
        int up   = arr[r - 1][c];

        // [max(left, up+1) ~ n] 범위로 arr[r][c] 설정
        for (int i = Math.max(left, up + 1); i <= n; i++) {
            arr[r][c] = i;

            // c가 info[r+1]보다 작거나 같다면 다음 행(r+1)으로 dfs
            if (c <= info[r + 1]) {
                dfs(r + 1, c);
            }
            // 그렇지 않다면 r=1로 돌리고, 열(c+1) 증가
            else {
                dfs(1, c + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // C++ 코드에서 EOF까지 반복하므로, Java에서도 입력이 더 이상 없으면 종료
        while (sc.hasNextInt()) {
            // 각 루프 시작 시 cnt=0, arr, info 초기화
            cnt = 0;
            for (int i = 0; i < 10; i++) {
                Arrays.fill(arr[i], 0);
            }
            Arrays.fill(info, 0);

            // k 읽기
            k = sc.nextInt();
            // k개만큼 info 배열에 입력 (인덱스 1부터)
            for (int i = 1; i <= k; i++) {
                info[i] = sc.nextInt();
            }

            // n 읽기
            n = sc.nextInt();

            // dfs 시작
            dfs(1, 1);

            // 결과 출력
            System.out.println(cnt);
        }
    }
}
