class Solution
{
    static int idx;
    public int solution(int n, int a, int b)
    {
        while(true){
            if(meat(a,b))
                return ++idx;
            a = check(a);
            b = check(b);
            ++idx;
        }
    }
    public boolean meat(int a, int b){
        int min = Math.min(a,b);
        int max = Math.max(a,b);
        return min+1==max && min%2!=0 ? true : false;
    }
    public int check(int num){
        return num%2==1 ? (num+1)/2 : num/2;
    }
}