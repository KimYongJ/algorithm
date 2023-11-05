//https://github.com/KimYongJ/algorithm
class Main{
    public static void main(String[] args)throws Exception{
        int N = read(),s = 0, left = 0, right = 0, mid;;
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = read();
            s += arr[i]; // 입력되는 숫자를 모두 더해 놓는다.
            if(arr[i]>right)// 입력되는 숫자 중 가장 큰 수를 구해 놓는다.
            	right = arr[i];
        }
        int max = read();
        if(max>=s) { // 예산이 넉넉해서 모두 배정 가능한 경우
        	System.out.println(right);// 구한 가장 큰 값을 출력하고 바로 종료.
        	return;
        }

        // 이하 이진탐색 시작
        while(left<=right){ // 왼쪽이 완전 커질 때까지 반복
            mid = right+left>>1;// 가운데 값을 구한다.
            
            int sum = 0;
            for(int a : arr){
                sum += mid-a<0 ? mid : a; // 중간 값보다 큰 예산 요청일 경우 중간 값을 더하고, 아니면 요청 예산값을 더한다.
            }
            if(sum>max){ // 요청 예산을 모두 더한 값이 총예산보다 크다면
                right = mid-1;// 중간 값 -1
            }else if(sum==max){// 요청 예산을 모두 더한 값이 총예산과 같다면 출력 후 종료
                System.out.println(mid); return;
            }else {// 요청 예산을 모두 더한 값이 총예산보다 작다면
                left = mid+1;// 중간 값 +1
            }
        }
        System.out.println(left-1);
    }
    private static int read() throws Exception {
        int value = 0;
        while (true) {
            // 입력 문자의 ASCII코드 값.
            // 가령 '0'이 들어왔으면 숫자 0이 아니라 '0'의 ASCII 코드값인 48이다.
            int input = System.in.read();
            if (input == ' ' || input == '\n') { // 개행문자거나 공백이면 연산을 끊음
                return value; // 양수면 그냥 반환
            } else {
                value = value * 10 + (input - 48); // 기존 값을 10배하고 입력된 추가값을 파싱하여 더함
            }
        }
    }
}