class Solution {
    public String solution(int a, int b) {
        int[] marr = {31,29,31,30,31,30,31,31,30,31,30,31};
        String[] dayString = {"THU","FRI","SAT","SUN","MON","TUE","WED"};
        int day = 0;
        for(int i=0;i<a-1;i++)
            day += marr[i];
        
        day += b;
        
        return dayString[day%7];
        
    }
}