import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 세 개의 숫자를 입력받음
            int d1 = sc.nextInt();
            int d2 = sc.nextInt();
            int d3 = sc.nextInt();

            // 종료 조건: 0 0 0 입력 시
            if (d1 == 0 && d2 == 0 && d3 == 0) break;

            // 시프트 값 계산 (세 숫자의 합)
            int shift = (d1 + d2 + d3) % 25 + 1;
            
            // 숫자 뒤에 남은 개행 문자 처리
            sc.nextLine(); 
            
            // 해독할 문장 입력
            String line = sc.nextLine();
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                // 소문자 알파벳인 경우만 복호화 수행
                if (c >= 'a' && c <= 'z') {
                    // 왼쪽으로 shift만큼 이동
                    char decoded = (char) (c - shift);
                    
                    // 'a'보다 작아질 경우 순환 처리
                    if (decoded < 'a') {
                        decoded = (char) (decoded + 26);
                    }
                    result.append(decoded);
                } else {
                    // 알파벳이 아니면 그대로 유지
                    result.append(c);
                }
            }

            // 결과 출력
            System.out.println(result.toString());
        }
        
        sc.close();
    }
}