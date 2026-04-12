import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 1년 1월 1일부터 1000년 1월 1일까지의 총 일수 미리 계산
        int totalTo1000 = 0;
        for (int y = 1; y < 1000; y++) {
            if (y % 3 == 0) {
                totalTo1000 += 200; // 3의 배수 해는 200일
            } else {
                totalTo1000 += 195; // 일반 해는 195일
            }
        }
        totalTo1000 += 1; // 1000년 1월 1일 당일의 1일 추가

        // 2. 테스트 케이스 수 입력
        if (sc.hasNextInt()) {
            int n = sc.nextInt();

            for (int i = 0; i < n; i++) {
                int y = sc.nextInt();
                int m = sc.nextInt();
                int d = sc.nextInt();

                int currentDays = 0;

                // 2-1. 1년부터 y-1년까지의 일수 합산
                for (int year = 1; year < y; year++) {
                    if (year % 3 == 0) {
                        currentDays += 200;
                    } else {
                        currentDays += 195;
                    }
                }

                // 2-2. y년의 1월부터 m-1월까지의 일수 합산
                for (int month = 1; month < m; month++) {
                    if (y % 3 == 0) {
                        // 3의 배수 해는 모든 달이 20일
                        currentDays += 20;
                    } else {
                        // 일반 해는 홀수달 20일, 짝수달 19일
                        if (month % 2 == 1) {
                            currentDays += 20;
                        } else {
                            currentDays += 19;
                        }
                    }
                }

                // 2-3. 해당 월의 d일 합산
                currentDays += d;

                // 3. 차이값 출력
                System.out.println(totalTo1000 - currentDays);
            }
        }
        sc.close();
    }
}