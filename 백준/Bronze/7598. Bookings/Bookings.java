import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            String flightNum = sc.next();
            
            // 입력 종료 조건
            if (flightNum.equals("#")) {
                break;
            }
            
            int booked = sc.nextInt();  // 초기 예약 좌석 수
            
            // 각 비행편의 트랜잭션 처리
            while (true) {
                String cmd = sc.next();
                int n = sc.nextInt();
                
                if (cmd.equals("X")) {
                    // 비행편 처리 완료
                    System.out.println(flightNum + " " + booked);
                    break;
                } else if (cmd.equals("B")) {
                    // 예약: 68석을 넘으면 무시
                    if (booked + n <= 68) {
                        booked += n;
                    }
                } else if (cmd.equals("C")) {
                    // 취소: 현재 예약보다 많으면 무시
                    if (n <= booked) {
                        booked -= n;
                    }
                }
            }
        }
    }
}