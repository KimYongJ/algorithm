// https://github.com/KimYongJ/algorithm
class Solution {
    /*
    * [ 공식 ]
    * 주어지는 큰 사각형을 작은 사각형으로 나누어 계산한다.
    * 작은 사각형의 크기는 가로와 세로의 최대 공약수를 각각 가로와 세로를 나누면 된다.
    * 그 후 멀쩡하지 않은 사각형의 크기 공식은 ( 가로길이 + 세로길이 - 1 ) 이다.
    * 작은 사각형의 멀쩡하지 않은 사각형을 구했으니 이를 최대 공약수에 곱해주면 
    * 큰 사각형의 멀쩡하지 않은 사각형의 총 갯수가 된다. 
    */
    public long solution(long w, long h) {
        long div = gcd(w,h); // 최대 공약수 
        
        return w*h - ( (w/div + h/div - 1) * div );
    }
    public long gcd(long a, long b ){
        return b==0 ? a : gcd(b,a%b);
    }
}