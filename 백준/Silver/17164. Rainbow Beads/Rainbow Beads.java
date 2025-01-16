import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] o = br.readLine().toCharArray();

        // 결과 변수
        int result = 0;

        // 슬라이딩 윈도우
        int s = 0; // 시작점
        for (int e = 0; e < N; e++) { // 끝점 확장
            if (e > 0 && (!isBeautiful(o[e - 1], o[e]) || !isBeautiful(getConverted(o[e - 1], 'B'), getConverted(o[e], 'B')) 
                    || !isBeautiful(getConverted(o[e - 1], 'R'), getConverted(o[e], 'R')))) {
                // 조건을 만족하지 않으면 시작점 이동
                s = e;
            }
            result = Math.max(result, e - s + 1); // 최대 길이 갱신
        }

        System.out.print(result);
    }

    // 두 문자가 아름다운지 확인
    private static boolean isBeautiful(char a, char b) {
        return a != b;
    }

    // 색상 변환 (V를 R 또는 B로 변환)
    private static char getConverted(char c, char target) {
        return c == 'V' ? target : c;
    }
}
