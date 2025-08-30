//https://www.acmicpc.net/submit/6359
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine()); // 방의 개수
            // 제곱수 개수 = 정수 제곱근 값
            int answer = (int) Math.sqrt(n);
            System.out.println(answer);
        }
    }
}
