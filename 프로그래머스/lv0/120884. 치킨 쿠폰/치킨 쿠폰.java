class Solution {
    public int solution(int ch) {
        int coupon = ch/10+ch%10;
        ch/=10;
        while(coupon>9){
            ch += coupon/10;
            coupon = (coupon>=10) ? (coupon/10+coupon%10) : (coupon/=10);
        }
        return ch;

    }
}