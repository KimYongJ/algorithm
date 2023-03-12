class Solution {
    public int solution(int num) {
        return find(0,num);
    }
    public int find(int depth, long num){
        if(depth==500) return -1;
        else if(num==1) return depth;

        num = num%2==0 ? num/2 : num*3+1;
        
        return find(depth+1,num);
    }
}