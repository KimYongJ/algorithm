// https://github.com/KimYongJ/algorithm
class Solution {
    private int startIdx = 0;
    private int endIdx;
    private StringBuilder sb = new StringBuilder();
    
    public String solution(String number, int k) {
        char[] arr = number.toCharArray();
        endIdx = k+1; // 탐색을할 배열의 종료인덱스 세팅
        
        for(int i=0; i<number.length()-k; i++){// 최종 만들 문자열 길이 만큼 반복
            findMax(arr);// 문자열 하나하나에 맥스값을 찾아 만든다.
        }
        return sb.toString();
    }
    public void findMax(char[] arr){// 시작 인덱스부터 종료인덱스까지 가장 큰 값을 찾고 처음 나오는 큰값의 인덱스를 startIndex로 만든다.
        char max = '0';
        for(int i=startIdx;i<endIdx; i++){
            if(arr[i]>max){// 큰 숫자를 찾았다면 max에 대입하고 시작인덱스를 max의 인덱스+1로 세팅한다.
                max = arr[i];
                startIdx = i+1;
            }
        }
        sb.append(max);// 가장 큰 값을 결과에 더해준다.
        endIdx++; // 연산이 끝난 후 다음 탐색할 endIdx에 +1을 해준다.
    }
}