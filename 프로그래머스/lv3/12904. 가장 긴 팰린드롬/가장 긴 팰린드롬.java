class Solution{
    private int result = 1; // 팰린드롬이 없을 때 결과는 1이여야 한다. 0으로 세팅시 17~18번 테스트 케이스 통과 불가
    private char[] arr;
    public int solution(String s){
        arr = s.toCharArray(); // 빠른 연산을 위해 배열로 전환
        int len = arr.length;
        int maxchar = 1; // 이변수는 최대 X개일 때 몇번 반복할지를 체크하는 변수이다.
        // 가장긴 글자 팰린드롬 부터 확인, 총 글자가 5개면 5개부터,4개,3개 글자순으로 차례로 비교한다.
        Loop : for(int i=len;i>1; i--){//n개 글자부터 2개 글자까지 확인
            for(int a=0;a<maxchar; a++){
                if(palindrome(a,a+i-1)) // 팰린드롬 함수에 시작 인덱스와 종료 인덱스를 보낸다.
                    break Loop; // 처음 찾은 팰린드롬이 가장 긴 문자열이므로 바로 반환한다.
            }
            maxchar++;
        }
        return result;
    }
    public boolean palindrome(int start, int end){
        int len = end-start+1;
        for(int i=0; i<len/2; i++){
            if(arr[start++] != arr[end--]){
                return false;
            }
        }
        result = len;
        return true;
    }
}