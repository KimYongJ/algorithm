class Solution
{
    static int idx;
    public int solution(int n, int a, int b)
    {
        while(true){
            if(meat(a,b))
                return ++idx;
            a = a%2==1 ? (a+1)/2 : a/2;
            b = b%2==1 ? (b+1)/2 : b/2;
            ++idx;
        }
    }
    public boolean meat(int a, int b){
        int min = Math.min(a,b);
        int max = Math.max(a,b);
        return min+1==max && min%2!=0 ? true : false;
    }
}