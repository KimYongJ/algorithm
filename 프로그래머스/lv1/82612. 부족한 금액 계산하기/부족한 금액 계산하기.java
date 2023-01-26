class Solution {
    public long solution(int price, long money, int count) {
        for(int i=1;i<=count; i++)
            money-=price*i;
        return money >0 ? 0 : Math.abs(money);
    }
}