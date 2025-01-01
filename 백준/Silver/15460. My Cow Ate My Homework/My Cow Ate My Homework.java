//https://github.com/kimyongj/algorithm
//https://www.acmicpc.net/problem/15460
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 숙제 문제의 개수(N)를 입력받음
        int n = scanner.nextInt();

        // 각 문제의 점수를 저장할 배열
        int[] s = new int[n];

        // 뒤에서부터 누적 합을 저장할 배열
        int[] a = new int[n];

        // 뒤에서부터 최소값을 저장할 배열
        int[] m = new int[n];

        // 문제 점수를 배열에 저장
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }

        // 누적 합 및 최소값 배열의 마지막 요소 초기화
        a[n - 1] = s[n - 1];
        m[n - 1] = s[n - 1];

        // 뒤에서부터 누적 합과 최소값 계산
        for (int i = n - 2; i >= 0; i--) {
            // 누적 합: i부터 끝까지의 합
            a[i] = a[i + 1] + s[i];

            // 최소값: i부터 끝까지의 최솟값
            m[i] = Math.min(s[i], m[i + 1]);
        }

        // 최대 평균 점수를 추적하기 위한 변수 (합과 개수)
        long maxSum = 0; // 최대 합
        int maxCount = 1; // 해당 합에 대한 개수

        // k의 가능한 값(1부터 n-2까지)을 순회
        for (int k = 1; k < n - 1; k++) {
            // 현재 k에서의 평균 계산
            // (a[k] - m[k]) / (n - k - 1) > maxAvg 인 경우
            if ((long) (a[k] - m[k]) * maxCount > maxSum * (n - k - 1)) {
                // maxSum과 maxCount 업데이트
                maxSum = a[k] - m[k];
                maxCount = n - k - 1;
            }
        }

        // 최대 평균을 달성하는 모든 k 값을 출력
        for (int k = 1; k < n - 1; k++) {
            // 현재 평균이 최대 평균과 동일한지 확인
            if ((long) (a[k] - m[k]) * maxCount == maxSum * (n - k - 1)) {
                // 최대 평균에 기여하는 k를 출력
                System.out.println(k);
            }
        }
    }
}
