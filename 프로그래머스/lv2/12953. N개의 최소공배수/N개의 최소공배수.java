class Solution {
    public int solution(int[] arr) {
        int num = arr[0];
        
        for(int i=1; i<arr.length; i++)
            num = gcd(num,arr[i],num,arr[i]);
        
        return num;
    }
    public int gcd(int a,int b,int n, int m){
        if(b==0) return n*m/a;
        return gcd(b,a%b,n,m);
    }
}