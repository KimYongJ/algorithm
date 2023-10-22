// https://github.com/KimYongJ/algorithm
class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end-begin)+1]; // 결과 배열 
        int i = 0; // for문을 돌릴 index
        if(begin==1) begin += i = 1; // 시작이 1인경우 맨 처음 0이 들어가야 하므로 넣은 코드.
        
        for(; i<answer.length; i++,begin++)
            answer[i] = isPrimeAndMeasure(begin); // begin이 소수인지 판별하고 소수면 1을 넣고, 아니면 자기를 제외한 최대 약수를 넣는다. 
        
        return answer;
    }
    public int isPrimeAndMeasure(long n){
        int max = 1; // 소수일 경우 1을 그대로 반환
        for(int i=2; i*i<=n ;i++){// 소수를 구하거나, 본인제외 최대 약수 구할 때 i*i<=n을 쓴다.
            if(n%i==0){ // i로 나누어 떨어질 때 
                if(n/i<=10_000_000){ // 1부터~ 10,000,000까지의 숫자만 입력되야 한다. n이 10억일 수도 있어서 이경우 나누면 천만보다 클 수 있어서 문제 조건에 맞지 않게되어 넣은 코드.
                    return (int)(n/i);
                }else{
                    max = i; // n을 i로 나눈수가 천만을 초과하면 최대 약수가 i가 된다.
                }
            } 
        }
        return max;// n이 소수면 1을, 소수가 아니면 최대약수를 반환
    }
}